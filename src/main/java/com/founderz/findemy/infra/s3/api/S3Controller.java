package com.founderz.findemy.infra.s3.api;

import com.founderz.findemy.infra.s3.dto.response.ImageUrlResponse;
import com.founderz.findemy.infra.s3.service.CreateImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final CreateImageService createImageService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/image")
    public ImageUrlResponse createImage(@RequestPart(value = "image", required = false) MultipartFile multipartFiles) {
        return createImageService.createImage(multipartFiles);
    }
}
