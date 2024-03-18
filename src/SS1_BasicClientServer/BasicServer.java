package SS1_BasicClientServer;

import java.net.*;
import java.io.*;
public class BasicServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        System.out.println("Server đã nhận được message:"+greeting);
        if ("hello server".equals(greeting)) {
            out.println("hello client");
        }
        else {
            out.println("unrecognised greeting");
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
