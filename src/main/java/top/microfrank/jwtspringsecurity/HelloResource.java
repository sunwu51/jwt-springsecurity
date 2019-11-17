package top.microfrank.jwtspringsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Frank
 * @date 2019/11/17 11:28
 */
@Controller
public class HelloResource {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

}
