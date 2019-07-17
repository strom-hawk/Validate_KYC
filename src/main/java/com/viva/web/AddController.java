package com.viva.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class AddController {
	
	HashMap<String, String> map = new HashMap<>(); 
	
     @Autowired
     @RequestMapping(value= {"/","login"})
	public String displayLogin()
	{
		return "Login";
	}
     
     @RequestMapping(value="add", method=RequestMethod.POST)
 	public String displayUserDashboard(@RequestParam("txtname") String uname,@RequestParam("txtpaswd") String pass) 
    {
    	 map.put("kanika","1234");
    	 map.put("suman","1234");
    	 map.put("vikas","1234");
    	 map.put("khushboo","1234");
    	 if(uname.equals("superadmin") && pass.equals("Com@123"))
    	 {
    		 return "adminDashboard";
    	 }else {
    		 if (map.containsKey(uname) && (map.get(uname)).equals(pass))  
    	        { 
    			 return "userDashboard"; 
    	            
    	        }
    		 else
    			 return "Home";
    	 }
    	 
    	 
    	 
 			
 	}
	

}
