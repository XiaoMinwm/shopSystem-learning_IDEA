package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.EUTreeNodeWithParentId;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNodeWithParentId> getContentCatList(@RequestParam(value="id", defaultValue="0") Long parentId) {
		List<EUTreeNodeWithParentId> categoryList = contentCategoryService.getCategoryList(parentId);
		return categoryList;
		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCat(Long parentId, String name) {
		TaotaoResult insertContentCat = contentCategoryService.insertContentCat(parentId, name);
		return insertContentCat;
		
	}
	@RequestMapping("/delete/")
	@ResponseBody
	public TaotaoResult deleteContentCat(Long parentId, Long id) {
		TaotaoResult deleteContentCat = contentCategoryService.deleteContentCat(parentId, id);
		return deleteContentCat;
	}
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCat(long id, String name) {
		TaotaoResult updateContentCat = contentCategoryService.updateContentCat(id, name);
		return updateContentCat;
	}
}
