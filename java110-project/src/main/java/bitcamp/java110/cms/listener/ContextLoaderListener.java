package bitcamp.java110.cms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bitcamp.java110.cms.AppConfig;
import bitcamp.java110.cms.service.impl.AuthServiceImpl;
import bitcamp.java110.cms.service.impl.ManagerServiceImpl;
import bitcamp.java110.cms.service.impl.StudentServiceImpl;
import bitcamp.java110.cms.service.impl.TeacherServiceImpl;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ContextLoaderListener.contextInitialized() 실행!");
        
        ServletContext sc = sce.getServletContext();

        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            
//             서블릿에서 Service를 이용할 수 있도록 ServletContext 보관소에 저장하기
            sc.setAttribute("iocContainer", context);
//            sc.setAttribute("managerService", managerService);
//            sc.setAttribute("studentService", studentService);
//            sc.setAttribute("teacherService", teacherService);
//            sc.setAttribute("authService", authService);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







