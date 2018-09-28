package bitcamp.java110.cms.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;

@WebServlet("/manager/list")
public class ManagerListServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
                    throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ManagerDao managerDao = (ManagerDao) this.getServletContext().getAttribute("managerDao");
        List<Manager> list = managerDao.findAll();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>매니저 관리</title>");
        
        out.println("<style>");
        out.println("table, td, th{");
        out.println("border: 1px solid silver;");
        out.println("}");
        out.println("</style>");
        
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>매니저 목록</h1>");
        
        out.println("<p><a href='form.html'>추가</a></p>");
        
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr><th>번호</th><th>이름</th><th>이메일</th><th>직위</th></tr>\n");
        out.println("</thead>");
        out.println("<tbody>");

        for (Manager m : list) {
            out.printf("<tr>");
            out.printf(" <td>%d</td>\n", m.getNo());
            out.printf(" <td><a href='detail?no=%d'>%s</a></td>", m.getNo(), m.getName());
            out.printf(" <td>%s</td>", m.getEmail());
            out.printf(" <td>%s</td>\n",m.getPosition());
            out.printf("</tr>");
        }
        out.println("</tbody>");
        out.println("</body>");
        out.println("</html>");
    }
}






















