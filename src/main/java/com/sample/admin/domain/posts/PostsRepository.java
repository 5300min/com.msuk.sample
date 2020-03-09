package com.sample.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* DB Layer 접근자
 * JpaRepository<Entity 클래스, PK타입>을 상속하면 기본 CRUD 메소드가 자동 생성
 * Entity 클래스와 함께 위치해야 한다.*/
/*
 * JOIN 관련 프레임워크 querydsl
 * 1. 타입안정성 / 2. 많이 사용함. / 3. 많은 레퍼런스
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") /* jpa에서 제공하지 않는 메소드의 경우 쿼리로 작성함 */
    List<Posts> findAllDesc();

}
