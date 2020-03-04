package com.sample.admin.web.dto;

import com.sample.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* Entity 클래스는 DB와 맞닿은 핵심클래스로 화면에 노출되는 사소한 기능변경으로 변경되어서는 안된다.
 * Request / Response 용은 이와같이 따로 Dto를 생성한다.
 * Entity 클래스는 조인 등 빈번하게 변경이 잦으므로 Controller에서 쓸 viewLayer 성격의 Dto는 분리하는 것이 좋다. */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
