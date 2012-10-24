import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: kspringer
 * Date: 19.10.12
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        System.out.println(getName() + " -> Creating new Thread...");
        this.socket = socket;
        start();
    }

    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input = "";

            while ((input = in.nextLine()).equalsIgnoreCase("q") == false) {
                System.out.println(input);
                System.out.println(getName() + " -> Waiting for Input...");
            }
            System.out.println(getName() + " -> Closing Connection...Bye!");

            socket.close();
            interrupt();
        } catch (Throwable t) {
            System.out.println("Caught " + t + " - closing thread");
        }
    }
}
