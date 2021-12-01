package dev.viniciusarnhold.experiments.aot.app.functions;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import dev.viniciusarnhold.experiments.aot.app.client.GithubClient;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepoRequest;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

@Component("repos")
public class GithubRepoListFunction implements Function<GithubRepoRequest, Flux<GithubRepositoryInfo>> {

    private final GithubClient client;

    public GithubRepoListFunction(GithubClient client) {
        this.client = client;
    }

    @Override
    public Flux<GithubRepositoryInfo> apply(GithubRepoRequest request) {
        return client.repositories(request.user());
    }
}
