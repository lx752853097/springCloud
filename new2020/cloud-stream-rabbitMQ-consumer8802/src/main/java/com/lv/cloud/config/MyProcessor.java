package com.lv.cloud.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 *  stream 自带的 Sink 和 Source，也仅仅能做个演示，真正的业务中还是需要自己定义更加灵活的接口。作为使用的channel通信通道
 */
@Component
public interface MyProcessor extends Source, Sink {

    String MESSAGE_INPUT = "log_input";

    String MESSAGE_OUTPUT = "log_output";

    String LOG_FORMAT_INPUT = "log_format_input";

    String LOG_FORMAT_OUTPUT = "log_format_output";

    @Input(MESSAGE_INPUT)
    SubscribableChannel logInput();

    @Output(MESSAGE_OUTPUT)
    MessageChannel logOutput();

    @Input(LOG_FORMAT_INPUT)
    SubscribableChannel logFormatInput();

    @Output(LOG_FORMAT_OUTPUT)
    MessageChannel logFormatOutput();
}
