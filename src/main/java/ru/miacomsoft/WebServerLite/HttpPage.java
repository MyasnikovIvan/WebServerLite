package ru.miacomsoft.WebServerLite;

public interface HttpPage {
    public abstract byte[]  onPage (HttpExchange query);
}

