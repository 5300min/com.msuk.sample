package com.sample.admin.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest /* H2 데이터베이스를 자동으로 실행함. */
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After /* 단위테스트가 끝날때마다 수행되는 메소드 / 배포 전 전체테스트시 테스트간 침범을 막기 위해 사용 / 여러 테스트진행시 데이터가 꼬여 실패 가능함. */
    public void cleanUp () {
        postsRepository.deleteAll();
    }

    @Test
    public void board_read() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        /* insert, update 쿼리를 실행, id 유무에 따라 나누어짐 (아래는 id가 없으므로 update) */
        postsRepository.save(Posts.builder()
                                .title(title)
                                .content(content)
                                .author("5300min@gmail.com")
                                .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); /* 모든 데이터 조회 */

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_insert() {
        // given
        LocalDateTime now = LocalDateTime.of(2020,3,8,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>> createDate="+posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }
}
