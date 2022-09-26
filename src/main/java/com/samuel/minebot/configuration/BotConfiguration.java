package com.samuel.minebot.configuration;

import com.samuel.minebot.command.Command;
import com.samuel.minebot.token.DiscordToken;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.gateway.intent.IntentSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.List;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class BotConfiguration {

    private final DiscordToken discordToken;

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<Command<T>> commands) {
        log.info("client logging...");
        GatewayDiscordClient client = DiscordClientBuilder.create(discordToken.getToken()).build()
                .gateway()
                .setEnabledIntents(IntentSet.all())
                .login().block();
        log.info("Discord Client logged!");

        Flux.fromIterable(commands)
                .flatMap(command -> client.on(command.getClassType())
                        .flatMap(command::execute)
                        .next())
                .subscribe();

        return client;
    }
}