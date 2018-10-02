// JSP 사용 전 - 로그인 폼 출력하기
// => 개발자가 직접 HTML 출력 코드를 작성해야 한다.
//
// JSP
// => 개발자를 대신하여 서블릿 클래스를 정의하고
//    자바 출력 코드르 작성한다.
// => 구동원리
//    hello.jsp ===> [JSP엔진] ===> hello_jsp.java 생성
//    - 생성된 자바 클래스는 HttpServlet 클래스의 하위 클래스이다.
//    - 클래스이름은 JSP 엔진에 따라 다를 수 있따.

package bitcamp.java110.cms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/footer")
public class FooterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        out.println("<footer>");
        out.println("    <p>&copy;자바110기</p>");
        out.println("</footer>");
    }
}














