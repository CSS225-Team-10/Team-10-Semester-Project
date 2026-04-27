import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

import java.net.InetSocketAddress;

/**
 * This class should make a simple http server stores
 * the notes in an SQLite database, and also does
 * GET and POST requests on a local server?
 * 
 * I am going to include a lot of comments in this code!
 */
public class NotesServer {
    /**
     * Start the server
     */
    public static void main(String[] args) throws IOException {
        // https://jumpcloud.com/it-index/what-is-a-socket-address
        // https://docs.oracle.com/javase/8/docs/api/java/net/InetSocketAddress.html
        // https://docs.oracle.com/en/java/javase/23/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html
        // InetSocket adress is standard to define IP address abd port number
        // for java servers.
        InetSocketAddress address = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(address, 0);

        server.start();
        System.out.println("Server started on port 8080");
    }
}