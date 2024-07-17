package com.example.kiara.com.demos.core.Controller;


import com.example.kiara.com.demos.Resful.Result;
import com.example.kiara.com.demos.Resful.ResultCode;
import com.example.kiara.com.demos.core.Entity.FileEntity;
import com.example.kiara.com.demos.core.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www.javacoder.top
 * @since 2024-07-17
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 单文件上传
     *
     * @param file
     * */

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            return fileService.saveFile(file);
        } catch (IOException | NoSuchAlgorithmException e) {
           return Result.fail();
        }
    }

    /**
     * 多文件上传
     *
     * @param files
     * */



    /**
     * 文件下载
     *
     * @param id 文件ID
     * @return ResponseEntity<Resource>
     */
    /**
     * 文件下载
     *文件ID
     * @param
     * @return Result<Resource> 包含文件资源的 Result 对象
     */
    @GetMapping("/download")
    public Result<Resource> downloadFile(HttpServletResponse response, @RequestParam("fileName") String fileName) {

}


