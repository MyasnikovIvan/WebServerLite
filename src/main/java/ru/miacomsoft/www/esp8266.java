package ru.miacomsoft.www;

import ru.miacomsoft.WebServerLite.HttpExchange;
import ru.miacomsoft.WebServerLite.onPage;

public class esp8266 {
    @onPage(url="esp8266.html",ext="html")
    public byte[] indexPage(HttpExchange query) {
        return "Test message".getBytes();
    }
}
