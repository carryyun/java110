package bitcamp.java110.cms.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bitcamp.java110.cms.domain.Student;

public class StudentController implements Controller{
    
    public String name="1";
    
    private List<Student> students=new ArrayList<>();
    
    public StudentController() {
        init();
    }
    
    public void service(Scanner keyIn) {
        while(true) {
            System.out.println("학생 관리> ");
            String command = keyIn.nextLine();
            if(command.equals("list")) {
                printStudents();
            }else if (command.equals("add")) {
                inputStudents(keyIn);
            }else if (command.equals("delete")) {
                deleteStudent(keyIn);
            }else if (command.equals("detail")) {
                detailStudent(keyIn);
            }else if (command.equals("quit")) {
                break;
            }else {
                System.out.println("유효하지 않는 명령입니다.");
            }
        }
    }
    private void inputStudents(Scanner keyIn) {
        while (true) {
            Student s = new Student();
            
            System.out.print("이름? ");
            s.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            s.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            s.setPassword(keyIn.nextLine());
            
            System.out.print("최종학력? ");
            s.setSchool(keyIn.nextLine());
            
            System.out.print("재직여부?(true/false) ");
            s.setWorking(Boolean.parseBoolean(keyIn.nextLine()));
            
            System.out.print("전화? ");
            s.setTel(keyIn.nextLine());
            
            students.add(s);
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    private void deleteStudent(Scanner keyIn) {
        System.out.print("삭제할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no >= students.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        
        students.remove(no);
        System.out.println("삭제하였습니다.");
    }
    private void detailStudent(Scanner keyIn) {
        System.out.print("조회할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no >= students.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        Student s= students.get(no);
        
        System.out.printf("이름: %s\n",s.getName());
        System.out.printf("이메일: %s\n",s.getEmail());
        System.out.printf("암호: %s\n",s.getPassword());
        System.out.printf("최종학력: %s\n",s.getSchool());
        System.out.printf("전화: %s\n",s.getTel());
        System.out.printf("재직여부: %b\n",s.isWorking());
        
    }
    
    private void printStudents() {
        int count=0;
        for (int i=0; i<students.size();i++) {
            if(count++ == students.size())
                break;
            Student s = students.get(i);
            System.out.printf("%d: %s, %s, %s %s %b %s\n", 
                    count-1,
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(),
                    s.getSchool(),
                    s.isWorking(),
                    s.getTel());
        }
    }
    
    //인스턴스블록 생성자가 실행되기전에 먼저실행됨 static 블록과 비슷한역할
    private void init(){
        Student s=new Student();
        s.setName("1");
        students.add(s);
        
        s=new Student();
        s.setName("2");
        students.add(s);
        
        s=new Student();
        s.setName("3");
        students.add(s);
        
        s=new Student();
        s.setName("4");
        students.add(s);
    }

}
