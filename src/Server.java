import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Taken from https://gist.github.com/chatton/14110d2550126b12c0254501dde73616
public class Server {

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException{
            ServerSocket ss = new ServerSocket(44444);
        while(true) {
            // don't need to specify a hostname, it will be the current machine

            System.out.println("ServerSocket awaiting connections...");
            Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + socket + "!");
            ClientHandler clientThread = new ClientHandler(socket);
            clients.add(clientThread);

            Thread t = new Thread(clientThread);
            t.start();
        }

        /*
        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


        // read the list of messages from the socket
        List<Block> blockchain = (List<Block>) objectInputStream.readObject();
        System.out.println("Received [" + blockchain.size() + "] messages from: " + socket);
        // print out the text of every message
        System.out.println("All messages:");
        blockchain.forEach((msg)-> System.out.println(msg.getData()));

        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
         */
    }
}