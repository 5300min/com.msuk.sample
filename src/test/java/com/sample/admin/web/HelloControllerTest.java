package com.sample.admin.web;

import com.sample.admin.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class) /* Junit 실행자가 아닌 다른 실행자를 실행 / 여기서는 Springrunner 라는 실행자 사용함 */
@WebMvcTest(controllers = HelloController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest { /* Web(Spring mvc)에 집중할 수 있는 어노테이션 / 시큐리티 설정은 동작하나 customOauth2UserService는 스캔하지 못함. / @Controller는 읽으나 @Service, @Repository 등은 못읽음. */

    @Autowired /* 스프링이 관리하는 Bean을 주입 받는다. */
    private MockMvc mvc; /* 웹 api 를 테스트할때 사용 / get, post 등 api 테스트 */

    @WithMockUser(roles = "USER")
    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) /* MockMvc를 통해 get요청 / 체이닝 지원 */
                .andExpect(status().isOk()) /* perform의 결과를 검증, OK인지 (http header 200인지?) */
                .andExpect(content().string(hello)); /* perform의 결과를 검증, 응답 본문이 'hello'인지? */
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) /* api 테스트시 요청 파라미터 설정 , String만 허용 */
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) /* $ 기준으로 필드명 명 */
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
