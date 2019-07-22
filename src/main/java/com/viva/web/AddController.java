package com.viva.web;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viva.entity.PostResponse;

import org.json.simple.JSONObject; 

@Controller
public class AddController {

	HashMap<String, String> map = new HashMap<>();
	String userid ="";

	@Autowired
	@RequestMapping(value = { "/", "login" })
	public String displayLogin() {
		return "Login";
	}
   
	@RequestMapping(value = "dashboard", method = RequestMethod.POST)
	public String displayUserDashboard(@RequestParam("txtname") String uname, @RequestParam("txtpaswd") String pass,
			Model model) {
		map.put("kanika@gmail.com", "1234");
		map.put("suman@gmail.com", "1234");
		map.put("vikas@gmail.com", "1234");
		map.put("khushboo@gmail.com", "1234");

		if (uname.equals("superadmin") && pass.equals("Com@123")) {
			userid = uname;
			return "adminDashboard";
		} else {
			if (map.containsKey(uname) && (map.get(uname)).equals(pass)) {
				userid = uname;
				model.addAttribute("uname", uname);
				return "userDashboard";

			} else {
				model.addAttribute("err1", 1);
				return "Login";
			}
		}
	}

	@RequestMapping(value = "uploaded")
	public String uploadKyc(@RequestParam("txttype") String type, @RequestParam("val") String val,
							@RequestParam("img") MultipartFile imgFile) throws IOException {

		String folder = "/photos/";
		byte[] bytes = imgFile.getBytes();
		Path path = Paths.get(folder + imgFile.getOriginalFilename());
		Files.write(path, bytes);

		String base64imageString = "";
		File file = new File(folder + imgFile.getOriginalFilename());
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes1 = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes1);
		base64imageString = new String(Base64.encodeBase64(bytes));
		System.out.println(base64imageString);
		fileInputStreamReader.close();

		 String createPostUrl = "http://localhost:8080/kyc/add";
		 RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 JSONObject personJsonObject = new JSONObject();
		 personJsonObject.put("type", type);
		 personJsonObject.put("idno", val);
		 personJsonObject.put("img", base64imageString);
		 personJsonObject.put("uid", userid);
		    
		// final ObjectMapper objectMapper = new ObjectMapper();
		 HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		     
		 PostResponse personResultAsJsonStr = restTemplate.postForObject(createPostUrl, request,PostResponse.class);
		   
		 assertNotNull(personResultAsJsonStr);
		 assertNotNull(personResultAsJsonStr.getStatus());
		 System.out.println(personResultAsJsonStr.getStatus());
		 
		 

		return "Login";
	}

}
