package com.bug.issuereplicate;

import com.bug.issuereplicate.model.Cat;
import com.bug.issuereplicate.model.Dog;
import com.bug.issuereplicate.model.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
public class IssueReplicateApplication {

    private static final Logger log = LoggerFactory.getLogger(IssueReplicateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IssueReplicateApplication.class, args);
    }

    @Bean
    public Consumer<Flux<Message<Pet>>> consumer() {
        return flux -> flux
                .doOnNext(m -> log.info(m.toString()))
				.map(Message::getPayload)
				.doOnNext(pet -> log.info(pet.toString()))
                .subscribe();
    }

    @Bean
    public Supplier<Flux<Message<Pet>>> producer() {
        return () -> Flux.just(new Cat(), new Dog())
                         .map(pet -> CloudEventMessageBuilder.withData(pet).build())
                         .doOnNext(message -> log.info(message.toString()));
    }


}
