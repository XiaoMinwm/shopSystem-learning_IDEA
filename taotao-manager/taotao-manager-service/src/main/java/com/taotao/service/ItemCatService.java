package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;

public interface ItemCatService {
	abstract List<EUTreeNode> getCatList(Long parentId);
}
