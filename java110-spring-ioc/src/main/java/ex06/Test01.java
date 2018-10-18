// 커스텀 에디터
// => 스프링 IoC 컨테이너가 객체를 만드릭 위해 생성자를 호출할 때
//    또는 프로퍼티 값을 절정하기 위해 setter를 호출할 때,
//    XML 설정 파일에 지정된 물자열을 해당 파라미터 타입에 맞게 자동으로 형변환한다.
//    단 primitive 타입에 대해서만 자동 형변환한다.
//    예) String ==> byte/short/int/long/float/double/boolean/char
//
// => 그 외 타입에 대해 형변환 하려면 별도의 변환기를 장착해야 한다.
//    그 별도의 값 변환기를 "커스텀 프로퍼티 에디터(custom property editor)"라 부른다.
// 
// 커스텀 프로퍼티 에디터 만들기
// => java.beans.PropertyEditor 인터페이스를 구현한다.
//    - 구현할 메서드가 너무 많기 때문에 보통 직접 구현하기 보다는
//      이 인터페이스를 미리 구현한 클래스(PropertyEditorSupport)를 상속 받아 만든다.
// => java.beans.PropertyEditorSupport 크래스를 상속 받는다
//    -이 클래스를 상속 받아서 setAsText(String) 메서드를 오버라이딩
//    - setAsText() 메서드
package ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

    public static void main(String[] args) {
        
        ApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext("ex06/app-context-1.xml");
        
        System.out.println("------------------------------");
        
        // 컨테이너에 들어 있는 객체의 개수와 이름 알아내기
        int count = iocContainer.getBeanDefinitionCount();
        System.out.printf("bean 개수 = %d\n", count);
        
        String[] names = iocContainer.getBeanDefinitionNames();
        for (String name : names) {
            System.out.printf("=> %s : %s\n", 
                    name, 
                    iocContainer.getType(name).getName());
        }
        
        System.out.println("------------------------------");
        
        Engine e1 = (Engine)iocContainer.getBean("e1");
        System.out.println(e1);
        
        Engine e2 = (Engine)iocContainer.getBean("e2");
        System.out.println(e2);
        
        Engine e3 = (Engine)iocContainer.getBean("e3");
        System.out.println(e3);
        
        // 공장을 통해서 객체를 만들 때는 
        // getBean()을 호출할 때 생성한다.
        Engine e4 = (Engine)iocContainer.getBean("e4");
        System.out.println(e4);
        
        // 공장을 통해서 객체를 생성한 후 
        // 해당 이름으로 저장하면, 
        // 같은 이름에 대해서는 매번 객체를 새로 만들지 않는다.
        // 즉 getBean()은 기존에 생성된 것을 리턴한다.
        Engine e4x = (Engine)iocContainer.getBean("e4");
        System.out.println(e4x);
        
        
        // getBean()을 호출할 때 객체 이름이 다르면 
        // 공장을 통해 새 객체를 만들어 리턴한다.
        Engine e5 = (Engine)iocContainer.getBean("e5");
        System.out.println(e5);
        
        // 공장을 통해 만들어진 객체가 있을 경우에는
        // 다시 만들지 않는다.
        Engine e5x = (Engine)iocContainer.getBean("e5");
        System.out.println(e5x);
        
        // 주의!
        // <bean> 태그에 class 속성만 있다고 해서 
        // class 속성에 지정된 클래스의 객체가 생성되어 보관되는 것은 아니다.
        // 그 클래스가 FactoryBean 인터페이스를 구현한 클래스인 경우
        // 그 객체에 대해 getObject() 리턴 값이 보관되는 것이다.
        //
        // 문제는 설정할 때 태그의 모양이 일반 객체와 같다는 것이다.
        // 예) <bean id="e4" class="ex06.EngineFactory3">
        //      <property name="model" value="H100"/>
        //    </bean>
        // 
        // 그래서 FactoryBean 인터페이스를 구현해서 공장 클래스를 만들 때는
        // 다른 개발자가 헷갈려 하지 않도록 클래스 이름 뒤에 FactoryBean을 
        // 붙이는 것이 좋다.
        // 
        
        Engine e6 = (Engine)iocContainer.getBean("e6");
        System.out.println(e6);
    }

}









