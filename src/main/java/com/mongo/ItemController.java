package com.mongo;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;


	@GetMapping(path = "/allblogs")
	public @ResponseBody List<Item> getAllBlog() {
		return itemRepository.findAll();
	}
    
    @PostMapping("/postblog")
    public ResponseEntity<String>postBlog (@RequestParam("image")MultipartFile imageFile,@RequestParam String title
    		,@RequestParam String name,@RequestParam String email,@RequestParam String blog)
    
    {
        try {
            Item image = new Item();
            image.setTitle(title);
            image.setName(name);
            image.setEmail(email);
            image.setBlog(blog);
            image.setFileName(imageFile.getOriginalFilename());
            image.setData(imageFile.getBytes());
            
            itemRepository.save(image);
            ClassPathResource resource = new ClassPathResource("templates/index.html");
            InputStream inputStream = resource.getInputStream();
            String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
          
             
   
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload image");
        }
    }
}
