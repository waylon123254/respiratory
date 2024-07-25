package cn.hxh.files.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-07-21 13:43:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {



    private Integer id;
    
    private String username;
    
    private String password;




}