package dev.viniciusarnhold.experiments.aot.app.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

@Component
public class GithubClientImpl implements GithubClient {

    private final WebClient client;

    public GithubClientImpl(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder.baseUrl("https://api.github.com")
                .build();
    }

    @Override
    public Flux<GithubRepositoryInfo> listRepos(String user) {
        return client.get()
                .uri("/users/{user}/repos", user)
                .retrieve()
                .bodyToFlux(GithubRepositoryInfo.class);
    }
}
