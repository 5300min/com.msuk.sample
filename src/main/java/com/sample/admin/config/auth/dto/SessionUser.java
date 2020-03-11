package com.sample.admin.config.auth.dto;

import com.sample.admin.domain.user.User;
import lombok.Getter;

import java.io.Serializable;


/* User클래스를 세션에 저장하려고 하면 직렬화를 구현하지 않았다는 에러가 발생한다.
* User 클래스는 Entity이므로 다른 Entity와 결합이 있을수 있으므로 직렬화를 구현하지 않는다.
* 추후 유지보수, 운영에 용이함.
* */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
