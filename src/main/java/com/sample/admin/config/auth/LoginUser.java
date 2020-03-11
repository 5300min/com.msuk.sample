package com.sample.admin.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) /* 어노테이션이 생성될수 있는 위치 지정 / PARAMETER의 경우 메소드의 파라미터로 선언된 객체에서만 사용이 가능함 */
@Retention(RetentionPolicy.RUNTIME) /* 컴파일 이후에도 JVM에 의해 계속 참조가 가능 (리플렉션) */
public @interface LoginUser { /* 해당 파일을 어노테이션 클래스로 지정 / 메소드 인자로 세션값을 받을수 있도록 선처리 작업 */
}
