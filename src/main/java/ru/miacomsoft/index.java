package ru.miacomsoft;

import ru.miacomsoft.WebServerLite.HttpExchange;
import ru.miacomsoft.WebServerLite.onPage;


public class index {
    @onPage(url = "index1.html", ext = "html")
    public byte[] page(HttpExchange query) {
        return "asdfasdfsa asdfasdfa sadfsadf".getBytes();
    }
}
