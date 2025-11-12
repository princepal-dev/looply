    package com.princeworks.looply.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import java.time.LocalDateTime;
    import java.util.List;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "messages")
    public class Message {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long messageId;

      @ManyToOne
      @JoinColumn(name = "sender_id", nullable = false)
      private User sender;

      @ManyToOne
      @JoinColumn(name = "receiver_id")
      private User receiver;

      @ManyToOne
      @JoinColumn(name = "group_id")
      private Group group;

      @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
      private List<MessageAttachment> attachments;

      @NotBlank private String message;

      @CreationTimestamp
      @Column(nullable = false, updatable = false)
      private LocalDateTime createdAt;

      @UpdateTimestamp
      @Column(nullable = false)
      private LocalDateTime updatedAt;
    }
