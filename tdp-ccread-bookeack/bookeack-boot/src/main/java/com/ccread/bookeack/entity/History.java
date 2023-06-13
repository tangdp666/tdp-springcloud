package com.ccread.bookeack.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 浏览历史表(History)表实体类
 *
 * @author makejava
 * @since 2023-06-03 15:18:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_history")
public class History  {
    //主键@TableId
    private Long id;

    //小说ID
    private Long novelId;
    //章节ID
    private Long chapterId;
    //创建人
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志：0-未删除；1-已删除
    private Integer delFlag;



}
