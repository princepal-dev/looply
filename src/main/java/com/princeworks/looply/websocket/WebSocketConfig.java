package com.princeworks.looply.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private CustomHandShakeInceptor customHandShakeInceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry
        .addEndpoint("/ws/chat")
        .setAllowedOrigins("*")
        .addInterceptors(customHandShakeInceptor)
        .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chat");
    }
    // 4. create websocket message model (ChatMessageEvent)
}
