package bitcamp.java110.cms.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public void service() {
        
    }

    public static void main(String[] args) throws Exception {

        // 클라이언트 연결을 기다리는 서버 소켓 준비
        ServerSocket serversocket = new ServerSocket(8888);

        while(true) {
            System.out.println("접속 대기중...");
            try (
                    
                    Socket socket = serversocket.accept();
                    PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    ){
                
                System.out.println(in.readLine());
                
                out.println("OK"); out.flush();
                
                while (true) {
                    String requestLine = in.readLine();
                    if (requestLine.equals("EXIT")){
                        out.println("good bye!");
                        out.println();
                        out.flush();
                        break;
                    }
                    out.println(requestLine);
                    out.println();
                    out.flush();
                }


            }catch(Exception e) {

            }
        }
    }
}

