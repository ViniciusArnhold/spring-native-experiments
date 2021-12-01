package dev.viniciusarnhold.experiments.aot.app.services;

import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

public interface GithubService {

    Flux<GithubRepositoryInfo> listRepos(String user);

}
