package org.com.dx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ApiController {
	@RequestMapping("/api")
    public String redisApiHome() {
        return "redirect:/swagger-ui.html";
    }
    @GetMapping({"/_isok"})
    @ResponseBody
    public String isOk() {
        return "ok";
    }
}
