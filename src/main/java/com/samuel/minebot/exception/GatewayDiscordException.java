package com.samuel.minebot.exception;

public class GatewayDiscordException extends RuntimeException{
    public GatewayDiscordException(String message) {
        super(message);
    }
}
