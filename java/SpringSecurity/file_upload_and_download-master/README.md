### 基于SpringBoot的文件上传下载


### 1、前端
       技术选型:
            HTML、CSS
       页面:
       1、登陆页面[已完成]
       2、上传页面[已完成]
### 2、数据库设计实现
    表:User(用户表)[已完成]
       Files(文件表) [已完成]
    表结构:User
        create table user(
            id int(11) primary key auto_increment, 用户id
            username varchar(30), 用户名
            password varchar(30), 密码
            
        );   
        create table files(
            id int(11) primary key auto_increment,文件id
            oldFileName varchar(255), 原文件名字
            newFileName varchar(255), 新文件名字
            ext varchar(20), 扩展名
            path varchar(300),路径
            size int(11), 文件大小
            type varchar(120),类型
            isImg varchar(8),是否图片
            downcounts int(6),下载类型
            upload_time datatime, 创建时间
            user_id int(11) 用户id
        )
### 3、后端
    技术选型:
        后端语言:java
        后端框架:springboot、mybatis         
    功能模块:登录功能[已完成]
            文件查询[已完成]
            文件上传[已完成]
            文件下载[已完成]
            删除功能[已完成]
            
###4、技术总结
    该案例十分简单这里就不该赘述。。。 