package SS3_MutliServerContinuous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
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
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.startConnection("127.0.0.1",6666);
        String inputLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while((inputLine = reader.readLine()) != null){
            System.err.println("Client send:"+inputLine);
            String response = client.sendMessage(inputLine);
            System.out.println(response);

        }

    }
}
