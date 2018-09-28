package bitcamp.java110.cms.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StudentDao studentDao = (StudentDao) this.getServletContext().getAttribute("studentDao");
        List<Student> list = studentDao.findAll();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>학생 관리</title>");
        
        out.println("<style>");
        out.println("table, td, th{");
        out.println("border: 1px solid silver;");
        out.println("}");
        out.println("</style>");
        
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>학생 목록</h1>");
        
        out.println("<p><a href='form.html'>추가</a></p>");
        
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr><th>번호</th><th>이름</th><th>이메일</th><th>학교</th><th>재직여부</th></tr>\n");
        out.println("</thead>");
        out.println("<tbody>");
        
        for (Student s : list) {
            out.printf("<tr>");
            out.printf(" <td>%d</td>\n", s.getNo());
            out.printf(" <td><a href='detail?no=%d'>%s</a></td>", s.getNo(), s.getName());
            out.printf(" <td>%s</td>", s.getEmail());
            out.printf(" <td>%s</td>\n",s.getSchool());
            out.printf(" <td>%b</td>\n",s.isWorking());
            out.printf("</tr>");
        }
        out.println("</tbody>");
        out.println("</body>");
        out.println("</html>");
    }
}
