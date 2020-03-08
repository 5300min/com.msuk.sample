package com.sample.admin.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass /* JPA Entity 클래스가 해당클래스를 상속할경우 아래 필드를 칼럼으로 인식하도록 한다. */
@EntityListeners(AuditingEntityListener.class) /* 해당클래스에 Auditing 기능 포함 */
public abstract class BaseTimeEntity {

    @CreatedDate /* Entity가 생성되어 저장될때 시간이 자동으로 저장된다. */
    private LocalDateTime createDate;

    @LastModifiedDate /* 조회한 Entity의 값을 변경할때 시간이 자동으로 저장된다. */
    private LocalDateTime modifiedDate;

}
