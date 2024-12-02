import java.io.*;
import javax.net.ssl.*;

public class client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8443;

        // Charger le truststore du client
        System.setProperty("javax.net.ssl.trustStore", "clientTruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "passserver123");

        // Créer une connexion SSL
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) socketFactory.createSocket(host, port);

        System.out.println("Connecté au serveur !");

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Envoyer un message au serveur
        out.write("Bonjour, serveur !\n");
        out.flush();

        // Lire la réponse du serveur
        String response = in.readLine();
        System.out.println("Reçu du serveur : " + response);

        socket.close();
    }
}
