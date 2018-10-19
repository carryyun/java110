// 의존 객체 자동 주입 : @Autowired의 위치
// => setter나 field에 붙이면 된다.
// => field에 붙일 때는 setter가 없어도 된다.
// => private 필드에 대해서도 값을 주입할 수 있다.
//    - private은 외부의 접근을 막는 기능을 하고 있는데,
//     _스프링 IoC 컨테이너에서 private 접근을 막는 것을 우회하여
//     _값을 넣기 때문에 객체지향을 깨뜨린다는 논란이 있다.

package ex08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03 {

    public static void main(String[] args) {
        
        ApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext("ex08/app-context-2.xml");
        
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
        
        Car c1 = (Car)iocContainer.getBean("c1");
        System.out.println(c1);
        
    }
}









