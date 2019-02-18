package devlab.phonebook.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@PropertySource("classpath:sample.properties")
@RequestMapping("/api")
public class SampleController {

    @Value("${app.title}")
    String attr;

    @GetMapping("/sample")
    public String sample() {
        return attr;
    }

}
