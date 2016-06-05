package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REDIS_BASE_URL}")
	private String REDIS_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	@Override
	public EUDataGridResult getContentList(long categoryId, int pages, int rows) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(pages, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbContent> pageInfo =new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}
	@Override
	public TaotaoResult insertContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		try {
			HttpClientUtil.doGet(REDIS_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult updateContent(TbContent content) {
		TbContent preContent = contentMapper.selectByPrimaryKey(content.getId());
		content.setCreated(preContent.getCreated());
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKey(content);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteContent(long id) {
		contentMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

}
