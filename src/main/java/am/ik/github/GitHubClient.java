package am.ik.github;

import am.ik.github.repositories.commits.CommitsApi;
import am.ik.github.repositories.contents.ContentsApi;
import org.springframework.web.reactive.function.client.WebClient;

public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(String apiUrl, WebClient.Builder builder, AccessToken accessToken) {
        this.webClient = builder //
                .baseUrl(apiUrl) //
                .defaultHeaders(accessToken.toAuthorization())
                .build();
    }

    public GitHubClient(WebClient.Builder builder, AccessToken accessToken) {
        this("https://api.github.com", builder, accessToken);
    }

    public ContentsApi.Readme readme(String owner, String repo) {
        return new ContentsApi.Readme(webClient, owner, repo);
    }

    public ContentsApi.File file(String owner, String repo, String path) {
        return new ContentsApi.File(webClient, owner, repo, path);
    }

    public ContentsApi.Contents contents(String owner, String repo, String path) {
        return new ContentsApi.Contents(webClient, owner, repo, path);
    }

    public ContentsApi.Contents contents(String owner, String repo) {
        return this.contents(owner, repo, "");
    }

    public CommitsApi.Commits commits(String owner, String repo) {
        return new CommitsApi.Commits(webClient, owner, repo);
    }
}
