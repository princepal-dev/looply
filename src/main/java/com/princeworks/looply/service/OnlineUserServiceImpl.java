package com.princeworks.looply.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class OnlineUserServiceImpl implements OnlineUserService {
  private final Set<Long> onlineUsers = ConcurrentHashMap.newKeySet();

  @Override
  public void markOnline(Long userId) {
      onlineUsers.add(userId);
  }

  @Override
  public void markOffline(Long userId) {
      onlineUsers.remove(userId);
  }

  @Override
  public boolean isOnline(Long userId) {
    return onlineUsers.contains(userId);
  }

  @Override
  public int countOnlineUsers() {
    return onlineUsers.size();
  }

  @Override
  public Set<Long> getAllOnlineUsers() {
    return Set.copyOf(onlineUsers);
  }
}
