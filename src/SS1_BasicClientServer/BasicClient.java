package SS1_BasicClientServer;
import java.net.*;
import java.io.*;
public class BasicClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        System.out.println(resp);
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BasicClient client = new BasicClient();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("hello server");
        System.out.println("Client đã nhận được message:"+response);
    }
}
