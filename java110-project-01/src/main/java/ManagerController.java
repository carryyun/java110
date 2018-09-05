import java.util.Scanner;

public class ManagerController {
    static Scanner keyIn;
    
    static class Manager extends Member{
        protected String tel;
        protected String position;
        public String getTel() {
            return tel;
        }
        public void setTel(String tel) {
            this.tel = tel;
        }
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }
        
    }
    
    static Manager[] managers=new Manager[100];
    static int managerIndex = 0;
    

    static void serviceManagerMenu() {
        while(true) {
            System.out.println("매니저 관리> ");
            String command = keyIn.nextLine();
            if(command.equals("list")) {
                printManagers();
            }else if (command.equals("add")) {
                inputManagers();
            }else if (command.equals("quit")) {
                break;
            }else {
                System.out.println("유효하지 않는 명령입니다.");
            }
        }
    }
    
    static void printManagers() {
        int count=0;
        for (Manager s : managers) {
            if(count++ == managerIndex)
                break;
            System.out.printf("%s, %s, %s %s %b %s\n", 
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(),
                    s.getTel(),
                    s.getPosition());
        }
    }
    static void inputManagers() {
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
            
            managers[managerIndex++] = s;
            
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
}
