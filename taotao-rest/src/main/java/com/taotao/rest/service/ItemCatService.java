package com.taotao.rest.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.pojo.CatResult;



public interface ItemCatService {
	CatResult getItemCatList();
}
