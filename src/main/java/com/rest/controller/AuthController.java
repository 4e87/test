package com.rest.controller;

import com.rest.config.WebSocketSessionMessaging;
import com.rest.domain.User;
import com.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    WebSocketSessionMessaging webSocketSessionMessaging;

    @PostMapping("/user")
    public User profile(@AuthenticationPrincipal User user) {
        return (User)userService.loadUserByUsername(user.getUsername());
    }

    @RequestMapping(value = "/reg")
    public String regUser(@RequestBody(required = true) User user) throws IOException {
        if (userService.addUser(user)) {
            for (WebSocketSession wss : webSocketSessionMessaging.getActiveSessions()) {
                wss.sendMessage(new TextMessage(user.getUsername() + " created"));
            }
        }
        return "";
    }

    @PostMapping("/showall")
    public ResponseEntity showAllUsers(@RequestParam(required = false, defaultValue = "2") String usersPerPage,
                                       @RequestParam(required = true, defaultValue = "1") String pageNumber,
                                       @RequestParam(required = false, defaultValue = "username") String sort){

        return ResponseEntity.ok(userService.findSorted(usersPerPage, pageNumber, sort));
    }

    @PostMapping("/adduser")
    public ResponseEntity addUser(@RequestBody(required = true) User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }


}
