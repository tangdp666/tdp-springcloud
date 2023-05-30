package com.ccread.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccread.admin.entity.SysOauthClient;
import com.ccread.admin.mapper.SysOauthClientMapper;
import com.ccread.admin.service.ISysOauthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements ISysOauthClientService {

}