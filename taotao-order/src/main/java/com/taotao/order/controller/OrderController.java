package com.taotao.order.controller;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dell on 2016/7/13.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody Order order) {
        TaotaoResult result = null;
        try {
            result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }
}
