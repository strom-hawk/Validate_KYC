package com.viva.web;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.viva.entity.Kyc;
import com.viva.entity.PostResponse;
import jdk.nashorn.internal.ir.ObjectNode;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@Controller
public class AddController {

	HashMap<String, String> map = new HashMap<>();
	String userid ="";


	@RequestMapping(value = {"/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		model.addAttribute("user", getPrincipal());
		userid = getPrincipal();
		return "userDashboard";
	}

	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "adminDashboard";
	}

	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String loginPage() {
		return "Login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}


	@RequestMapping(value = {"/user_ACK"}, method = RequestMethod.POST)
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
        System.out.println();
		String createPostUrl = "http://localhost:8080/kyc/add";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("type", type);
		personJsonObject.put("idno", val);
		personJsonObject.put("img", base64imageString);
		personJsonObject.put("uid", userid);

		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);

		PostResponse personResultAsJsonStr = restTemplate.postForObject(createPostUrl, request, PostResponse.class);

		assertNotNull(personResultAsJsonStr);
		assertNotNull(personResultAsJsonStr.getStatus());
		System.out.println(personResultAsJsonStr.getStatus());

		return "Upload_Kyc_Ack";
	}

	@RequestMapping(value ="viewkyc")
	public String viewKyc(Model model) {

		RestTemplate restTemplate = new RestTemplate();
		String createGetUrl = "http://localhost:8080/kyc/view";
		JsonNode response = restTemplate.getForObject(createGetUrl, JsonNode.class);
		System.out.println(response);


		ObjectMapper mapper = new ObjectMapper();

		ObjectReader reader = mapper.readerFor(new TypeReference<List<Kyc>>() {
		});
		List  mapList = new ArrayList<>();
		try {
			List<Kyc> kycList = reader.readValue(response);

			for(int i=0; i< kycList.size();i++) {

				Map<String,String> map = new HashMap<String,String>();

				map.put("type",kycList.get(i).getType());
				map.put("idno",kycList.get(i).getIdno());

				BASE64Decoder decoder = new BASE64Decoder();
				byte[] decodedBytes = decoder.decodeBuffer(kycList.get(i).getImg());
				System.out.println("Decoded upload data : " + decodedBytes.length);

				BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
				System.out.println(image);
				String uploadPath = "./src/main/resources/decode"+i+".jpg";
				File f = new File(uploadPath);

				ImageIO.write(image, "jpg", f);

				map.put("img",uploadPath);
				map.put("uid",kycList.get(i).getUid());
				mapList.add(map);

			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}



		model.addAttribute("showtable", 1);
		model.addAttribute("maplist", mapList);


		return "adminDashboard";
	}
}
