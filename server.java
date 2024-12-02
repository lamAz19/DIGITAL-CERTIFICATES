import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class server {
    public static void main(String[] args) throws Exception {
        int port = 8443;

        // Charger le keystore du serveur
        System.setProperty("javax.net.ssl.keyStore", "serverkey.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "stpass123");

        // Créer le serveur SSL
        SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(port);

        System.out.println("Serveur en écoute sur le port " + port + "...");

        // Accepter une connexion client
        while (true) {
            SSLSocket socket = (SSLSocket) serverSocket.accept();
            System.out.println("Client connecté !");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Lire les données du client
            String message = in.readLine();
            System.out.println("Reçu du client : " + message);

            // Répondre au client
            out.write("Bonjour, client !\n");
            out.flush();

            socket.close();
        }
    }
}
