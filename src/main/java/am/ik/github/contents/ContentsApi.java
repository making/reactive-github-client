package am.ik.github.contents;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ContentsApi {
    public static class File {
        private final WebClient webClient;
        private final String owner;
        private final String repo;
        private final String path;

        public File(WebClient webClient, String owner, String repo, String path) {
            this.webClient = webClient;
            this.owner = owner;
            this.repo = repo;
            this.path = path;
        }

        public Mono<ContentsResponse.File> get() {
            return this.webClient.get() //
                    .uri("/repos/{owner}/{repo}/contents/{path}", owner, repo, path) //
                    .retrieve()
                    .bodyToMono(ContentsResponse.File.class);
        }

        public Mono<ContentsResponse.Put> create(ContentsRequest.Create create) {
            return this.webClient.put() //
                    .uri("/repos/{owner}/{repo}/contents/{path}", owner, repo, path) //
                    .syncBody(create) //
                    .retrieve() //
                    .bodyToMono(ContentsResponse.Put.class);
        }

        public Mono<ContentsResponse.Put> update(ContentsRequest.Update update) {
            return this.webClient.put() //
                    .uri("/repos/{owner}/{repo}/contents/{path}", owner, repo, path) //
                    .syncBody(update) //
                    .retrieve() //
                    .bodyToMono(ContentsResponse.Put.class);
        }

        public Mono<ContentsResponse.Delete> delete(ContentsRequest.Delete delete) {
            return this.webClient.delete() //
                    .uri(b -> delete.buildingUri(b.path("/repos/{owner}/{repo}/contents/{path}")).build(owner, repo, path))
                    .retrieve() //
                    .bodyToMono(ContentsResponse.Delete.class);
        }
    }

    public static class Readme {
        private final WebClient webClient;
        private final String owner;
        private final String repo;

        public Readme(WebClient webClient, String owner, String repo) {
            this.webClient = webClient;
            this.owner = owner;
            this.repo = repo;
        }

        public Mono<ContentsResponse.File> get() {
            return this.webClient.get() //
                    .uri("/repos/{owner}/{repo}/readme", owner, repo) //
                    .retrieve()
                    .bodyToMono(ContentsResponse.File.class);
        }
    }
}
