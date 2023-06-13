package com.ccread.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.book.entity.Category;
import com.ccread.common.result.R;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-05-31 16:50:24
 */
public interface CategoryService extends IService<Category> {

    R<List> getCategoryList();
}
