package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FtpTest {
	@Test
	public void testFtpClient() throws Exception {
		//FtpUtil ftpUtil = new FtpUtil();
		FileInputStream inputStream = new FileInputStream(new File("U:\\git\\pictureService\\test.jpg"));
		FtpUtil.uploadFile("192.168.0.121", 21, "uftp", "878455", "/home/uftp/www/image", "2016/4/30", "hello2.jpg", inputStream);
		/*FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.0.121", 21);
		ftpClient.login("uftp", "878455");
		
		
		ftpClient.changeWorkingDirectory("/home/uftp/www/image");
		//ftpClient.setBufferSize(1024*1024);
		ftpClient.setControlEncoding("utf-8"); 
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
		ftpClient.storeFile("hello.jpg", inputStream);
		
		ftpClient.logout();*/
	}
}
