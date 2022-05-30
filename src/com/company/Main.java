package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import static com.company.Const.TCP_PORT;

/**
 * Класс создает новый Thread для каждого нового TCP подключения
 */
public class Main {

    static ArrayList <ClientHandler> thread = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(TCP_PORT);)
        {
            while (true) {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                thread.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {

        }
    }
}
