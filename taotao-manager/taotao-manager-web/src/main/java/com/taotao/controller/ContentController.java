package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
		EUDataGridResult contentList = contentService.getContentList(categoryId, page, rows);
		return contentList;
	}
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult insertContent = contentService.insertContent(content);
		return insertContent;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult updateContent(TbContent content) {
		TaotaoResult updateContent = contentService.updateContent(content);
		return updateContent;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(Long ids) {
		TaotaoResult deleteContent = contentService.deleteContent(ids);
		return deleteContent;
	}
	
}
