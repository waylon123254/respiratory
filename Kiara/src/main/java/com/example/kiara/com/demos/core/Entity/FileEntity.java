package com.example.kiara.com.demos.core.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author www.javacoder.top
 * @since 2024-07-17
 */
@Data
@ApiModel("文件信息表")
@TableName("file")
public class FileEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 文件id
     */
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty("文件id")
    private Integer id;

    /**
     * 文件名
     */
    @TableField("file_name")
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 文件标题
     */
    @TableField("title")
    @ApiModelProperty("文件标题")
    private String title;

    /**
     * 文件关键字
     */
    @TableField("keyword")
    @ApiModelProperty("文件关键字")
    private String keyword;

    /**
     * 文件大小
     */
    @TableField("size")
    @ApiModelProperty("文件大小")
    private Long size;

    /**
     * 文件缩略图
     */
    @TableField("image")
    @ApiModelProperty("文件缩略图")
    private String image;

    /**
     * 文件价格
     */
    @TableField("price")
    @ApiModelProperty("文件价格")
    private BigDecimal price;

    /**
     * 文件页码总数
     */
    @TableField("pagecount")
    @ApiModelProperty("文件页码总数")
    private String pagecount;

    /**
     * 文件类型
     */
    @TableField("type")
    @ApiModelProperty("文件类型")
    private String type;

    /**
     * 文件摘要
     */
    @TableField("summary")
    @ApiModelProperty("文件摘要")
    private String summary;

    /**
     * 文件下载链接
     */
    @TableField("download")
    @ApiModelProperty("文件下载链接")
    private String download;

    /**
     * 文件唯一标识
     */
    @TableField("md5")
    @ApiModelProperty("文件唯一标识")
    private String md5;

    @TableField("time")
    @ApiModelProperty("文件上传时间")
    private Date time;

    /**
     * 文件截至时间
     */
    @TableField("deadtime")
    @ApiModelProperty("文件截至时间")
    private Date DeadTime;

    /**
     * 文件发布时间
     */
    @TableField("publishtime")
    @ApiModelProperty("文件发布时间")
    private Date PublishTime;

    /**
     * 文件热度
     */
    @TableField("hat")
    @ApiModelProperty("文件热度")
    private String hat;

    /**
     * 文件状态,0表示未被禁用,1表示被禁用
     */
    @TableField("status")
    @ApiModelProperty("文件状态,0表示未被禁用,1表示被禁用")
    private Integer status;

    /**
     * 是否复制rn0,允许复制rn1,不允许复制
     */
    @TableField("copy")
    @ApiModelProperty("是否复制rn0,允许复制rn1,不允许复制")
    private Integer copy;
}
