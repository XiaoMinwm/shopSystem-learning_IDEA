package com.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ShowItemParamService;
@Service
public class ShowItemParamServiceImpl implements ShowItemParamService {
	
	@Autowired
	private TbItemParamItemMapper itemParamMapper;
	@Override
	public String getItemParamByItemId(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list == null || list.size() == 0) {
			return "";
		}
		
		TbItemParamItem itemParams = list.get(0);
		String paramData = itemParams.getParamData();
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("  <tbody>\n");
		for(Map m:jsonList) {
			
			sb.append("    <tr>\n");
			sb.append("      <th class=\"tdTitle\" colspan=\"2\">"+m.get("group")+"</th>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			List<Map> list2 = (List<Map>) m.get("params");
			for(Map m2:list2) {
			sb.append("      <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
			sb.append("      <td>"+m2.get("v")+"</td>\n");
			sb.append("    </tr>\n");
			
			}
		}
		sb.append("  </tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

}
