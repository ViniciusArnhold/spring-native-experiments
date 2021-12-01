package dev.viniciusarnhold.experiments.aot.app.services;

import org.springframework.stereotype.Service;

import dev.viniciusarnhold.experiments.aot.app.client.GithubClient;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

@Service
class GithubServiceImpl implements GithubService {

    private final GithubClient githubClient;

    public GithubServiceImpl(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public Flux<GithubRepositoryInfo> listRepos(String user) {
        return githubClient.listRepos(user);
    }
}
