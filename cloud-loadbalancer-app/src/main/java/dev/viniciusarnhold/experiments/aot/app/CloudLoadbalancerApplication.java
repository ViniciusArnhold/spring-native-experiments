package dev.viniciusarnhold.experiments.aot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class CloudLoadbalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudLoadbalancerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    WebClient.Builder loadBalancedWebClient(ClientHttpConnector connector) {
        return WebClient.builder()
                .clientConnector(connector);
    }

    @Bean
    WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.build();
    }

    @Bean
    RouterFunction<ServerResponse> githubRouter(WebClient webClient) {
        return route(GET("/github/repos/{username}"),
                req -> {
                    Flux<String> githubRepoFlux = webClient.get()
                            .uri("https://github-service/users/{username}/repos", req.pathVariable("username"))
                            .retrieve()
                            .bodyToFlux(String.class);

                    return ok().body(githubRepoFlux, String.class);
                });
    }

}
