package com.sample.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/* DB Layer 접근자
 * JpaRepository<Entity 클래스, PK타입>을 상속하면 기본 CRUD 메소드가 자동 생성
 * Entity 클래스와 함께 위치해야 한다.*/
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
