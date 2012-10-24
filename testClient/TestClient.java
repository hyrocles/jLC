package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: kspringer
 * Date: 19.10.12
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
public class TestClient {
    public static void main(String[] args) {
        Socket server = null;

        try {
            server = new Socket("localhost", 9898);
            Scanner in = new Scanner(server.getInputStream());
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);

            out.println("2");
            out.println("4");
            out.println("Q");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            System.out.print("Server not running? Try restart...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null)
                try {
                    server.close();
                } catch (IOException e) {
                }
        }
    }
}
