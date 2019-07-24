package com.viva.web;

import com.viva.entity.PostResponse;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import com.viva.service.KycService;

import static org.junit.Assert.assertNotNull;

@Controller
public class AddController {

	HashMap<String, String> map = new HashMap<>();
	String userid ="";
	KycService service;


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

		PostResponse response = service.uploadKycDetailsService(type,val,imgFile,userid);

		return "Upload_Kyc_Ack";
	}

	@RequestMapping(value ="viewkyc")
	public String viewKyc(Model model) {

		List mapList;
		mapList = service.viewKycDetailsService();

		model.addAttribute("showtable", 1);
		model.addAttribute("maplist", mapList);


		return "adminDashboard";
	}
}
