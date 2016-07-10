package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * Created by XiaoMin on 2016/7/9.
 */
public interface UserService {
    TbUser getUserByToken(String token);
}
