package lista3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORTA = 37372;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            new Thread(() -> {
                try {
                    String mensagem;
                    while ((mensagem = in.readLine()) != null) {
                        System.out.println(mensagem);
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao receber mensagem: " + e.getMessage());
                }
            }).start();
            System.out.println("Digite sua mensagem: ");
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
}
