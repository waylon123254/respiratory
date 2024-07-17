package com.example.kiara.com.demos.core.Service;

import com.example.kiara.com.demos.Resful.Result;
import com.example.kiara.com.demos.core.Entity.FileEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author www.javacoder.top
 * @since 2024-07-17
 */
public interface FileService extends IService<FileEntity> {
    Result<String> saveFile(MultipartFile file) throws IOException, NoSuchAlgorithmException;


    Result<Resource> downloadFile(Integer id);
}
