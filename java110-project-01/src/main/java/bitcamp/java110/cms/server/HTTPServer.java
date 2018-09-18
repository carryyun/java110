package bitcamp.java110.cms.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class HTTPServer {

    public static void main(String[] args) throws Exception {

        try(    Socket socket = new Socket("www.zdnet.co.kr", 80);
                PrintStream out =new PrintStream(socket.getOutputStream());
                Scanner in = new Scanner(socket.getInputStream());
                ){        
            // HTTP 요청
            // 1) request-line
            out.println("GET / HTTP/1.1");

            // 2) 요청 헤더 (general header | request header | entity header)
            out.println("Host: www.zdnet.co.kr");
            out.println("Connection: keep-alive");
            out.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
            out.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            out.println("Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");

            // 3) 헤더 끝을 표시
            out.println();

            // 4) 서버에 보낼 데이터(message-body)
            // => 없으면 생략

            while(true) {
                String str= in.nextLine();
                System.out.println(str);
            }
        }


    }

}
