package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.EUTreeNodeWithParentId;
import com.taotao.common.utils.TaotaoResult;

public interface ContentCategoryService {
	List<EUTreeNodeWithParentId> getCategoryList(long parentId);
	TaotaoResult insertContentCat(long parentId, String name);
	TaotaoResult deleteContentCat(long parentId, long id);
	TaotaoResult updateContentCat(long id, String name);
}
