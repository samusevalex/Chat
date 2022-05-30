package com.company;

/**
 * Класс принимает сообщение от одного клиента и разливает его по всем другим клиентам
 */
class TypeMessage {

    private static TypeMessage instance = null;

    private TypeMessage() {
    }

    /**
     * SingleTon
     * @return возвращает instance класса.
     */
    static TypeMessage getInstance() {
        if (instance == null)
            instance = new TypeMessage();
        return instance;
    }

    /**
     * @param myClientHandler - ссылка на instance клиента, для того, чтобы клиент, отправивший сообщение, сам его не получал
     * @param message - сообщение, которое надо разлить по всем остальным клиентам
     */
    void type(ClientHandler myClientHandler, String message) {
        for (ClientHandler clientHandler : Main.thread)
            if (clientHandler != null &&  clientHandler != myClientHandler)
                clientHandler.getOut().println(message);
    }
}
