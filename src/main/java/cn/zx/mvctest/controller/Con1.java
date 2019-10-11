package cn.zx.mvctest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Controller
public class Con1 {

    @ResponseBody
    @GetMapping("/get")
    public Map<String, String> getInfo(HttpServletRequest httpServletRequest, HttpServletResponse response){
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        1,
                1,
                        1L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(1024));
        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(1000);
                ServletOutputStream outputStream = response.getOutputStream();
                response.setContentType("application/octet-stream;charset=ISO8859-1");
                response.setHeader("Content-Disposition", "attachment;filename="+ "qq.xml");
                response.addHeader("Pargam", "no-cache");
                response.addHeader("Cache-Control", "no-cache");
                File file = new File("C:\\Users\\admin\\Desktop\\qq.xml");
                Long length = file.length();
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] b = new byte[length.intValue()];
                fileInputStream.read(b);
                outputStream.write(b);
                outputStream.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return new HashMap<String,String>(){{
            put("name","zx");
            put("age","22");
        }};
    }
}
