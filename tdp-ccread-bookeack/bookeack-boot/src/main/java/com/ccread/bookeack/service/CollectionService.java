package com.ccread.bookeack.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccread.bookeack.entity.Collection;
import com.ccread.common.result.R;
import com.ccread.common.vo.CollectionListVo;

import java.util.List;


/**
 * 收藏表(Collection)表服务接口
 *
 * @author makejava
 * @since 2023-06-03 15:15:03
 */
public interface CollectionService extends IService<Collection> {

    R<List<CollectionListVo>> getCollectionList();

    R<Boolean> getCollectionFalg(Long novelId);

    R addCollection(Long novelId);

    R upCollection(Long novelId);
}
