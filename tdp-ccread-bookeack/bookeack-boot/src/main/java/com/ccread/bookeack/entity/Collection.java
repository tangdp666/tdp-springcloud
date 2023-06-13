package com.ccread.bookeack.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 收藏表(Collection)表实体类
 *
 * @author makejava
 * @since 2023-06-03 15:15:03
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_collection")
public class Collection  {
    //主键@TableId
    private Long id;

    //小说ID
    private Long novelId;
    //状态：0-正常；1-取消
    private Integer status;
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
