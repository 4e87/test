package com.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketSessionMessaging {

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    public List<WebSocketSession> getActiveSessions() {
        return new ArrayList<>(sessions.values());
    }

    public void addWebSocketSession(WebSocketSession webSocketSession) {
        sessions.put(webSocketSession.getId(), webSocketSession);
    }
}