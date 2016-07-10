package com.taotao.portal.service.impl;

import com.taotao.common.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XiaoMin on 2016/7/10.
 */
public interface CartService {
    TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
}
