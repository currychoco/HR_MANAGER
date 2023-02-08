package site.currychoco.hrmanager.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //어노테이션을 작성할 곳
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 지속 시간 설정
@Documented // Java doc에 문서화 여부 결정
public @interface CheckAuthority {

    String authCode(); // 파라미터 멤버
}
