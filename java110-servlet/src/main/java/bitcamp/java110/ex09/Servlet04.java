/* 포워드(forward)
 * => 다른 서블릭으로 작업을 위임하는 기술
 *    이전 서블릿에서 출력한 내용이 있다면 포워드 전에 버린다
 *    그래서 작업을 위임 받은 서블릿에서 새로 출력한다.
 */
package bitcamp.java110.ex09;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex09/servlet04")
public class Servlet04 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {
        
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>ex09</title>");
        out.println("</head>");
        out.println("<body>");
        
        // 다른 서블릿의 실행을 포함한다.
        // => 다른 서블릿으로 실행을 위임한 후 그 서블릿으 실해잉 끝난 후 다시 리턴된다.
        // => forward()를 하면 이전 서블릿에서 버퍼로 출력한 내용이 제거되지만,
        //    include()를 하면 이전 서블릿의 출력 내용이 유지된다.
        //    그래서 여러 서블릿의 출력 내용이 합쳐진다.
        RequestDispatcher rd = req.getRequestDispatcher("servlet05");
        rd.include(req, res);
        
        out.println("<h1>Servlet04</h1>");
        
        rd = req.getRequestDispatcher("servlet06");
        rd.include(req, res);
        
        out.println("</body>");
        out.println("</html>");
    }
}


















