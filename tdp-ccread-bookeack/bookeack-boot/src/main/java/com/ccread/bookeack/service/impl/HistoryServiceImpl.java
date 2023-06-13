package com.ccread.bookeack.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.bookeack.entity.History;
import com.ccread.bookeack.mapper.HistoryMapper;
import com.ccread.bookeack.service.HistoryService;
import org.springframework.stereotype.Service;

/**
 * 浏览历史表(History)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 15:18:05
 */
@Service("historyService")
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

}
