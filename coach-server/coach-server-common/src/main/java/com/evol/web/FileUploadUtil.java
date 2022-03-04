package com.evol.web;

import com.evol.enums.ApiResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileUploadUtil {

    /**
     * 保存文件到本地目录
     * @param files 文件
     * @param dir 本地物理目录
     * @return
     */
    public static List<String> SaveFile(MultipartFile[] files, String dir){
        if(StringUtils.isBlank(dir) || files == null){ return null;}
        List<String> filenames = new ArrayList<String>();
        for(int i=0;i<  files.length;i++){
            MultipartFile mulFile = files[i];
            String fileName = mulFile.getOriginalFilename();
            Path path = Paths.get(dir, fileName);
            File dest = new File(path.toString());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                mulFile.transferTo(dest);
                filenames.add(mulFile.getName());
            } catch (Exception ex) {
                log.error("save file fail", ex);
            }
        }
        return filenames;
    }
}
