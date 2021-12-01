package dev.viniciusarnhold.experiments.aot.app.client;

import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

public interface GithubClient {

    Flux<GithubRepositoryInfo> listRepos(String user);

}
