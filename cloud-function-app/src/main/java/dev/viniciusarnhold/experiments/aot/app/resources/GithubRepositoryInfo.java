package dev.viniciusarnhold.experiments.aot.app.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubRepositoryInfo(long id,
                                   @JsonProperty("full_name")
                                   String fullName,
                                   String language) {
}
