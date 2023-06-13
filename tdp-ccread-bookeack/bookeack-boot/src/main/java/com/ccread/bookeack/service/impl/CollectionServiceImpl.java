package com.ccread.bookeack.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.bookeack.entity.Collection;
import com.ccread.bookeack.manager.BookFeignManager;
import com.ccread.bookeack.mapper.CollectionMapper;
import com.ccread.bookeack.service.CollectionService;
import com.ccread.common.constant.SystemConstants;
import com.ccread.common.dto.NovelInfoListDto;
import com.ccread.common.redis.utils.RedisUtils;
import com.ccread.common.result.R;
import com.ccread.common.utils.BeanCopyUtils;
import com.ccread.common.vo.CollectionListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 收藏表(Collection)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 15:15:04
 */
@Service("collectionService")
@RequiredArgsConstructor
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    private final RedisUtils redisUtils;

    private final BookFeignManager bookFeignManager;

    @Override
    public R<List<CollectionListVo>> getCollectionList() {
        LambdaQueryWrapper<Collection>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getDelFlag, SystemConstants.IS_NODELFLAG);
        queryWrapper.eq(Collection::getStatus,SystemConstants.COLLECTION_ISOK);
        queryWrapper.eq(Collection::getCreateBy,redisUtils.get("userid"));
        queryWrapper.orderByDesc(Collection::getCreateTime);

        List<Collection> collections = list(queryWrapper);

        List<Long> novelIds = collections.stream().map(Collection::getNovelId).distinct().collect(Collectors.toList());
        List<NovelInfoListDto> novelInfoListDtos = bookFeignManager.getNovelInfoList(novelIds);

        Map<Long, NovelInfoListDto> novelInfoListDtoMap = novelInfoListDtos.stream()
                .collect(Collectors.toMap(NovelInfoListDto::getId, Function.identity()));


        List<CollectionListVo> collectionListVos = BeanCopyUtils.copyBeanList(collections, CollectionListVo.class);
        collectionListVos.stream()
                .map(collectionListVo -> {
                    String novelName = novelInfoListDtoMap.get(collectionListVo.getNovelId()).getNovelName();
                    collectionListVo.setNovelName(novelName);
                    return collectionListVo;
                })
                .map(collectionListVo -> {
                    String cover = novelInfoListDtoMap.get(collectionListVo.getNovelId()).getCover();
                    collectionListVo.setCover(cover);
                    return collectionListVo;
                })
                .collect(Collectors.toList());



        return R.ok(collectionListVos);
    }

    @Override
    public R<Boolean> getCollectionFalg(Long novelId) {
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getNovelId,novelId);
        queryWrapper.eq(Collection::getCreateBy,redisUtils.get("userid"));
        queryWrapper.eq(Collection::getStatus,SystemConstants.COLLECTION_ISOK);

        Collection collection = getOne(queryWrapper);
        if (Objects.nonNull(collection)){
            return R.ok(true);
        }
        return R.ok(false);
    }

    @Override
    public R addCollection(Long novelId) {
        Integer userid = (Integer) redisUtils.get("userid");
        LambdaQueryWrapper<Collection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collection::getNovelId,novelId);
        queryWrapper.eq(Collection::getCreateBy,redisUtils.get("userid"));

        Collection collection = getOne(queryWrapper);

        if (Objects.nonNull(collection)){
            collection.setStatus(SystemConstants.COLLECTION_ISOK);
            saveOrUpdate(collection);
        }else {
            Collection collection1 = new Collection();
            collection1.setNovelId(novelId);
            collection1.setStatus(SystemConstants.COLLECTION_ISOK);
            collection1.setCreateBy(userid.longValue());
            collection1.setCreateTime(new Date());
            collection1.setUpdateBy(userid.longValue());
            collection1.setUpdateTime(new Date());
            save(collection1);
        }

        return R.ok();
    }

    @Override
    public R upCollection(Long novelId) {
        LambdaUpdateWrapper<Collection> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Collection::getNovelId,novelId);
        updateWrapper.eq(Collection::getCreateBy,redisUtils.get("userid"));
        updateWrapper.set(Collection::getStatus,SystemConstants.COLLECTION_ISNULL);

        update(updateWrapper);
        return R.ok();
    }
}
