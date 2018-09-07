import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java110.cms.context.ApplicationContext;
import bitcamp.java110.cms.control.Controller;
import bitcamp.java110.cms.control.ManagerController;
import bitcamp.java110.cms.control.StudentController;
import bitcamp.java110.cms.control.TeacherController;

public class App {
    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception{

        HashMap<String, Controller> requestHandlerMapping=new HashMap<>();
        ApplicationContext iocContainer=new ApplicationContext("bitcamp.java110.cms.control");
            
        requestHandlerMapping.put("1", new StudentController()); 
        requestHandlerMapping.put("2", new TeacherController());    
        requestHandlerMapping.put("3", new ManagerController());
        while(true) {
            String menu = promptMenu();
            if(menu.equals("0")) {
                System.out.println("안녕히가세요!");
                break;
            } 
            
            Controller controller = (Controller) iocContainer.getBean(menu);
//            Controller controller = requestHandlerMapping.get(menu);

            if (controller != null) {
                controller.service(keyIn);
            } else {
                System.out.println("해당 메뉴가 없습니다.");
            }
        }
        keyIn.close();
    }

    public static String promptMenu() {
        System.out.println("[메뉴]");
        System.out.println("1.학생 관리");
        System.out.println("2.강사 관리");
        System.out.println("3.매니저 관리");

        System.out.print("메뉴 번호> ");
//        String menu=keyIn.nextLine();
        return keyIn.nextLine();
    }
}