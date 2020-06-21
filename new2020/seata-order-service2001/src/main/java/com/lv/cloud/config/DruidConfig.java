package com.lv.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//配置代理数据源，需要将业务数据源放到代理数据源中，另外mybatis的SqlSessionFactory需要手动配置，将代理数据源写入mybatis的SqlSessionFactory中
@Configuration
public class DruidConfig {
    @Value("${spring.application.name}")
    private String applicationId;

    // mybatis配置
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;
    //@Value("${mybatis.configuration.map-underscore-to-camel-case}")
    //private boolean mapUnderscoreToCamelCase;
    @Value("${mybatis.config-location}")
    private String configLocation;

    //配置文件application.yml中配置 数据源其他配置， 显示为黄色字体表示DataSourceProperties里面没有这些属性，需要创建一个DruidDataSource，并注入到容器；
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druib(){
        return new DruidDataSource();
    }

    @Primary//@Primary标识必须配置在代理数据源上，否则本地事务失效
    @Bean("dataSourceProxy")
    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceProxy);
        factoryBean.setTypeAliasesPackage(typeAliasesPackage);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        factoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        //factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        return factoryBean.getObject();
    }

    /**
     * 初始化分布式全局事务扫描
     * fsp_tx_group
     * @return
     */
    /*@Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        return new GlobalTransactionScanner(applicationId.toLowerCase(), "my_test_tx_group");
    }*/

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams = new HashMap<>();
        //设置后台登陆账号、密码等
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
        //不允许谁访问 ，比如某个IP
        //initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        //拦截所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
