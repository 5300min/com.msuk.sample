package com.sample.admin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /* Json을 반환하는 컨트롤러로 만들어줌 */
public class HelloController {

    @GetMapping("/hello") /* get요청을 받는 api */
    public String hello() {
        return "hello";
    }
}
