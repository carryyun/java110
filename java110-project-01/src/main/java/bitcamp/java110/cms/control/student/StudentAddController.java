package bitcamp.java110.cms.control.student;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;
import bitcamp.java110.cms.server.Request;
import bitcamp.java110.cms.server.Response;

@Component
public class StudentAddController {

    StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @RequestMapping("student/add")
    public void add(Request request, Response response) {
        Student m = new Student();
        m.setName(request.getParameter("name"));
        m.setEmail(request.getParameter("email"));
        m.setPassword(request.getParameter("password"));
        m.setSchool(request.getParameter("school"));
        
        String yn= request.getParameter("working");
        if (yn.equals("y"))
            yn="true";
        else
            yn="false";
        m.setWorking(Boolean.parseBoolean(yn));
        m.setTel(request.getParameter("tel"));

        studentDao.insert(m);
        
        PrintWriter out= response.getWriter();
        out.println("저장하였습니다.");

    }
}
