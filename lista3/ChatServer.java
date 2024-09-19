package lista3;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<Socket> clientes = new HashSet<>();
    private static final int PORTA = 37372;

    public static void main(String[] args) throws IOException {
        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            System.out.println("Servidor de chat iniciado na porta " + PORTA);

            while (true) {
                Socket cliente = servidor.accept();
                clientes.add(cliente);
                System.out.println("Novo cliente conectado: " + cliente.getInetAddress());

                new Thread(new ClienteHandler(cliente)).start();
            }
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket cliente;
        private BufferedReader in;
        @SuppressWarnings("resource")
        public ClienteHandler(Socket cliente) throws IOException {
            this.cliente = cliente;
            in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            new PrintWriter(cliente.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                String mensagem;
                while ((mensagem = in.readLine()) != null) {
                    System.out.println("Mensagem recebida: " + mensagem);
                    enviarParaTodos(mensagem);
                }
            } catch (IOException e) {
                System.err.println("Erro no cliente: " + e.getMessage());
            } finally {
                try {
                    cliente.close();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar cliente: " + e.getMessage());
                }
            }
        }

        private void enviarParaTodos(String mensagem) {
            for (Socket cliente : clientes) {
                try {
                    PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                    out.println(mensagem);
                } catch (IOException e) {
                    System.err.println("Erro ao enviar mensagem: " + e.getMessage());
                }
            }
        }
    }
}

