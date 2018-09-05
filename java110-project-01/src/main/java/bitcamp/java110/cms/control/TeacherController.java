package bitcamp.java110.cms.control;

import java.util.Scanner;
import bitcamp.java110.cms.dao.TeacherList;
import bitcamp.java110.cms.domain.Teacher;

public class TeacherController {
    public static Scanner keyIn;
    
    public static void serviceTeacherMenu() {
        while(true) {
            System.out.println("강사 관리> ");
            String command = keyIn.nextLine();
            if(command.equals("list")) {
                printTeachers();
            }else if (command.equals("add")) {
                inputTeachers();
            }else if (command.equals("delete")) {
                deleteTeacher();
            }else if (command.equals("detail")) {
                detailTeacher();
            }else if (command.equals("quit")) {
                break;
            }else {
                System.out.println("유효하지 않는 명령입니다.");
            }
        }
    }
    
    private static void printTeachers() {
        int count=0;
        for (int i=0; i<TeacherList.size();i++) {
            if(count++ == TeacherList.size())
                break;
            Teacher s=TeacherList.get(i);
            System.out.printf("%s, %s, %s %s %b %s\n", 
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(),
                    s.getTel(),
                    s.getPay(),
                    s.getSubject());
        }
    }
    private static void inputTeachers() {
        while (true) {
            Teacher s = new Teacher();
            
            System.out.print("이름? ");
            s.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            s.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            s.setPassword(keyIn.nextLine());
            
            System.out.print("전화? ");
            s.setTel(keyIn.nextLine());
            
            System.out.print("시급? ");
            s.setPay(Integer.parseInt(keyIn.nextLine()));
            
            System.out.print("강의과목?(예: 자바,C,C++ ");
            s.setSubject(keyIn.nextLine());
            
            TeacherList.add(s);
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }

    
    private static void deleteTeacher() {
        System.out.print("삭제할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no >= TeacherList.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        TeacherList.remove(no);
        
        System.out.println("삭제하였습니다.");
    }
    private static void detailTeacher() {
        System.out.print("조회할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no >= TeacherList.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        Teacher s = TeacherList.get(no);
        System.out.printf("이름: %s\n",s.getName());
        System.out.printf("이메일: %s\n",s.getEmail());
        System.out.printf("암호: %s\n",s.getPassword());
        System.out.printf("전화: %s\n",s.getTel());
        System.out.printf("급여: %d\n",s.getPay());
        System.out.printf("강의과목: %b\n",s.getSubject());
        
    }
}
