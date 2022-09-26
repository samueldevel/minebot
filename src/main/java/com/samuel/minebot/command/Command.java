package com.samuel.minebot.command;

import discord4j.core.event.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public interface Command<T extends Event> {

    Logger log = LoggerFactory.getLogger(Command.class);

    Class<T> getClassType();

    Mono<Void> execute(T event);

    default Mono<Void> handleError(Throwable error) {
        log.error("Unable to process" + getClassType().getSimpleName(), error);
        return Mono.empty();
    }
}