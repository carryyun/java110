package bitcamp.java110.cms.control.student;

import java.util.Scanner;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.DuplicationDaoException;
import bitcamp.java110.cms.dao.MandatoryValueDaoException;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

@Component
public class StudentAddController {
    
    StudentDao studentDao;
    
    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @RequestMapping("student/add")
    public void add(Scanner keyIn) {
        while (true) {
            Student m = new Student();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            System.out.print("최종학력? ");
            m.setSchool(keyIn.nextLine());
            
            System.out.print("재직여부?(true/false) ");
            m.setWorking(Boolean.parseBoolean(keyIn.nextLine()));
            
            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());
            
            try {
                studentDao.insert(m);
                System.out.println("저장하였습니다.");
            }catch(MandatoryValueDaoException ex) {
                System.out.println("필수 값 누락 오류!");
            }catch(DuplicationDaoException ex) {
                System.out.println("이메일 중복 오류");
            }
            
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
}
