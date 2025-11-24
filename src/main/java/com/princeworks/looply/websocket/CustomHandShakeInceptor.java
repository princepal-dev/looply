package com.princeworks.looply.websocket;

import com.princeworks.looply.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
public class CustomHandShakeInceptor implements HandshakeInterceptor {
  @Autowired private JwtUtils jwtUtils;

  @Override
  public boolean beforeHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Map<String, Object> attributes)
      throws Exception {
    ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
    List<String> authToken = serverHttpRequest.getHeaders().get("Authorization");
    if (authToken == null) return false;
    if (!authToken.getFirst().startsWith("Bearer "))
        return false;

    String token = authToken.getFirst().substring(7);

    if (!jwtUtils.validateToken(token)) return false;

    String userName = jwtUtils.getUserNameFromJWTToken(token);

    attributes.put("username", userName);
    return true;
  }

  @Override
  public void afterHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Exception exception) {
  }
}
