package com.viva.servicetest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;


import com.viva.entity.PostResponse;
import com.viva.service.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
@RunWith(SpringRunner.class)
@SpringBootTest

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceTest {

	@Autowired
	KycService service;
	@Test
//For Upload KYC correct AAdhar card number
	public void AshouldUploadKycWithCorrectDetails() throws IOException {



		File file1 = new File("C:\\photos1\\1001.jpg");
		System.out.println("FIle "+file1.getName()+"Length "+file1.length());
		FileInputStream input = new FileInputStream(file1);
		MultipartFile multipartFile = new MockMultipartFile("file",
				file1.getName(), "text/plain", IOUtils.toByteArray(input));

		PostResponse response = service.uploadKycDetailsService("Aadhar", "123456789012",multipartFile , "bill@gmail.com");
		assertEquals("success", response.getStatus());
	}
	@Test
//For Upload KYC wrong aadhar card number
//Sends 0 for incorrect details
	public void BcheckKycWithWrongDetails() throws IOException {
		int result = service.checkKycDetailsService("Aadhar", "123459", "bill@gmail.com",10);
		assertEquals(0,result);
	}
	@Test
//For Upload KYC wrong aadhar card number
//Sends 1 for correct details
	public void CcheckKycWithCorrectDetails() throws IOException {
		int result = service.checkKycDetailsService("Aadhar", "123459987098", "bill@gmail.com",10);
		assertEquals(1,result);
	}
	@Test
	public void DviewKycDetailsServiceTest() {
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

