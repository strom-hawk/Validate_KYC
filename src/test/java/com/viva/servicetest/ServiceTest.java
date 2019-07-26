package com.viva.servicetest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.viva.entity.PostResponse;
import com.viva.service.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	@Autowired
	KycService service;
	@Test
	//For Upload KYC correct AAdhar card number
	public void shouldUploadKycWithCorrectDetails() throws IOException {
		
	

		 	File file = new File("C:\\Users\\comviva\\Pictures\\1001.jpg");
		    DiskFileItem fileItem = new DiskFileItem("file", "image/jpg", false, file.getName(), (int) file.length() , file.getParentFile());
		    fileItem.getOutputStream();
		    MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
	
		
		PostResponse response = service.uploadKycDetailsService("aadhar", "123456789012",multipartFile , "bill@gmail.com");
		assertEquals("success", response.getStatus());
	}
	@Test
	//For Upload KYC wrong aadhar card number
	//Sends 1 for incorrect details
	public void checkKycWithWrongDetails() throws IOException {
		
		int result = service.checkKycDetailsService("aadhar", "123459", "bill@gmail.com",10);
		assertEquals(1,result);
	}
	@Test
	//For Upload KYC wrong aadhar card number
	//Sends 0 for correct details
	public void checkKycWithCorrectDetails() throws IOException {
		
		int result = service.checkKycDetailsService("aadhar", "123459987098", "bill@gmail.com",10);
		assertEquals(1,result);
	}
	
	@Test
	public void viewKycDetailsServiceTest() {
		List maplist= service.viewKycDetailsService();
		if (maplist.size()==0) {
			return;
		}
		Map<String,String> map = new HashMap<String,String>();
		
		for (int i=0;i<maplist.size();i++) {
			map=(Map<String, String>) maplist.get(i);
			assertNotNull(map.get("type"));
			assertNotNull(map.get("idno"));
			assertNotNull(map.get("img"));
			assertNotNull(map.get("uid"));
		}
	}
	
}
