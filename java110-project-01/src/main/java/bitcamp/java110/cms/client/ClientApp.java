package bitcamp.java110.cms.client;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bitcamp.java110.cms.context.RequestMappingHandlerMapping;
import bitcamp.java110.cms.context.RequestMappingHandlerMapping.RequestMappingHandler;

public class ClientApp {

    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        // Spring IoC 컨테이너 사용   
        ClassPathXmlApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext("bitcamp/java110/cms/conf/application-context.xml");
        // IoC 컨테이너가 생성한 객체 조회하기
        System.out.println("================================================");
        String[] nameList = iocContainer.getBeanDefinitionNames();
        for(String name : nameList) {
            System.out.println(name);
        }
        System.out.println("================================================");

        RequestMappingHandlerMapping requestHandlerMap = 
                new RequestMappingHandlerMapping();

        // => IoC 컨테이너에 보관된 객체의 이름 목록을 가져온다.
        String[] names = iocContainer.getBeanDefinitionNames();
        for (String name : names) {
            // => 이름으로 객체를 꺼낸다.
            Object obj = iocContainer.getBean(name);

            // => 객체에서 @RequestMapping이 붙은 메서드를 찾아 저장한다.
            requestHandlerMap.addMapping(obj);
        }


        try (         // 서버에 연결하기
                Socket socket = new Socket("localhost", 8888);

                // 서버에 데이터를 보내고 읽을 도구를 준비하기
                // PrintWriter out = new PrintWriter(socket.getOutputStream());
                PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ){
            out.println("HELLO"); out.flush();
            
            System.out.println( in.readLine() );

            while (true) {
                String requestLine = prompt();
                out.println(requestLine);
                out.flush();
                while(true) {
                    String responseLine = in.readLine();
                    System.out.println(responseLine);
                    if(responseLine.length()==0)
                        break;
                }
                if (requestLine.equals("EXIT")){
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        keyIn.close();
        iocContainer.close();
    }

    private static String prompt() {
        System.out.print("입력> ");
        return keyIn.nextLine();
    }
}






















