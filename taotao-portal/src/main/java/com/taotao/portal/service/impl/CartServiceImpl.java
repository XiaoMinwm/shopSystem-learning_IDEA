package com.taotao.portal.service.impl;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMin on 2016/7/10.
 */
@Service
public class CartServiceImpl implements CartService {
    /**
     * 添加购物车商品
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Override
    public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem = null;
        List<CartItem> itemList = getCartItemList(request);
        for(CartItem cItem : itemList) {
            if(cItem.getId() == itemId) {
                cItem.setNum(cItem.getNum() + num);
                cartItem = cItem;
                break;
            }
        }
        if(cartItem == null) {
            cartItem = new CartItem();
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
            if(result.getStatus() == 200) {
                TbItem item = (TbItem) result.getData();
                cartItem.setNum(item.getNum());
                cartItem.setId(item.getId());
                cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
                cartItem.setPrice(item.getPrice());
                cartItem.setTitle(item.getTitle());
            }
            itemList.add(cartItem);
        }
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }

    @Override
    public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> list = getCartItemList(request);
        for(CartItem item : list) {
            if(item.getId() == itemId) {
                list.remove(item);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
        return TaotaoResult.ok();
    }

    /**
     * 删除购物车商品
     * <p>Title: deleteCartItem</p>
     * <p>Description: </p>
     * @param itemId
     * @return
     * @see com.taotao.portal.service.CartService#deleteCartItem(long)
     */


    /**
     * 从cookie中取商品列表
     */
    private List<CartItem> getCartItemList(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if(cartJson == null) {
            return new ArrayList<>();
        }
        try {
            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
