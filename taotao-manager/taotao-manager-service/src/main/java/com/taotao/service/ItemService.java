package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemByid(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	TaotaoResult createItem (TbItem item, String desc, String itemParam) throws Exception;
	TaotaoResult updateSolr(TbItem item);
}
