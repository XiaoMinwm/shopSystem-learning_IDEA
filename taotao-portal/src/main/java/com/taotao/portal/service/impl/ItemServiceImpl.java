package com.taotao.portal.service.impl;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by XiaoMin on 2016/6/19.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public ItemInfo getItemById(Long itemId) {
        try {
            //调用rest的服务查询商品基本信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if (taotaoResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo)taotaoResult.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 取商品描述
     * @param itemId
     * @return
     */
    @Override
    public String getItemDescById(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
            if(taotaoResult.getStatus() == 200) {
                TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
                String result = itemDesc.getItemDesc();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);

            //把json转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                String paramData = itemParamItem.getParamData();
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("<tbody>\n");
                for (Map m1 : jsonList) {
                    sb.append("<tr><th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th></tr><tr>\n");
                    List<Map> subList = (List<Map>) m1.get("params");
                    for (Map m2 : subList) {
                        sb.append("</tr><tr><td class=\"tdTitle\">" + m2.get("k") + "</td><td>" + m2.get("v") + "</td></tr>\n");
                    }
                }
                sb.append("</tbody>\n" + "</table>");
                return sb.toString();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
