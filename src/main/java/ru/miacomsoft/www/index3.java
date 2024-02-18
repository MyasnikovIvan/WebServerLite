package ru.miacomsoft.www;

import ru.miacomsoft.WebServerLite.HttpExchange;
import ru.miacomsoft.WebServerLite.onPage;

public class index3 {
    @onPage(url="index3.html",ext="js")
    public byte[] indexPage(HttpExchange query) {
        return " // ddddddddddddfsdfsd---  sdfsdf sd--- sdfsddddddddd".getBytes();
    }
}
