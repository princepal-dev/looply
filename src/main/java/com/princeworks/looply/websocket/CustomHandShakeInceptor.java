package com.princeworks.looply.websocket;

import com.princeworks.looply.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.Optional;

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
    Optional<String> token = extractToken(serverHttpRequest);
    if (token.isEmpty()) {
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      return false;
    }

    if (!jwtUtils.validateToken(token.get())) return false;
    String userName = jwtUtils.getUserNameFromJWTToken(token.get());
    attributes.put("username", userName);
    return true;
  }

  @Override
  public void afterHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Exception exception) {}

  private Optional<String> extractToken(ServletServerHttpRequest request) {
    String rawHeader = request.getHeaders().getFirst("Authorization");
    if (rawHeader == null || !rawHeader.startsWith("Bearer ")) return Optional.empty();
    return Optional.of(rawHeader.substring(7));
  }
}
