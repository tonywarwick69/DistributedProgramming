package SS2_ContinuousServerClient;

import SS1_BasicClientServer.BasicServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while((inputLine =in.readLine())!= null){
            //If you input "." symbol in the client it close the server connection
            if(".".equals(inputLine)){
                out.println("good bye");
                break;
            }
            out.println(inputLine);
        }
    }
    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("Server đang lắng nghe tại cổng 6666");
        BasicServer server=new BasicServer();
        server.start(6666);
    }

}
