package com.princeworks.looply.websocket;

import com.princeworks.looply.service.OnlineUserService;
import com.princeworks.looply.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.security.Principal;

@Slf4j
@Component
public class WebSocketEventListener {
  @Autowired private UserService userService;
  @Autowired private OnlineUserService onlineUserService;

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    Long userId = extractUserId(event.getUser());
    if (userId == null) return;

    if (!onlineUserService.isOnline(userId)) onlineUserService.markOnline(userId);

    log.info("User {} connected", userId);
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    Long userId = extractUserId(event.getUser());
    if (userId == null) return;

    if (onlineUserService.isOnline(userId)) onlineUserService.markOffline(userId);

    log.info("User {} disconnected", userId);
  }

  private Long extractUserId(Principal principal) {
    if (principal == null) return null;
    return userService.findUserIdByUserName(principal.getName());
  }
}
