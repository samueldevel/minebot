package com.samuel.minebot.token;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class DiscordToken {

    @Value("${discord.token}")
    private String token;

    public String getToken() {
        log.info("token successfully caught up");
        return token;
    }
}