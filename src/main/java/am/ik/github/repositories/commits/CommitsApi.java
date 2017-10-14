package am.ik.github.repositories.commits;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class CommitsApi {

    public static class Commits {
        private final WebClient webClient;
        private final String owner;
        private final String repo;

        public Commits(WebClient webClient, String owner, String repo) {
            this.webClient = webClient;
            this.owner = owner;
            this.repo = repo;
        }


        public static class CommitParameter {
            private String sha;
            private String path;
            private String author;
            private ZonedDateTime since;
            private ZonedDateTime until;

            private CommitParameter() {

            }

            public CommitParameter sha(String sha) {
                this.sha = sha;
                return this;
            }

            public CommitParameter path(String path) {
                this.path = path;
                return this;
            }

            public CommitParameter author(String author) {
                this.author = author;
                return this;
            }

            public CommitParameter since(ZonedDateTime since) {
                this.since = since;
                return this;
            }

            public CommitParameter until(ZonedDateTime until) {
                this.until = until;
                return this;
            }

            MultiValueMap<String, String> queryParams() {
                MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
                if (sha != null) {
                    queryParams.add("sha", sha);
                }
                if (path != null) {
                    queryParams.add("path", path);
                }
                if (author != null) {
                    queryParams.add("author", author);
                }
                if (since != null) {
                    queryParams.add("since", since.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                }
                if (until != null) {
                    queryParams.add("until", until.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                }
                return queryParams;
            }
        }

        public Flux<CommitsResponse.Commit> get() {
            return this.get(x -> {
            });
        }


        public Flux<CommitsResponse.Commit> get(Consumer<CommitParameter> parameterConsumer) {
            CommitParameter parameter = new CommitParameter();
            parameterConsumer.accept(parameter);
            return this.webClient.get() //
                    .uri(b -> b.path("/repos/{owner}/{repo}/commits")
                            .queryParams(parameter.queryParams())
                            .build(owner, repo)) //
                    .retrieve()
                    .bodyToFlux(CommitsResponse.Commit.class);
        }
    }
}
