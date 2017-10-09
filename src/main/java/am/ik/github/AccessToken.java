package am.ik.github;

import org.springframework.http.HttpHeaders;

import java.util.Optional;
import java.util.function.Consumer;

public class AccessToken {
    private final String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken() {
        this(Optional.ofNullable(System.getenv("GITHUB_ACCESS_TOKEN"))
                .orElseGet(() -> System.getProperty("github.access.token", "")));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Consumer<HttpHeaders> toAuthorization() {
        return httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, "token " + accessToken);
    }
}
