package com.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;


public class TestWebSocketHandler extends AbstractWebSocketHandler {
    @Autowired
    WebSocketSessionMessaging webSocketSessionMessaging;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //session.sendMessage(new TextMessage("test"));
        webSocketSessionMessaging.addWebSocketSession(session);
    }
}
