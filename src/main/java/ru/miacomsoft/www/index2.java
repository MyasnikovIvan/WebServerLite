package ru.miacomsoft.www;

import ru.miacomsoft.WebServerLite.HttpExchange;
import ru.miacomsoft.WebServerLite.onPage;

public class index2 {
    @onPage(url="index2.html",ext="html")
    public byte[] indexPage(HttpExchange query) {
        return "ddddddddddddddddddddd".getBytes();
    }
}
