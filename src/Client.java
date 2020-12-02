import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        // need host and port, we want to connect to the ServerSocket at port 44444
        sendMsg();
    }

    public static void sendMsg() throws InterruptedException, IOException {

        Socket socket = new Socket("localhost", 44444);
        System.out.println("Connected!"); //172.20.10.2
        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        try{

                String e = "Test";
                objectOutputStream.writeObject(e);
                Thread.sleep(100000);
        } finally{
            System.out.println("Closing socket and terminating program.");
            socket.close();
        }
    }
}

    /*
        // make a bunch of messages to send.
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello from the other side!"));
        messages.add(new Message("How are you doing?"));
        messages.add(new Message("What time is it?"));
        messages.add(new Message("Hi hi hi hi."));

        List<Block> blockchain = new ArrayList<>();
        blockchain.add(new Block("First block", "0"));
        blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));
        */
