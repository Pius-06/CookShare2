package de.pius.cookshare.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String path; // recipes/uuid.jpg

    @Column(nullable = false, length = 50)
    private String originalName; // kuchen.jpg

    @Column(nullable = false, length = 50)
    private String contentType; // image/jpeg

    @Column(nullable = false)
    private long size;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Image() {
    }

    public Image(
            Long id,
            String path,
            String originalName,
            String contentType,
            long size,
            LocalDateTime createdAt) {
        this.id = id;
        this.path = path;
        this.originalName = originalName;
        this.contentType = contentType;
        this.size = size;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}