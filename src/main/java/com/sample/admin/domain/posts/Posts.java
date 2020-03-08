package com.sample.admin.domain.posts;

import com.sample.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter /* Entity 클래스에서는 Setter 메소드를 만들지 않는다. / 명확히 변화가 필요하다면 추가한다. */
@NoArgsConstructor /* 기본 생성자 자동 추가 */
@Entity /* 테이블에 링크될 클래스 명시, 카멜케이스로 나타낸다.*/
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder /* 기본 생성자보다 더 명확하게 어떤 필드에 어떤값을 채워야 할지, 빌더패턴을 통해 명확하게 인식하기 위함 */
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /* JPA 영속성 컨텍스트 : 엔티티를 영구 저장하는 환경
     * JPA EntityManager 활성화 된 상태로 트랜잭션안에서 데이터를 가져오면 영속성 컨텍스트가 유지된 상태.
     * 이 상태에서 해당  데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영.
     * 즉, Entity객체 값만 변경하면 별도로 update를 날릴 필요가 없다. (=더티 체킹 =Dirty Checking)
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
