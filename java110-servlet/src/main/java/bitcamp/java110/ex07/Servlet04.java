/* 서블릿 배치 정보 - 초기 파라미터
 * => 서블릿이 실행하는 동안 사용할 값이 고정값이라면
 *    자바 코드로 그 값을 표현하기 보다는
 *    애노테이션이나 XML 태그로 표현하는 것이 관리하기 편하다.
 * 
 */
package bitcamp.java110.ex07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( 
        value="/ex07/servlet04",
        initParams= {
                @WebInitParam(name="aaa", value="hello"),
                @WebInitParam(name="bbb", value="hello2"),
                @WebInitParam(name="ccc", value="hello3")
        })
public class Servlet04 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        System.out.println("ex06.servlet01.init()...호출됨");
    }
    
    @Override
    public void service(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {
        
        res.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        out.println("/ex07/servlet04 실행!");
        
        // ServletContext 보관소에 저장된 값 꺼내기
        // => 먼저 ServletContext 객체를 알아낸다.
        ServletContext sc = this.getServletContext();
        out.printf("ServletContext: aaa=%s\n",sc.getInitParameter("aaa"));
        
        // => HttpSession의 값 꺼내기
        HttpSession session = req.getSession();
        out.printf("HttpSession: ccc=%s\n",session.getAttribute("ccc"));
    }
}

//완























