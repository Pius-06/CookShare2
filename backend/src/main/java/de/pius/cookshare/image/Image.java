package de.pius.cookshare.image;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString() 
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
}