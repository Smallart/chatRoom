package com.jxust.we_chat_together.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 工具类：
 * 用于文件的上传
 */
public class ImgUpload {
    public static String savaImage(MultipartFile blFile) throws IOException {
        if (!blFile.isEmpty()){
            String oldFileName=blFile.getOriginalFilename();
            String randomStr=UUIDUtils.getUUID();
            String newFileName=randomStr+oldFileName.substring(oldFileName.lastIndexOf("."));
            File file=new File(imgPath()+newFileName);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            System.out.println(imgPath());
            //将内存中的图片写入文件中
            blFile.transferTo(file);
            return newFileName;
        }
        return null;
    }
    public static String imgPath(){
        return new File("src/main/resources/static/img").getAbsolutePath()+"/";
    }
}
