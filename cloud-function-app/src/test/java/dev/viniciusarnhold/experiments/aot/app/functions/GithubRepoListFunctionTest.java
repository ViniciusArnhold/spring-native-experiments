package dev.viniciusarnhold.experiments.aot.app.functions;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.function.context.FunctionCatalog;

import dev.viniciusarnhold.experiments.aot.app.client.GithubClient;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepoRequest;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
class GithubRepoListFunctionTest {

    @MockBean
    GithubClient githubClient;

    @Test
    void apply(@Autowired FunctionCatalog functionCatalog) {
        when(githubClient.repositories("SomeUser"))
                .thenReturn(Flux.just(new GithubRepositoryInfo(1L, "Repo 1", null), new GithubRepositoryInfo(2L, "Repo 2", null)));

        Function<GithubRepoRequest, Flux<GithubRepositoryInfo>> reposFunction = functionCatalog.lookup("repos");

        StepVerifier.create(reposFunction.apply(new GithubRepoRequest("SomeUser")))
                .expectNextCount(2)
                .verifyComplete();
    }
}