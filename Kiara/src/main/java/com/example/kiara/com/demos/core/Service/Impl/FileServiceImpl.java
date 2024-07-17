package com.example.kiara.com.demos.core.Service.Impl;

import com.example.kiara.com.demos.Resful.Result;
import com.example.kiara.com.demos.Resful.ResultCode;
import com.example.kiara.com.demos.core.Entity.FileEntity;
import com.example.kiara.com.demos.core.Mapper.FileMapper;
import com.example.kiara.com.demos.core.Service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author www.javacoder.top
 * @since 2024-07-17
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    @Transactional
    public Result<String> saveFile(MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException {
        // 保存文件到本地
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = multipartFile.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(multipartFile.getInputStream(), filePath);

        // 计算文件MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(multipartFile.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        String md5 = sb.toString();

        // 保存文件信息到数据库
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setTitle(fileName); // 设置标题为文件名
        fileEntity.setKeyword(""); // 关键字可以根据需求设置
        fileEntity.setSize(multipartFile.getSize());
        fileEntity.setImage(""); // 图片路径可以根据需求设置
        fileEntity.setType(multipartFile.getContentType());
        fileEntity.setSummary(""); // 摘要可以根据需求设置
        fileEntity.setDownload(filePath.toString());
        fileEntity.setMd5(md5);
        fileEntity.setTime(new Date());
//        fileEntity.setDeadtime(null); // 可以根据需求设置
//        fileEntity.setPublishtime(new Date());
        fileEntity.setHat("0");
//        fileEntity.setStatus(false);//       fileEntity.setCopy(false);

        this.save(fileEntity);

       return Result.success();
    }

    @Override
    public Result<Resource> downloadFile(Integer id) {
        FileEntity fileEntity = this.getById(id);
        if (fileEntity == null) {
          return  Result.fail();
        }

        Path filePath = Paths.get(fileEntity.getDownload());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return Result.success("File download successful", resource);
            } else {
                return Result.fail();
            }
        } catch (IOException e) {
            return Result.fail();
        }
    }
}