package com.example.springboot.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Common.Result;
import com.example.springboot.Mapper.FileMapper;
import com.example.springboot.entity.FilesClass;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @javax.annotation.Resource
    private FileMapper fileMapper;

@PostMapping("/upload")
public String uploadFile(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
        return "No file uploaded";
    }

    try {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 确保文件上传路径存在
        Path uploadPath = Paths.get(fileUploadPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // 如果路径不存在，创建路径
        }

        // 检查文件是否已存在
        String md5 = SecureUtil.md5(file.getInputStream());// 计算文件的 MD5 值
        String fileUrl = getFileByMd5(md5);
        if (fileUrl != null) {
            return "{\"url\":\"" + fileUrl + "\"}"; // 文件已存在，返回旧文件的URL
        }

        // 生成唯一的文件名
        String uid = IdUtil.fastSimpleUUID();
        String newFileName = uid + "-" + originalFilename;
        String fileUid = uid + StrUtil.DOT + type;
        String url = "http://localhost:9090/file/" + URLEncoder.encode(fileUid, "UTF-8");

        // 保存文件到指定路径
        Path dest = uploadPath.resolve(new String(newFileName.getBytes("UTF-8"), "ISO8859-1"));
        file.transferTo(dest);
        String filePath = dest.toString();
        System.out.println("File Path: " + filePath);
        System.out.println("File MD5:" + md5);

        // 保存文件信息到数据库
        FilesClass saveFile = new FilesClass();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5); // 保存文件的 MD5 值
        fileMapper.insert(saveFile);

        // 返回文件上传成功的信息
        System.out.println("File uploaded successfully");
        return "{\"url\":\"" + url + "\"}";
    } catch (IOException e) {
        e.printStackTrace();
        // 返回文件上传失败的信息
        return "Failed to upload file";
    }
}
    @GetMapping("/{fileuid}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileuid, HttpServletResponse response) throws IOException {
        File uploadFile = new File(fileUploadPath + fileuid);

        if (!uploadFile.exists() || !uploadFile.isFile()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + uploadFile.getName());
        InputStream inputStream = new FileInputStream(uploadFile);
        ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(inputStream));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(uploadFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
//    private String getFileByMd5(String md5){
//        QueryWrapper<FilesClass> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("md5", md5);
//        List<FilesClass> filesClassList = fileMapper.selectList(queryWrapper);
//        if (filesClassList.isEmpty()) {
//            return null;  // 如果不存在相同MD5值的文件，则返回空
//        }
//        // 这里可以根据业务需求，返回文件的相关信息或者直接返回文件路径等
//        return filesClassList.get(0).getUrl();  // 假设返回文件的URL
//    }
private String getFileByMd5(String md5){
    QueryWrapper<FilesClass> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("md5", md5);
    List<FilesClass> filesClassList = fileMapper.selectList(queryWrapper);
    if (filesClassList.isEmpty()) {
        return null;  // 如果不存在相同MD5值的文件，则返回空
    }
    // 这里可以根据业务需求，返回文件的相关信息或者直接返回文件路径等
    return filesClassList.get(0).getUrl();  // 假设返回文件的URL
}

/**
 * 文件接口
 * */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name)
    {
        try {
            QueryWrapper<FilesClass> queryWrapper = new QueryWrapper<>();
            // 未删除的记录
            queryWrapper.eq("is_delete", false);
            queryWrapper.orderByDesc("id");
            if (!"".equals(name)) {
                queryWrapper.like("name", name);
            }

            IPage<FilesClass> page = fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
            return Result.success(page);
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
            return Result.error(500, "Failed to retrieve data from database.");
        }
    }

    // 根据id删除接口
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        FilesClass filesClass =fileMapper.selectById(id);
        filesClass.setIsDelete(true);
        return Result.success(fileMapper.updateById(filesClass));
    }
    // 批量删除接口
    @PostMapping("delete/deletebatch")
    public Result deleteBatch(@RequestBody List < Integer > ids) {
        QueryWrapper<FilesClass> queryWrapper =new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List <FilesClass> filesClasses =fileMapper.selectList(queryWrapper);
        for (FilesClass file:filesClasses){
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        int rowsAffected = fileMapper.updateBatchById(filesClasses);
        return Result.success(rowsAffected + " 条数据删除成功");
    }
    @PostMapping("/update")
    public  Result save (@RequestBody FilesClass filesClass){
        return  Result.success(fileMapper.updateById(filesClass));
    }

}

// 根据uid查询文件信息

//        FilesClass file = fileMapper.selectById(uid);
//        if (file != null) {
//            try {
//                Path filePath = Paths.get(fileUploadPath).resolve(uid + "-" + file.getName());
//                Resource resource = new UrlResource(filePath.toUri());
//                if (resource.exists() || resource.isReadable()) {
//                    return ResponseEntity.ok()
//                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
//                            .body(resource);
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        }
//        return ResponseEntity.notFound().build();


//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return "No file uploaded";
//        }
//
//        try {
//            String originalFilename = file.getOriginalFilename();
//            String type = FileUtil.extName(originalFilename);
//            long size = file.getSize();
//
//            // 确保文件上传路径存在
//            Path uploadPath = Paths.get(fileUploadPath);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath); // 如果路径不存在，创建路径
//            }
//
//            // 生成唯一的文件名
//            String uid = IdUtil.fastSimpleUUID();
//            String newFileName = uid + "-" + originalFilename;
//            String fileUid = uid + StrUtil.DOT + type;
//            String url = "http://localhost:9090/file/" + fileUid;
//            String md5 = SecureUtil.md5(file.getInputStream());// 计算文件的 MD5 值
//
//            // 保存文件到指定路径
//            Path dest = uploadPath.resolve(newFileName);
//            file.transferTo(dest);
//            String filePath = dest.toString();
//            System.out.println("File Path: " + filePath);
//            System.out.println("File MD5:"+md5);
//
//            // 根据 MD5 值查询文件
//            String fileUrl = getFileByMd5(md5);
//
//            if (fileUrl == null) { // 如果不存在相同MD5值的文件，才进行文件信息的保存操作
//                // 保存文件信息到数据库
//                FilesClass saveFile = new FilesClass();
//                saveFile.setName(originalFilename);
//                saveFile.setType(type);
//                saveFile.setSize(size / 1024);
//                saveFile.setUrl(url);
//                saveFile.setMd5(md5); // 保存文件的 MD5 值
//                fileMapper.insert(saveFile);
//            } else {
//                url = fileUrl; // 更新url为已存在文件的URL
//            }
//
//            // 返回文件上传成功的信息
//            System.out.println("File uploaded successfully");
//            return "{\"url\":\"" + url + "\"}";
//        } catch (IOException e) {
//            e.printStackTrace();
//            // 返回文件上传失败的信息
//            return "Failed to upload file";
//        }
//    }