package bitcamp.java110.ex07;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener01 implements ServletContextListener{
@Override
public void contextInitialized(ServletContextEvent sce) {
    ServletContextListener.super.contextInitialized(sce);
    
    ServletContext sc = sce.getServletContext();
    
    System.out.printf("%s\n,",sc.getInitParameter("aaa"));
    
}
}
