package com.samuel.minebot.configuration;

import com.samuel.minebot.token.DiscordToken;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.gateway.intent.IntentSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class BotConfiguration {

    private final DiscordToken discordToken;

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        log.info("client logging...");
        GatewayDiscordClient gateway = DiscordClientBuilder.create(discordToken.getToken()).build()
                .gateway()
                .login()
                .block();
        log.info("client logged!");

        gateway.onDisconnect().block();
        return gateway;
    }
}