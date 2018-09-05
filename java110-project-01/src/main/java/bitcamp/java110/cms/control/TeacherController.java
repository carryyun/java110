package bitcamp.java110.cms.control;

import java.util.Scanner;
import bitcamp.java110.cms.domain.Member;

public class TeacherController {
    static Teacher[] teachers = new Teacher[100];
    static int teacherIndex = 0; //Teacher
    public static Scanner keyIn;
    
    static class Teacher extends Member{
        protected String tel;
        protected int pay;
        protected String subject;
        public String getTel() {
            return tel;
        }
        public void setTel(String tel) {
            this.tel = tel;
        }
        public int getPay() {
            return pay;
        }
        public void setPay(int pay) {
            this.pay = pay;
        }
        public String getSubject() {
            return subject;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
    
    
    
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
        for (Teacher s : teachers) {
            if(count++ == teacherIndex)
                break;
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
            
            if(teacherIndex==teachers.length) {
                increaseStorage();
            }
            
            teachers[teacherIndex++] = s;
            
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    private static void increaseStorage() {
        Teacher[] newList = new Teacher[teachers.length+3];
        
        for(int i=0; i<teachers.length;i++) {
            teachers[i]=newList[i];
        }
        teachers=newList;
    }
    
    private static void deleteTeacher() {
        System.out.print("삭제할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no >= teacherIndex) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        for (int i = no; i < teacherIndex - 1; i++) {
            teachers[i] = teachers[i + 1];
        }
        teacherIndex--;
        
        System.out.println("삭제하였습니다.");
    }
    private static void detailTeacher() {
        System.out.print("조회할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no >= teacherIndex) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        System.out.printf("이름: %s\n",teachers[no].getName());
        System.out.printf("이메일: %s\n",teachers[no].getEmail());
        System.out.printf("암호: %s\n",teachers[no].getPassword());
        System.out.printf("전화: %s\n",teachers[no].getTel());
        System.out.printf("급여: %d\n",teachers[no].getPay());
        System.out.printf("강의과목: %b\n",teachers[no].getSubject());
        
    }
}
