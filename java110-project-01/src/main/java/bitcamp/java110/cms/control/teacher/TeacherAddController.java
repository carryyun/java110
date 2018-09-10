package bitcamp.java110.cms.control.teacher;

import java.util.Scanner;

import bitcamp.java110.cms.App;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.domain.Teacher;

@Component
public class TeacherAddController {
    
    @RequestMapping("teacher/add")
    public void add(Scanner keyIn) {
        while (true) {
            Teacher m = new Teacher();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());
            
            System.out.print("시급? ");
            m.setPay(Integer.parseInt(keyIn.nextLine()));
            
            System.out.print("강의과목?(예: 자바,C,C++) ");
            m.setSubjects(keyIn.nextLine());
            
            App.teachers.add(m);
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    
    private void init() {
        Teacher s = new Teacher();
        s.setName("a");
        App.teachers.add(s);
        
        s = new Teacher();
        s.setName("b");
        App.teachers.add(s);
        
        s = new Teacher();
        s.setName("c");
        App.teachers.add(s);
        
        s = new Teacher();
        s.setName("d");
        App.teachers.add(s);
        
        s = new Teacher();
        s.setName("e");
        App.teachers.add(s);
    }
    
}
