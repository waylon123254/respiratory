package cn.hxh.files;

import cn.hxh.files.mapper.UserDao;
import cn.hxh.files.pojo.Files;
import cn.hxh.files.pojo.User;
import cn.hxh.files.service.FilesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@SpringBootTest
class FilesApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FilesService filesService;

    @Test
    void contextLoads() {
        User hxh = userDao.query("hxh", "123");
        System.out.println(hxh);
    }

    @Test
    void test01() {
        List<Files> files = filesService.queryAllByLimit(0, 100000, 3);
        files.forEach(System.out::println);

    }

    @Test
    void test02() throws Exception {
        Files files = filesService.queryById(7);
        String Path = ResourceUtils.getURL("classpath:") + "/static" + files.getPath();
        String decode = URLDecoder.decode(Path);
        System.out.println(decode);
        String s = decode +"\\/"+ files.getNewfilename();
        FileInputStream in = new FileInputStream("\\F:\\IDEA代码库\\files\\target\\test-classes\\static\\upload\\2020-07-23\\20200723123533578b38101d7e4cfca3bbce46ff5ca52f.jpeg");
        System.out.println(in);
    }
}
