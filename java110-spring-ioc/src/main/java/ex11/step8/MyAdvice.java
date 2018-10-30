package ex11.step8;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 원래 메서드에 넘어갈 파라미터 값과
// 원래 메서드가 리턴하는 값을 알아내기
//

@Component
@Aspect
public class MyAdvice {

    @Before("execution(* ex11.step8.ServiceImpl.addPhoto(..)) and args(f)")
    public void before(String f) {
        System.out.println("MyAdvice.before(): " + f);
    }
    
    @AfterReturning(pointcut="execution(* ex11.step8.ServiceImpl.addPhoto(..))",
                returning="rt")
    public void afterReturning(Object rt) {
        System.out.println("MyAdvice.afterReturning(): " + rt.toString());
    }
    
    @AfterThrowing(pointcut="execution(* ex11.step8.ServiceImpl.addPhoto(..))",
            throwing="err")
    public void afterThrowing(Exception err) {
        System.out.println("MyAdvice.afterThrowing(): " + err.getMessage());
    }
    
    
}
