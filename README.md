# Looply Backend

Backend for **Looply**, a Telegram-like chat and social web application built with Java and Spring Boot.  
Supports real-time one-to-one and group chat, file sharing, posts, likes, comments, and robust group management.

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **Real-time:** WebSocket
- **Authentication:** JWT (JSON Web Tokens)
- **ORM:** Spring Data JPA + Hibernate
- **Documentation:** Swagger (Springdoc OpenAPI)
- **Utilities:** Lombok, ModelMapper
- **Validation:** Spring Boot Validation

---

## ⚙️ Features

### User Management
- User registration & login with JWT authentication
- Profile update (username, avatar, status)
- Password hashing using BCrypt

### Chat System
- Real-time one-to-one messaging using WebSocket
- Message storage in PostgreSQL
- Seen/unseen message status
- File sharing in chat (up to 10MB)

### Group Chat
- Create and manage groups
- Roles: Owner, Admin, Member
- Invite-only or public groups
- Group messaging via WebSocket
- Admin controls (promote, demote, transfer ownership, remove members)

### Social Feed
- Create posts with optional media
- Like/unlike posts
- Comment on posts
- Paginated feed

### Security & Stability
- JWT authentication with refresh tokens
- Validation for all API requests
- Global exception handling
- Swagger documentation for all APIs



