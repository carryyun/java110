package bitcamp.java110.cms.control.manager;

import java.util.Scanner;

import bitcamp.java110.cms.App;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.domain.Manager;

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
            
            if(App.managerDao.insert(m)>0)
                System.out.println("저장하였습니다");
            else
                System.out.println("같은 이메일의 학생이 존재합니다.");
            
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    
    private void init() {
        Manager s = new Manager();
        s.setName("a");
        s.setEmail("a@test.com");
        App.managerDao.insert(s);
        
        s = new Manager();
        s.setName("b");
        s.setEmail("b@test.com");
        App.managerDao.insert(s);
        
        s = new Manager();
        s.setName("c");
        s.setEmail("c@test.com");
        App.managerDao.insert(s);
        
        s = new Manager();
        s.setName("d");
        s.setEmail("d@test.com");
        App.managerDao.insert(s);
        
        s = new Manager();
        s.setName("e");
        s.setEmail("e@test.com");
        App.managerDao.insert(s);
    }
    
}
