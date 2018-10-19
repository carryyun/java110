package ex08;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

public class MyBeanPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization() 실행");
        System.out.println("==>" + bean.toString());

        Class<?> clazz = bean.getClass();
        Method[] methods=clazz.getMethods();
        ApplicationContext iocContatiner=null;
        
        for(Method m : methods) {
            Autowired anno = m.getAnnotation(Autowired.class);
            if(anno == null ) continue;
            
            Class<?> paramType = m.getParameterTypes()[0];
            Object paramObj = iocContatiner.getBean(paramType);
            
            if(paramObj==null) continue;
            try {
                m.invoke(bean, paramObj);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessAfterInitialization() 실행");
        System.out.println("==>" + bean.toString());
        return bean;
    }
}
