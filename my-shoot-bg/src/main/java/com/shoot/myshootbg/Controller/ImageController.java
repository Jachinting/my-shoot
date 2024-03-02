package com.shoot.myshootbg.Controller;

import com.shoot.myshootbg.entity.Restbean;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    
    @Value("${file.upload.path}")
    private String path;
    
    @Value("${file.upload.baseurl}")
    private String base_url;
    
    @ResponseBody
    @PostMapping("/upload")
    public String uploadPicture(@RequestParam("file")MultipartFile file, HttpServletRequest request)throws IOException {
        
        //获取文件在服务器的储存路径
        File filepath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filepath.exists() && !filepath.isDirectory()) {
            System.out.println("目录不存在，创建目录" + filepath);
            filepath.mkdir();
        }
        
        //获取文件的原始名字
        String originalFileName = file.getOriginalFilename();
        System.out.println("文件的原始名称："+originalFileName);
        
        //获取文件类型，以“."为标识符
        String type = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        System.out.println("文件类型：" + type);
        
        //获取文件名字，不包含拓展名
        String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));

        //设置新文件名称
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name +"."+ type;
        System.out.println("新文件名称：" + fileName);

        //指定位置创建文件
        File targetFile = new File(path, fileName);

        //开始上传
        try{
            file.transferTo(targetFile);
            System.out.println("上传成功");
            return Restbean.success(base_url+fileName).asJsonString();
        }catch (IOException e){
            System.out.println("上传失败");
            System.out.println(e.toString());
            return Restbean.failure(400,e.getMessage()).asJsonString();
        }
    }
}
