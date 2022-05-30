package com.company;

import java.util.ArrayDeque;

import static com.company.Const.BUFFER_SIZE;

/**
 * Класс хранит последние BUFFER_SIZE сообщений чата. Когда подключается новый клиент, ему выводится этот буфер.
 */
class Buffer {

    private ArrayDeque<String> buffer = new ArrayDeque<>(BUFFER_SIZE);
    private static Buffer instance = null;

    private Buffer() {
    }

    /**
     * Паттен SingleTon.
     * @return возвращает instance класса.
     */
    static Buffer getInstance() {
        if (instance == null)
            instance = new Buffer();
        return instance;
    }

    /**
     * @param str - сообщение, добавляемое в буфер
     */
    synchronized void addMessage(String str) {
        buffer.addLast(str);
        if(buffer.size()> BUFFER_SIZE)
            buffer.removeFirst();
    }

    /**
     * @return возвращает клон буффера. Вызывается, когда к чату подключается новый клиент.
     */
    ArrayDeque<String> getClone() {
        return buffer.clone();
    }
}
