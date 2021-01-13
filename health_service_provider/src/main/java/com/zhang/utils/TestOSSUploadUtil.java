package com.zhang.utils;


import com.zhang.utils.config.OSSConfig;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestOSSUploadUtil {
    public static void main(String[] args) throws IOException {

//        File pdfFile = new File("D:\\Downloads\\1121.jpg");
//        FileInputStream fileInputStream = new FileInputStream(pdfFile);
//        MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
//                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//
//        OSSConfig ossClientConfig = new OSSConfig();
//        OSSUploadUtil.setConfigInfo(ossClientConfig);
//
//        String res = OSSUploadUtil.uploadImageToOSS(multipartFile, "测试00021.jpg");
//        System.out.println(res);

        OSSConfig ossClientConfig = new OSSConfig();
        OSSUploadUtil.setConfigInfo(ossClientConfig);

        String imageUrl = OSSUploadUtil.getImageUrl("测试00021.jpg");

        System.out.println(imageUrl);

    }
}
