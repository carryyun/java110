import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import bitcamp.java110.cms.context.ApplicationContext;
import bitcamp.java110.cms.control.Controller;
import bitcamp.java110.cms.control.ManagerController;
import bitcamp.java110.cms.control.StudentController;
import bitcamp.java110.cms.control.TeacherController;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Student;
import bitcamp.java110.cms.domain.Teacher;

public class App {
    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception{

        HashMap<String, Controller> requestHandlerMapping=new HashMap<>();
        ApplicationContext iocContainer=new ApplicationContext("bitcamp.java110.cms.control");
            

        requestHandlerMapping.put("1", new StudentController(new LinkedList<Student>())); 
        requestHandlerMapping.put("2", new TeacherController(new ArrayList<Teacher>()));    
        requestHandlerMapping.put("3", new ManagerController(new ArrayList<Manager>()));
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

        System.out.print("명령> ");
        String menu=keyIn.nextLine();
        switch(menu){
        case "1":
        case "2":
        case "3":
        case "0":
            return menu;
        default:
            System.out.println("메뉴 번호가 유효하지 않습니다.");
        }

        return "0";
    }

}