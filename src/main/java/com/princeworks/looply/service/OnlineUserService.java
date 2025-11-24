package com.princeworks.looply.service;

public interface OnlineUserService {
    void addUser(String sessionId, Long userId);
    void removeUser(String sessionId);
    Long getUserId(String sessionId);
    int getOnlineUsers();
    Boolean isUserOnline(Long userId);
}
