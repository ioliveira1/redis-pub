package com.ioliveira.redispub.publishers;

public interface MessagePublisher {
    void publish(final String channel, final String message);
}
