package com.sample.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication /* 스프링부터 자동설정, bean 읽기 자동 설정  / 이 위치부터 설정을 읽어가므로 해당 클래스는 프로젝트 최상단에 위치해야 한다. */
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
