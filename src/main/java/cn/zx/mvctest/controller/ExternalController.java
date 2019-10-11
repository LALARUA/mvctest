package cn.zx.mvctest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExternalController {

    @GetMapping("/diFirewalls")
    public List<String> getAllDiFirewallList(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        System.out.println(session.getId());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));

        String url = "http://localhost:8081/di/di/external_getAllDiFirewalls.htm";

        ResponseEntity<HttpServletResponse> response = restTemplate.getForEntity(url, HttpServletResponse.class);


        return new ArrayList<String>(){{
            add("123");
            add("456");
        }};
    }
}
