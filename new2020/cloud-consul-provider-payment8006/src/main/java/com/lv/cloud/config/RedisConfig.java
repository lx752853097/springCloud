package com.lv.cloud.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
//开启基于注解版的缓存
@EnableCaching
@ConfigurationProperties(prefix = "spring.cache.redis")
public class RedisConfig extends CachingConfigurerSupport {

    private Duration timeToLive = Duration.ZERO;

    public void setTimeToLive(Duration timeToLive) {
        this.timeToLive = timeToLive;
    }

    /**
     * Logger
     */
    private static final Logger lg = LoggerFactory.getLogger(RedisConfig.class);

    //@Autowired
    //private JedisConnectionFactory jedisConnectionFactory;
    @Autowired
    private LettuceConnectionFactory lettuceConnectiionFactory;

    //给容器中添加组件
    //注解：
    //主键生成策略，如果使用缓存的时候，没有指定key并且方法没有参数或者2个及以上，那么是个生成策略就会生效，并且生成一个key；否则使用key作为key
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        //  设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
        // 使用：进行分割，可以很多显示出层级关系
        // 这里其实就是new了一个KeyGenerator对象，只是这是lambda表达式的写法，我感觉很好用，大家感兴趣可以去了解下
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            lg.info("自动生成Redis Key -> [{}]", rsToUse);
            return rsToUse;
        };
    }

    //CacheManager 返回值，表示通过注入cacheManager来使用哪种类型的Cache，这里使用的redis缓存，在使用缓存注解的时候默认使用redis缓存；
    //@Bean
    //@Override
   /* public CacheManager cheManager() {
        // 初始化缓存管理器，在这里我们可以缓存的整体过期时间什么的，我这里默认没有配置
        lg.info("初始化 -> [{}]", "CacheManager RedisCacheManager Start");
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(this.timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(lettuceConnectiionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
        return redisCacheManager;
    }*/

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectiionFactory) {
        lg.info("实例化并加入spring容器");
        //设置序列化 json格式序列化
        RedisSerializer<Object> jackson2JsonRedisSerializer = valueSerializer();
        //设置序列化，String格式
        RedisSerializer<String> stringSerializer = keySerializer();
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectiionFactory);
        redisTemplate.setKeySerializer(stringSerializer); // key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
        redisTemplate.setHashKeySerializer(stringSerializer); // Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer); // Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    //设置序列化方法
    // String格式的
    public RedisSerializer<String> keySerializer(){
        return new StringRedisSerializer();
    }
    //sjon格式的
    public RedisSerializer<Object> valueSerializer(){
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =  new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        lg.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                lg.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                lg.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                lg.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                lg.error("Redis occur handleCacheClearError：", e);
            }
        };
        return cacheErrorHandler;
    }
}
