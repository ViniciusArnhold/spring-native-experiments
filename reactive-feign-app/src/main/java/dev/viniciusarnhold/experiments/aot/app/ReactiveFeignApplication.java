package dev.viniciusarnhold.experiments.aot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AccessBits;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.viniciusarnhold.experiments.aot.app.client.GithubClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactivefeign.spring.config.ReactiveFeignAutoConfiguration;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableReactiveFeignClients
@RestController
@NativeHint(
        trigger = ReactiveFeignClient.class,
        types = {
                @TypeHint(types = ReactiveFeignAutoConfiguration.class, access = AccessBits.FULL_REFLECTION)
        }
)
public class ReactiveFeignApplication {

    private final GithubClient githubClient;

    public ReactiveFeignApplication(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveFeignApplication.class);
    }

    @GetMapping("/github/repos/{username}")
    Flux<String> listRepositories(@PathVariable String username) {
        return githubClient.repos(username);
    }

}
