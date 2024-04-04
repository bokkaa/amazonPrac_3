package com.example.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.s3.dto.FileDto;
import com.example.s3.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    private final FileRepository fileRepository;
    private final AmazonS3 amazonS3;


    @Transactional
    public List<String> uploadFile(List<MultipartFile> multipartFileList){

        List<String> fileURLs = new ArrayList<>();

        multipartFileList.forEach(files -> {
            String fileName = convertFileName(files.getOriginalFilename());
            String EachURL = "https://" + bucket +".s3." +region +".amazonaws.com/" +fileName;

            ObjectMetadata objectMetadata =new ObjectMetadata();
            objectMetadata.setContentLength(files.getSize());
            objectMetadata.setContentType(files.getContentType());

            try(InputStream inputStream = files.getInputStream()){

                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
                fileRepository.save(new FileDto(UUID.randomUUID().toString(), EachURL).toEntity());
                fileURLs.add(EachURL);

            }catch (IOException e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 실패");
            }

        });


        return fileURLs;
    }


    private String convertFileName(String fileName){
        return UUID.randomUUID().toString().concat(getExtension(fileName));
    }

    private String getExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));

        }catch (StringIndexOutOfBoundsException e){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "지원하지 않는 파일 형식");
        }
    }

}
