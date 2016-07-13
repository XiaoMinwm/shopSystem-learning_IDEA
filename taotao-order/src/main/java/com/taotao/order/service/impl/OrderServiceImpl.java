package com.taotao.order.service.impl;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.impl.JedisClientSingle;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2016/7/13.
 */
@Service
public class OrderServiceImpl implements OrderService {
    //redis中的订单key
    @Value("${ORDER_ID_KEY}")
    private String ORDER_ID_KEY;
    @Value("${ORDER_BEGIN_ID}")
    private Long ORDER_BEGIN_ID;
    @Autowired
    private JedisClientSingle jedisClientSingle;
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        String orderIdStr = jedisClientSingle.get(ORDER_ID_KEY);
        Long orderId = null;
        if(StringUtils.isEmpty(orderIdStr)) {
            jedisClientSingle.set(ORDER_ID_KEY, ORDER_BEGIN_ID.toString());
            orderId = ORDER_BEGIN_ID;
        } else {
            orderId = jedisClientSingle.incr(ORDER_ID_KEY);
        }
        //设置订单号
        order.setOrderId(orderId.toString());
        Date date = new Date();
        //订单创建时间
        order.setCreateTime(date);
        //订单更新时间
        order.setUpdateTime(date);
        //插入订单表
        orderMapper.insert(order);
        //插入订单商品表
        for (TbOrderItem tbOrderItem : itemList) {
            //取订单商品id
            Long orderItemId = jedisClientSingle.incr("ORDER_ITEM_ID");
            tbOrderItem.setId(orderItemId.toString());
            tbOrderItem.setOrderId(orderId.toString());
            //添加到订单商品表
            orderItemMapper.insert(tbOrderItem);
        }
        //插入物流表
        orderShipping.setOrderId(orderId.toString());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId.toString());
    }
}
