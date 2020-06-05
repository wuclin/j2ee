package com.jlu.jtee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class uploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(uploadController.class);


    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,Map<String, Object> map,
    HttpSession session ) {
        if (file.isEmpty()) {
            map.put("msg","上传失败，请选择文件");
            return "import";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D://project/jtee/src/main/resources/resources/exam/";
        fileName = UUID.randomUUID() + fileName;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            dest.toString();
            LOGGER.info("上传成功");
            map.put("msg","上传成功");
            session.setAttribute("fileName",dest);
            return "import";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        map.put("msg","上传失败！");
        return "import";
    }


}
