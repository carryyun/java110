package bitcamp.java110.cms.server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPClient {

    public static void main(String[] args) throws Exception {
        ServerSocket serversocket = new ServerSocket(80);

        while(true) {
            try(    Socket socket = serversocket.accept();
                    PrintStream out =new PrintStream(socket.getOutputStream());
                    Scanner in = new Scanner(socket.getInputStream());
                    ){        
                // 클라이언트가 보낸 데이터 읽기
                while(true) {
                    String str= in.nextLine();
                    System.out.println(str);

                    if(str.length() == 0)
                        break;
                }

                // 클라이언트에게 응답하기
                out.println("HTTP/1.1 200 OK");
                out.println();
                out.print("Hello!");

            }
        }
    }
}
