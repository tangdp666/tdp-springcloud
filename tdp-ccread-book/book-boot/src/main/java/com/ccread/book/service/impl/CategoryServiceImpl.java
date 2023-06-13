package com.ccread.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.book.entity.Category;
import com.ccread.book.mapper.CategoryMapper;
import com.ccread.book.service.CategoryService;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.result.R;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.CategoryListVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-05-31 16:50:24
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public R<List> getCategoryList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getDelFlag, SystemConstants.IS_NODELFLAG);
        List<Category> categoryList = list(queryWrapper);
        List<CategoryListVo> categoryListVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryListVo.class);
        return R.ok(categoryListVoList);
    }
}
