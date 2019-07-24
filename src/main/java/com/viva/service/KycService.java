package com.viva.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.viva.entity.Kyc;
import com.viva.entity.PostResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class KycService {

    public PostResponse uploadKycDetailsService(String type, String val, MultipartFile imgFile,String userid) throws IOException {

        Path path1 = Paths.get("C:\\photos1");
        //if directory exists?
        if (!Files.exists(path1)) {
            try {
                Files.createDirectories(path1);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        String folder = "C:/photos1/";
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



        return personResultAsJsonStr;

    }

    public List viewKycDetailsService(){

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
                String uploadPath = "./src/main/webapp/images/decode"+i+".jpg";
                String showPath="/images/decode"+i+".jpg";
                File f = new File(uploadPath);
                ImageIO.write(image, "jpg", f);

                map.put("img",showPath);
                map.put("uid",kycList.get(i).getUid());
                mapList.add(map);

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return mapList;

    }
}

