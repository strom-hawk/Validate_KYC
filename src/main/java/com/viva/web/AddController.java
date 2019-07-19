package com.viva.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class AddController {
	
	HashMap<String, String> map = new HashMap<>(); 
	
     @Autowired
     @RequestMapping(value= {"/","login"})
	public String displayLogin()
	{
		return "Login";
	}
     
     @RequestMapping(value="dashboard", method=RequestMethod.POST)
 	public String displayUserDashboard(@RequestParam("txtname") String uname,@RequestParam("txtpaswd") String pass,Model model) 
    {
    	 map.put("kanika@gmail.com","1234");
    	 map.put("suman@gmail.com","1234");
    	 map.put("vikas@gmail.com","1234");
    	 map.put("khushboo@gmail.com","1234");
    	
    	 if(uname.equals("superadmin") && pass.equals("Com@123"))
    	 {
    		 return "adminDashboard";
    	 }else {
    		 if (map.containsKey(uname) && (map.get(uname)).equals(pass))  
    	        { 
    			 model.addAttribute("uname",uname);
    			 return "userDashboard"; 
    	            
    	        }
    		 else {
    			 model.addAttribute("err1",1);
    			 return "Login";
    		 }
    	 }	 	
 	}
    @RequestMapping(value= "uploaded")
 	public String uploadKyc(@RequestParam("txttype") String type,@RequestParam("val") String val,@RequestParam("img") String img)
 	{
    	
        //img.replace("C:\\fakepath\\", "");
    	System.out.println(img);
    	 File originalFile = new File("D:\\"+img);
         String encodedBase64 = null;
         try {
        	 
             FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
             byte[] bytes = new byte[(int)originalFile.length()];
             System.out.println(bytes.length);
             fileInputStreamReader.read(bytes);
             encodedBase64 = new String(Base64.encodeBase64(bytes));
             System.out.println(encodedBase64);
             
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
 		return "Login";
 	}

}
