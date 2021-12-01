package dev.viniciusarnhold.experiments.aot.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import dev.viniciusarnhold.experiments.aot.app.services.GithubService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/github")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{user}/repos")
    public Flux<GithubRepositoryInfo> listRepos(@PathVariable("user") String user) {
        return githubService.listRepos(user);
    }

}
