package com.taotao.sso.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by XiaoMin on 2016/6/20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${REDIS_USER_SSO_EXPIRE}")
    private int REDIS_USER_SSO_EXPIRE;
    @Override
    public TaotaoResult checkData(String content, Integer type) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();

        if(1 == type) {
            criteria.andUsernameEqualTo(content);
        } else if ( 2 == type) {
            criteria.andPhoneEqualTo(content);
            //email校验
        } else {
            criteria.andEmailEqualTo(content);
        }
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        if(list == null || list.size() == 0) {
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        tbUserMapper.insert(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult userLogin(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if(null == list || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }

        TbUser user = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, REDIS_USER_SSO_EXPIRE);
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getByToken(String token) {
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        if(StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "此session已经过期，请重新登录");
        }
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, REDIS_USER_SSO_EXPIRE);
        return TaotaoResult.ok(user);
    }
}
