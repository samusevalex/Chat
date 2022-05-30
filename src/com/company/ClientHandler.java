package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Боевой класс обслуживающий конкретное подключение к чату
 */
class ClientHandler extends Thread {

    private Socket clientSocket;
    private PrintWriter out;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    PrintWriter getOut(){
        return out;
    }

    @Override
    public void run() {
        /**
         * Создаём потоки in и out для клиента чата
         */
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            /**
             * Подтягиваем instance-ы буфера и класса, разливающего сообщения
             */
            Buffer buffer = Buffer.getInstance();
            TypeMessage typeMessage = TypeMessage.getInstance();
            /**
             * Вводим своё имя для чата
             */
            out.printf("Welcome to Chat.\nPlease enter your name: ");
            String userName = in.readLine();
            /**
             * Выводим последние BUFFER_SIZE сообщений чата
             */
            for (String str : buffer.getClone())
                out.println(str);
            /**
             * Вводим сообщение, отправляем его в буффер + отправляем на разливальщика
             */
            String newLine = "";
            while (!newLine.equals("exit")){
                out.printf(userName + ": ");
                String message = "\r" + userName + ": " + in.readLine();
                buffer.addMessage(message);
                typeMessage.type(this, message);
            };

            clientSocket.close();
            out.close();

        } catch (IOException e) {

        }
        Main.thread.remove(this);
    }
}
