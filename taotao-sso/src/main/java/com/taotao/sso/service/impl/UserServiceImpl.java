package com.taotao.sso.service.impl;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by XiaoMin on 2016/6/20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;
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
}
