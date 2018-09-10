package bitcamp.java110.cms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
//애노테이션 유지 정책
// CLASS    : 컴파일 한 후에도 .class 파일에 남겨둔다. 단, 실행할 때는 참조할 수 없다.
// SOURCE   : 컴파일 할 때 제거된다. 즉 .class 파일에 남겨두지 않는다.
// RUNTIME  : 컴파일 한 후에도 .claas 파일에 남겨 둔다.
@Retention(value=RetentionPolicy.CLASS)//검파일 한 후, .class 파일에 남겨둔다.
            //차주 ㅏ
public @interface Component{
    String value() default ""; 

}
