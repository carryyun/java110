package bitcamp.java110.cms.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

@WebServlet("/student/detail")
public class StudentDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {

        
        int no = Integer.parseInt(request.getParameter("no"));
        StudentDao studentDao = (StudentDao) this.getServletContext().getAttribute("studentDao");
        Student student = studentDao.findByNo(no);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>학생 관리</title>");
        out.println("<link rel='stylesheet' type='text/css' href='../css/common.css'>");
        out.println("</head>");
        out.println("<body>");
        
        // 페이지 머리말 포함하기
        RequestDispatcher rd = request.getRequestDispatcher("/header");
        rd.include(request, response);
        
        out.println("<h1>학생 목록</h1>");
        
        if (student == null) {
            out.println("해당 번호의 학생 정보가 없습니다!");
        }else {
            out.println("<table>");
            out.println("<tbody>");
            out.printf("<tr><th>번호</th><td>%d</td></tr>\n", student.getNo());
            out.printf("<tr><th>이름</th><td>%s\n", student.getName());
            out.printf("<tr><th>이메일</th><td>%s\n", student.getEmail());
            out.printf("<tr><th>암호</th><td>%s\n", student.getPassword());
            out.printf("<tr><th>최종학력</th><td>%s\n", student.getSchool());
            out.printf("<tr><th>전화</th><td>%s\n", student.getTel());
            out.printf("<tr><th>재직여부</th><td>%b\n", student.isWorking());
            out.println("</tbody>");
            out.println("</table>");

            out.println("<button type='button' onclick='remove()'>삭제</button>");
        }
        
        
        
        out.println("<script>");
        out.println("function remove() {");
        out.printf("location.href='delete?no=%d'\n", student.getNo());
        out.println("}");
        out.println("</script>");
        
        rd = request.getRequestDispatcher("/footer");
        rd.include(request, response);
        
        out.println("</body>");
        out.println("</html>");
    }

}
