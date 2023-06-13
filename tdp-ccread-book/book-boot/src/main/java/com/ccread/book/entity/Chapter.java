package com.ccread.book.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 章节表(Chapter)表实体类
 *
 * @author makejava
 * @since 2023-05-30 20:30:22
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_chapter")
public class Chapter  {
    //主键@TableId
    private Long id;
    //章节名
    private String chapterName;
    //文章
    private String article;
    //所属小说ID
    private Long novelId;
    //排序
    private Integer orderNum;
    //创建时间
    private Date createTime;
    //创建人
    private Long createBy;
    //更新时间
    private Date updateTime;
    //更新人
    private Long updateBy;
    //删除标志：0-未删除；1-已删除
    private Integer delFlag;



}
