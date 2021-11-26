package dev.viniciusarnhold.experiments.aot.app;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.viniciusarnhold.experiments.aot.app.client.GithubClient;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepoRequest;
import dev.viniciusarnhold.experiments.aot.app.resources.GithubRepositoryInfo;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class CloudFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFunctionApplication.class);
    }

    @Bean
    Function<GithubRepoRequest, Flux<GithubRepositoryInfo>> repos(GithubClient client) {
        return request -> client.repositories(request.user());
    }
}
