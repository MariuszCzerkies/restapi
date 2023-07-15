package com.example.restapi.external.service;

import org.springframework.stereotype.Service;

@Service
//@Scope("singleton") -> to jest domyślnie ustawione
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HelloService {
    public String hello() {
        return "Hello World!";
    }
}
