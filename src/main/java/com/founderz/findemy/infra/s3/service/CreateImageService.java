package com.founderz.findemy.infra.s3.service;

import com.founderz.findemy.infra.s3.S3Util;
import com.founderz.findemy.infra.s3.response.ImageUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateImageService {

    private final S3Util s3Util;

    @Transactional
    public ImageUrlResponse createImage(MultipartFile multipartFiles) {

        String imageUrl = s3Util.upload(multipartFiles);

        return new ImageUrlResponse(imageUrl);
    }

}
