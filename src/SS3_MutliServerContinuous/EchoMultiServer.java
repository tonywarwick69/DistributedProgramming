package SS3_MutliServerContinuous;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoMultiServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while(true){
            new EchoClientHandler(serverSocket.accept()).start();
        }

    }
    private static class EchoClientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        public EchoClientHandler(Socket socket){
            this.clientSocket = socket;
        }
        public void run(){
            try {
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while((inputLine = in.readLine()) != null){
                    if(".".equals(inputLine)){
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }
                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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
        EchoMultiServer server=new EchoMultiServer();
        server.start(6666);

    }

}
