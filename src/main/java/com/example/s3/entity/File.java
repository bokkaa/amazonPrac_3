package com.example.s3.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileURLs;

    private String fileId;
    @CreatedDate
    private LocalDateTime createDate;

    @Builder
    public File(Long id, String fileURLs, String fileId, LocalDateTime createDate) {
        this.id = id;
        this.fileURLs = fileURLs;
        this.fileId = fileId;
        this.createDate = createDate;
    }
}
