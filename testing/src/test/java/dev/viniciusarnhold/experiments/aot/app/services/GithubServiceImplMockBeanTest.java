package dev.viniciusarnhold.experiments.aot.app.services;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dev.viniciusarnhold.experiments.aot.app.client.GithubClient;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = GithubServiceImpl.class)
@MockitoSettings
class GithubServiceImplMockBeanTest implements WithAssertions {

    @MockBean
    GithubClient client;

    @Autowired
    GithubServiceImpl githubService;

    @Test
    void sample() {
        when(client.listRepos("SomeUser")).thenReturn(Flux.just(
                new GithubRepositoryInfo(1L, null, null),
                new GithubRepositoryInfo(2L, null, null),
                new GithubRepositoryInfo(3L, null, null)
        ));

        StepVerifier.create(githubService.listRepos("SomeUser"))
                .expectNextCount(3)
                .verifyComplete();
    }
}