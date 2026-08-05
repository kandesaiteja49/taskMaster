package com.taskmaster.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Objects;

@Entity
@Table(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    private String fileType;
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Attachment() {}

    public Attachment(UUID id, String fileName, String fileUrl, String fileType, LocalDateTime uploadedAt, Task task, User user) {
        this.id = id;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.uploadedAt = uploadedAt;
        this.task = task;
        this.user = user;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @PrePersist
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }

    public static AttachmentBuilder builder() {
        return new AttachmentBuilder();
    }

    public static class AttachmentBuilder {
        private UUID id;
        private String fileName;
        private String fileUrl;
        private String fileType;
        private LocalDateTime uploadedAt;
        private Task task;
        private User user;

        public AttachmentBuilder id(UUID id) { this.id = id; return this; }
        public AttachmentBuilder fileName(String fileName) { this.fileName = fileName; return this; }
        public AttachmentBuilder fileUrl(String fileUrl) { this.fileUrl = fileUrl; return this; }
        public AttachmentBuilder fileType(String fileType) { this.fileType = fileType; return this; }
        public AttachmentBuilder uploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
        public AttachmentBuilder task(Task task) { this.task = task; return this; }
        public AttachmentBuilder user(User user) { this.user = user; return this; }
        public Attachment build() {
            return new Attachment(id, fileName, fileUrl, fileType, uploadedAt, task, user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment attachment = (Attachment) o;
        return Objects.equals(id, attachment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
