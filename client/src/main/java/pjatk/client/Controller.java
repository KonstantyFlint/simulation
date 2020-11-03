package pjatk.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    @RequestMapping("")
    Object dummy(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://localhost:22222/trains",Object.class);
    }
}
