package com.example.s3.dto;

import com.example.s3.entity.File;
import lombok.Data;

@Data
public class FileDto {

    private String fileId;
    private String fileURLs;

    public FileDto(String fileId, String fileURLs) {
        this.fileId = fileId;
        this.fileURLs = fileURLs;
    }

    public File toEntity(){
        return File.builder()
                .fileId(fileId)
                .fileURLs(fileURLs)
                .build();
    }
}
