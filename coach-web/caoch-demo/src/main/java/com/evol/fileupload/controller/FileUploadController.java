package com.evol.fileupload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class FileUploadController{

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    //单文件上传
    @PostMapping(value ="/upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) throws Exception {
        if (file == null || file.isEmpty()) {
            return "文件为空";
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("文件名称："+fileName);  //打印文件上传名称
        System.out.println("文件描述："+description); //打印文件上传名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String subPath = sdf.format (new Date());
        String basePath = uploadDir +  subPath +"/" + fileName;
        System.out.println("保存文件路径："+basePath);
        File dest = new File(basePath);
        //检测是否存在目录
        if (!dest.getParentFile().exists()){
            //新建文件夹
            dest.getParentFile().mkdirs();
        }
        //文件保存
        file.transferTo(dest);
        return "SUCCESS";
    }

    //对于多个文件上传来说，只需要在方法中传入MultipartFile[数组即可，或者通过MultipartHttpServletRequest#getFiles("file")方法来获取上传的多个文件。
    //我们直接来看代码示例，相信读者会看到熟悉的代码。
    @PostMapping ("/uploads")
    public String uploads(@RequestParam("files") MultipartFile[] uploadFiles, HttpServletRequest request){
    List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
        //String realPath=request.getSession().getServletContext().getRealPath("/uploadFile/");
        String realPath= uploadDir;
        System.out.println(realPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String subPath = sdf.format (new Date());
        for (MultipartFile uploadFile : uploadFiles){

            File folder = new File(realPath + subPath);
//            if (folder.isDirectory()){
//                //...
//            }
            folder.mkdirs();
            String oldName = uploadFile.getOriginalFilename();
            try{
                uploadFile.transferTo(new File(folder,oldName));
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return "SUCCESS";
    }
}