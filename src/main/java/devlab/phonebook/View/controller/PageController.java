package devlab.phonebook.View.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@PropertySource("classpath:sample.properties")
public class PageController {

    @Value("${links.swagger}")
    private String swaggerLinkTitle;

    @GetMapping("/")
    public String homePage(Model model, HttpServletRequest request,  @RequestParam(value = "email", required = false) String email) {
        model.addAttribute("swaggerLinkTitle", swaggerLinkTitle);
        model.addAttribute("servlet", getHeadersInfo(request));
        model.addAttribute("email", email);

        return "index";
    }

    @GetMapping("/result")
    public String resultPage( @RequestParam(value = "email", required = false) String email) {

        return "redirect:/?email=" + email;
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {

            String key = headerNames.nextElement().toString();
            String value = request.getHeader(key);
            map.put(key, value);
        }

//        System.out.println(" * * * * * * * * * * * *");
//        map.forEach((k, v) -> System.out.println(k + ": " + v));
//        System.out.println(" * * * * * * * * * * * *");

        return map;

    }


}

//todo - uzupełnić controller o powyższe.


