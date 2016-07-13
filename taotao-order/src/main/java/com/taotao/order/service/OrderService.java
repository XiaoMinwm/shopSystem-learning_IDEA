package com.taotao.order.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by dell on 2016/7/13.
 */
public interface OrderService {
    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
