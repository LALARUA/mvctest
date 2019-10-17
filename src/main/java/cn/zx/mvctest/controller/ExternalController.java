package cn.zx.mvctest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
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
import java.util.concurrent.Executors;

@RestController
public class ExternalController {
    private static RestTemplate restTemplate = new RestTemplate();
    static {

        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter){
                ((StringHttpMessageConverter)
                        httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
                break;
            }
        }

    }

    @GetMapping("/diFirewalls")
    public List<String> getAllDiFirewallList(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        System.out.println(session.getId());
        String url = "http://localhost:8081/di/di/external_getAllDiFirewalls.htm";

//        String result = restTemplate.getForObject(url, String.class);
        ResponseEntity<HttpServletResponse> forEntity = restTemplate.getForEntity(url, HttpServletResponse.class);
//        System.out.println(result);


        return new ArrayList<String>(){{
            add("123");
            add("456");
        }};
    }
}
