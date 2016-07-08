package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * Created by XiaoMin on 2016/6/19.
 */
public class ItemInfo extends TbItem {
    public String[] getImages() {
        String image = getImage();
        if(image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
