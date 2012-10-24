import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created with IntelliJ IDEA.
 * User: kspringer
 * Date: 19.10.12
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Service {
    public static final int PORT = 9898;

    public Service(int port) throws IOException {
        ServerSocket ss = new ServerSocket(port);
        while (true) {
            ServerThread thread = null;
            try {
                thread = new ServerThread(ss.accept());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (thread != null)
                    thread.interrupt();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Service(PORT);
    }
}
