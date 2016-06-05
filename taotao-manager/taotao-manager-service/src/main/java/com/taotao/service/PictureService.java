package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface PictureService {
	 Map uploadPicture(MultipartFile uploadFile);
}
