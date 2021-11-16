package dev.viniciusarnhold.experiments.aot.app.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "github")
public interface GithubClient {
    @GetMapping("/users/{username}/repos")
    Flux<String> repos(@PathVariable String username);
}
