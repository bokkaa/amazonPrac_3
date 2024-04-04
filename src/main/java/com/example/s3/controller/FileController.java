package com.example.s3.controller;

import com.example.s3.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/files")
@RequiredArgsConstructor
public class FileController {


    private final AwsS3Service awsS3Service;


    @PostMapping("/uploadFile")
    private ResponseEntity<List<String>> uploadFile(@RequestParam("multipartFileList")List<MultipartFile> multipartFileList){

       return ResponseEntity.status(HttpStatus.CREATED).body(awsS3Service.uploadFile(multipartFileList));
    }
}
