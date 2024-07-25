package com.example.springboot.Controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Common.Result;
import com.example.springboot.Controller.Login.UserControllerLogin;
import com.example.springboot.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.Service.IUserService;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import com.example.springboot.entity.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @Author lv
 * @Date 2023-10-22
 */
@RestController@ RequestMapping("/user")
public class UserController {


        @Autowired
        private IUserService userService;

        /*** 新增或者更新接口
         *
         * @param user
         * @return
         */
        @PostMapping("/save")
        public Result saveOrUpdate(@RequestBody User user) {
                if(user.getId()==null){
                        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("username", user.getUsername());
                        User existUser = userService.getOne(queryWrapper);
                        if (null!=existUser){
                                return Result.error(405, "用户名"+user.getUsername() + "已存在，请修改");
                        }
                }else{
                        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("username", user.getUsername());
                        User existUser = userService.getOne(queryWrapper);
                        // 检查是否存在具有相同用户名但不同ID的用户（对于更新操作）
                        if (existUser != null && !existUser.getId().equals(user.getId())) {
                                return Result.error(405, user.getUsername() + "用户名已存在，请修改");
                        }
                }

                // 执行保存或更新操作
                boolean success = userService.saveOrUpdate(user);
                if (success) {
                        return Result.success(user);
                } else {
                        return Result.error(400, "失败");
                }
        }

        /** 查询所有数据接口
         *
         * @return
         */
        @GetMapping("/findAll")
        public Result findAll() {
                return Result.success(200,"查询成功",userService.list());
        }


        /***根据id删除接口
         *
         * @param id
         * @return
         */
        @DeleteMapping("/deleteById/{id}")
        public Result deleteById(@PathVariable Integer id) {
                int successCount = 0; // 统计删除成功的条数
                boolean i =userService.removeById(id);
                if (i){
                        return  Result.success(200,"删除成功",successCount);
                }else {
                        return  Result.error(400,"失败");
                }
        }

        /***
         * 根据id批量删除
         * @param idList
         * @return
         *  批量删除接口
         */

        @PostMapping("/deleteBatch")
        public Result deleteBatch(@RequestBody List<Integer> idList) {
                int successCount = 0; // 统计删除成功的条数
                for (Integer id : idList) {
                        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("id", id);
                        List<User> list = userService.list(queryWrapper);
                        if (CollectionUtils.isEmpty(list)) {
                                return Result.error(500, "删除失败");
                        }
                }

                boolean delete = userService.removeByIds(idList);
                if (delete) {
                        successCount = idList.size(); // 设置成功删除的条数为传递的id列表的长度
                        return Result.success(200, "删除成功",successCount);
                } else {
                        return Result.error(405, "删除失败");
                }
        }


//      搜索栏实现代码
        /***根据id查询数据接口
         *
         * @param id
         * @return
         */
        @GetMapping("/index/{id}")
        public User findById(@PathVariable Integer id) {
                return userService.getById(id);
        }
        /***
         * 根据username查询
         *
         */
        @GetMapping("/username/{username}")
        public User findBy(@PathVariable String username) {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("username",username);
                return userService.getOne(queryWrapper);
        }

/**
 * 用户分页接口
 * */
//@PostMapping("/page")
//public Result findPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize,
//                       @RequestParam(value = "username", required = false) String username, @RequestParam(value = "address", required = false) String address,
//                       @RequestParam(value = "email", required = false) String email) {
//        IPage<User> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//       // queryWrapper.orderByDesc("id");
//        /**
//         * and查询
//         * */
//        if(!"".equals(username)){
//                queryWrapper.like("username", username);
//        }
//        if(!"".equals(email)){
//                queryWrapper.like("email",email);
//        }
//        if(!"".equals(address)){
//                queryWrapper.like("address", address);
//        }
//        queryWrapper.orderByAsc("id");
//        return Result.success(userService.page(new Page<>(pageNum,pageSize),queryWrapper));
//}

