package am.ik.github;

import am.ik.github.contents.ContentsApi;
import org.springframework.web.reactive.function.client.WebClient;

public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(String apiUrl, WebClient.Builder builder, AccessToken accessToken) {
        this.webClient = builder //
                .baseUrl(apiUrl + "/repos/{owner}/{repo}/") //
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
}
