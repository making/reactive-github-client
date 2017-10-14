package am.ik.github.repositories.commits;

import am.ik.github.AccessToken;
import am.ik.github.GitHubClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

public class CommitsApiTest {
    GitHubClient client;

    @Before
    public void setUp() throws Exception {
        this.client = new GitHubClient(WebClient.builder(), new AccessToken());
    }

    @Test
    public void commits() throws Exception {
        Flux<CommitsResponse.Commit> making = this.client
                .commits("making", "ik.am").get();
        List<CommitsResponse.Commit> commits = making.collectList().block();
        System.out.println(commits);
    }


    @Test
    public void commitsPath() throws Exception {
        Flux<CommitsResponse.Commit> making = this.client
                .commits("making", "ik.am")
                .get(p -> p.path("pom.xml"));
        List<CommitsResponse.Commit> commits = making.collectList().block();
        System.out.println(commits);
    }
}