        /***分页查询数据接口
         *
         * @param pageNum
         * @param pageSize
         * @param username
         * @param address
         * @param email
         * @return
         */
        @PostMapping("/page")
public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "")
        String username, @RequestParam(defaultValue = "") String address, @RequestParam(defaultValue = "") String email
                               //                              @RequestParam(defaultValue = "") String nickname,
                               //                              @RequestParam(defaultValue = "") String phone



) {
        IPage < User > page = new Page < > (pageNum, pageSize);
        QueryWrapper < User > queryWrapper = new QueryWrapper < > ();
        /**
         * and查询
         * */
        if (!"".equals(username)) {
                queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
                queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
                queryWrapper.like("address", address);
        }
        queryWrapper.orderByAsc("id");

        // 执行查询操作
        IPage<User> result = userService.page(page, queryWrapper);

        // 对查询结果进行校验
        if (result.getCurrent() > result.getPages()) {  // 当前页码值大于最大页码值
                page.setCurrent(result.getPages());  // 使用最大页码值作为当前页码值重新查询
                result = userService.page(page, queryWrapper);
        }
        User currentUser = TokenUtil.getCurrentUser();
        if (currentUser != null) {
                System.out.println(currentUser.getNickname());
                return Result.success(result);
        } else {
                // 处理currentUser为空的情况，例如返回默认值或者抛出异常
                // 这里做相应的处理，例如返回空数据或者抛出异常
                return Result.error(401, "用户未登录");
        }
}
        /**
        *导出接口
        **/

        @GetMapping("/export")
        public void export(HttpServletResponse response) throws Exception {
                // 获取用户数据
                List<User> userList = userService.list();

                // 创建ExcelWriter对象，并开启自动关闭流的功能
                ExcelWriter writer = ExcelUtil.getWriter(true);

                // 设置表头
                writer.addHeaderAlias("id", "ID");
                writer.addHeaderAlias("username", "用户名");
                writer.addHeaderAlias("password", "密码");
                writer.addHeaderAlias("phone", "手机号");
                writer.addHeaderAlias("email", "邮箱");
                writer.addHeaderAlias("address", "地址");
                writer.addHeaderAlias("nickname", "昵称");
                writer.addHeaderAlias("avatar", "头像");

                // 写入数据
                writer.write(userList, true);

                // 设置响应头信息
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                String fileName = URLEncoder.encode("用户信息", "ISO-8859-1");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

                // 将ExcelWriter对象写入输出流
                try (ServletOutputStream out = response.getOutputStream()) {
                        writer.flush(out, true);
                }

                // 关闭ExcelWriter对象（无需手动关闭流）
                writer.close();
        }
        /**
         * 导入接口         要用英文
         * **/
        @PostMapping("/import")
        public void importData(@RequestParam("file") MultipartFile file) throws Exception {
/**
 * 英文实现的方法
 * */
//                try (InputStream inputStream = file.getInputStream()) {
//                        ExcelReader reader = ExcelUtil.getReader(inputStream);
//                       List<User> userList = reader.readAll(User.class);
//                        List<User> userList = reader.read(0,1,User.class);
//
//                        userService.saveBatch(userList);
//
//                } catch (IOException e) {
//                        e.printStackTrace();
//
//                } catch (Exception e) {
//                        e.printStackTrace();
//
//                }
                InputStream inputStream = file.getInputStream();
                ExcelReader reader = ExcelUtil.getReader(inputStream);
                List<List<Object>> list = reader.read(1);
                List<User> users = CollUtil.newArrayList();

                for (List<Object> row : list) {
                        User user = new User();
                        user.setId(Integer.parseInt(row.get(0).toString()));
                        user.setUsername(row.get(1).toString());
                        user.setPassword(row.get(2).toString());
                        user.setPhone(row.get(3).toString());
                        user.setEmail(row.get(4).toString());
                        user.setAddress(row.get(5).toString());
                        user.setNickname(row.get(6).toString());  // 修改索引为6
                        user.setAvatar(row.get(6).toString());  // 修改索引为6
                        users.add(user);
                }

                userService.saveBatch(users);
        }

        /***       用户登录接口
         *
         * @param userControllerLogin
         * @return
         */
//        @PostMapping("/login")
//        public Result login(@RequestBody UserControllerLogin userControllerLogin){
//                String username=userControllerLogin.getUsername();
//                String password = userControllerLogin.getPassword();
//                if (StrUtil.isBlankIfStr(username)||StrUtil.isBlankIfStr(password)){
//                        return  Result.error(400,"用户名或密码为空 ，请重新输入");
//                }else{
//                        return userService.login(userControllerLogin);
//                }
//
//        }

        /**    用户注册接口
         *
         * @param userControllerLogin
         * @return
         */
        @PostMapping("/register")
        public Result register(@RequestBody UserControllerLogin userControllerLogin) {
                String username = userControllerLogin.getUsername();
                String password = userControllerLogin.getPassword();
                if (StrUtil.isBlankIfStr(username) || StrUtil.isBlankIfStr(password)) {
                        return Result.error(400, "用户名或密码为空 ，请重新输入");

                }else {
                        return userService.register(userControllerLogin);
                }

        }

}