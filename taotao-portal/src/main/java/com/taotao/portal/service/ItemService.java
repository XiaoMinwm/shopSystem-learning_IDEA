package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

/**
 * Created by XiaoMin on 2016/6/19.
 */
public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
}
