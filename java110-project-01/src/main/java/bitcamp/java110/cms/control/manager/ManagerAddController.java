package bitcamp.java110.cms.control.manager;

import java.util.Scanner;
import bitcamp.java110.cms.App;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Student;

@Component
public class ManagerAddController {
    
    public ManagerAddController() {
        init();
    }
    @RequestMapping("manager/add")
    public void add(Scanner keyIn) {
        while (true) {
            Manager m = new Manager();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());
            
            System.out.print("직위? ");
            m.setPosition(keyIn.nextLine());
            
            App.managers.add(m);
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    
    private void init() {
        Manager s = new Manager();
        s.setName("a");
        App.managers.add(s);
        
        s = new Manager();
        s.setName("b");
        App.managers.add(s);
        
        s = new Manager();
        s.setName("c");
        App.managers.add(s);
        
        s = new Manager();
        s.setName("d");
        App.managers.add(s);
        
        s = new Manager();
        s.setName("e");
        App.managers.add(s);
    }
    
}
