import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread{

    private Socket client;
    private ArrayList<ClientHandler> clients;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;

        public ClientHandler(Socket clientSocket/*, ArrayList<ClientHandler> clients*/) throws IOException {
            this.client = clientSocket;
            //this.clients = clients;
            this.input = new ObjectInputStream(client.getInputStream());
            this.output =  new ObjectOutputStream(client.getOutputStream());
        }

        @Override
            //run handles the client "requests"
        public void run() {
            try {
                Object msg = input.readObject();
                System.out.println(msg);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            try {
                    client.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
}
