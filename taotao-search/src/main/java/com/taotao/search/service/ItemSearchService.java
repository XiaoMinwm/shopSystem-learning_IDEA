package com.taotao.search.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * Created by XiaoMin on 2016/6/7.
 */
public interface ItemSearchService {
    TaotaoResult importAllItems();
    TaotaoResult importItem(TbItem item);
}
