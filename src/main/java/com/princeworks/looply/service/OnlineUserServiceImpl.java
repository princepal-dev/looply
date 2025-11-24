package com.princeworks.looply.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class OnlineUserServiceImpl implements OnlineUserService {
  private final ConcurrentHashMap<String, Long> sessionToUserMap = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<Long, Set<String>> userToSessionMap = new ConcurrentHashMap<>();

  @Override
  public void addUser(String sessionId, Long userId) {
    if (!sessionToUserMap.containsKey(sessionId)) {
      sessionToUserMap.put(sessionId, userId);
    }
    if (!userToSessionMap.containsKey(userId)) {
      Set<String> session = ConcurrentHashMap.newKeySet();
      session.add(sessionId);
      userToSessionMap.put(userId, session);
    } else {
      Set<String> session = userToSessionMap.get(userId);
      session.add(sessionId);
    }
  }

  @Override
  public void removeUser(String sessionId) {
    Long userId = sessionToUserMap.get(sessionId);
    sessionToUserMap.remove(sessionId);
    Set<String> userActiveSessions = userToSessionMap.get(userId);
    if (userId != null && userActiveSessions != null) {
      userActiveSessions.remove(sessionId);
      if (userActiveSessions.isEmpty()) userToSessionMap.remove(userId);
    }
  }

  @Override
  public Long getUserId(String sessionId) {
    return sessionToUserMap.get(sessionId);
  }

  @Override
  public int getOnlineUsers() {
    return userToSessionMap.size();
  }

  @Override
  public Boolean isUserOnline(Long userId) {
    return userToSessionMap.containsKey(userId) && !userToSessionMap.get(userId).isEmpty();
  }
}
