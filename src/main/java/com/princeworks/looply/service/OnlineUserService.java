package com.princeworks.looply.service;

import java.util.Set;

public interface OnlineUserService {
    void markOnline(Long userId);
    void markOffline(Long userId);
    boolean isOnline(Long userId);
    int countOnlineUsers();
    Set<Long> getAllOnlineUsers();
}
