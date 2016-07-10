package com.taotao.portal.service.impl;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by XiaoMin on 2016/7/9.
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    private String SSO_PAGE_LOGIN;

    public String getSSO_BASE_URL() {
        return SSO_BASE_URL;
    }

    public String getSSO_PAGE_LOGIN() {
        return SSO_PAGE_LOGIN;
    }

    @Override
    public TbUser getUserByToken(String token) {
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
            if(result.getStatus() == 200) {
                TbUser user = (TbUser) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
