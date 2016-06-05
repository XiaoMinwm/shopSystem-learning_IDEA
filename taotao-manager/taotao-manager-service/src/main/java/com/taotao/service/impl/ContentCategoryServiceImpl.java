package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.EUTreeNodeWithParentId;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EUTreeNodeWithParentId> getCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNodeWithParentId> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNodeWithParentId node = new EUTreeNodeWithParentId();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()? "closed":"open");
			node.setParentId(parentId);
			resultList.add(node);
		}
		
		return resultList;
	}
	@Override
	public TaotaoResult insertContentCat(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setStatus(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(tbContentCategory);
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok(tbContentCategory);
	}
	@Override
	public TaotaoResult deleteContentCat(long parentId, long id) {
		contentCategoryMapper.deleteByPrimaryKey(id);
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> parentCatList = contentCategoryMapper.selectByExample(example);
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(parentCatList == null || parentCatList.size() == 0) {
			parentCat.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult updateContentCat(long id, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok();
	}

}
