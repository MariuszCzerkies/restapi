package com.example.restapi.api;

import com.example.restapi.external.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    /******* 3 rodzaje wstrzykiwania:
     * pole -> @Autowired ale HelloService helloService
     * setter -> @Autowired ale settera(public void setHelloService(HelloService helloService){...})
     * konstruktor - @Autowired HelloController(HelloService helloService) {...}, najbardziej zalecane
     ******/

    private final HelloService helloService;

    @GetMapping("/")
    public String hello() {
        return helloService.hello();
    }
}
