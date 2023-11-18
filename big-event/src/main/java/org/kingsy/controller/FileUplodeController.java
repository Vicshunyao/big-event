package org.kingsy.controller;

import org.kingsy.pojo.Result;
import org.kingsy.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUplodeController {
    @PostMapping("/upload")
    public Result<String> uplode(MultipartFile file) throws Exception {
       String originalFilename= file.getOriginalFilename();
//       保证文件唯一
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("E:\\demo\\files\\"+filename));
     String url=   AliOssUtil.uploadFile(filename,file.getInputStream());
       return Result.success(url);

    }
}
