package dev.viniciusarnhold.experiments.aot.app.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

@Component
public class GithubClient {

    private final WebClient client;

    public GithubClient(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder
                .baseUrl("https://api.github.com/")
                .build();
    }

    public Flux<GithubRepositoryInfo> repositories(String name) {
        return this.client.get()
                .uri("/users/{user}/repos", name)
                .retrieve()
                .bodyToFlux(GithubRepositoryInfo.class);
    }
}
