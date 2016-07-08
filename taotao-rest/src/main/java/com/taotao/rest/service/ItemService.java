package com.taotao.rest.service;

import com.taotao.common.utils.TaotaoResult;

/**
 * Created by XiaoMin on 2016/6/19.
 */
public interface ItemService {
    TaotaoResult getItemBaseInfo(long itemId);
    TaotaoResult getItemDesc(long itemId);

    TaotaoResult getItemParam(long itemId);
}
