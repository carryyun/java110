package bitcamp.java110.cms.control;

import java.util.List;
import java.util.Scanner;

import bitcamp.java110.cms.domain.Manager;

public class ManagerController {
    private List<Manager> managers;
    public Scanner keyIn;
    
    public ManagerController(Scanner keyIn, List<Manager> managers) {
        this.keyIn=keyIn;
        this.managers=managers;
    }

    public void serviceManagerMenu() {
        while(true) {
            System.out.println("매니저 관리> ");
            String command = keyIn.nextLine();
            if(command.equals("list")) {
                printManagers();
            }else if (command.equals("add")) {
                inputManagers();
            }else if (command.equals("delete")) {
                deleteManager();
            }else if (command.equals("detail")) {
                detailManager();
            }else if (command.equals("quit")) {
                break;
            }else {
                System.out.println("유효하지 않는 명령입니다.");
            }
        }
    }
    
    private void printManagers() {
        int count=0;
        for (int i=0;i<managers.size();i++) {
            if(count++ == managers.size())
                break;
            Manager s= managers.get(i);
            System.out.printf("%s, %s, %s %s %b %s\n", 
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(),
                    s.getTel(),
                    s.getPosition());
        }
    }
    private void inputManagers() {
        while (true) {
            Manager s = new Manager();
            
            System.out.print("이름? ");
            s.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            s.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            s.setPassword(keyIn.nextLine());
            
            System.out.print("전화? ");
            s.setTel(keyIn.nextLine());
            
            System.out.print("position? ");
            s.setPosition(keyIn.nextLine());
            
            managers.add(s);
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    
    private void deleteManager() {
        System.out.print("삭제할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        if(no<0 || no >= managers.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        managers.remove(no);
        
        System.out.println("삭제하였습니다.");
        
    }
    private void detailManager() {
        System.out.print("조회할 번호?");
        int no= Integer.parseInt(keyIn.nextLine());
        if(no<0 || no >= managers.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        Manager s= managers.get(no);
        System.out.printf("이름: %s\n",s.getName());
        System.out.printf("이메일: %s\n",s.getEmail());
        System.out.printf("암호: %s\n",s.getPassword());
        System.out.printf("전화: %s\n",s.getTel());
        System.out.printf("Position: %b\n",s.getPosition());
        
    }
}
