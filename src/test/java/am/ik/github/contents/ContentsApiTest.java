package am.ik.github.contents;

import am.ik.github.AccessToken;
import am.ik.github.GitHubClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ContentsApiTest {
    GitHubClient client;

    @Before
    public void setUp() throws Exception {
        this.client = new GitHubClient(WebClient.builder(), new AccessToken());
    }


    @Test
    public void testFileApi() throws Exception {
        ContentsApi.File file = this.client.file("making-dev", "making-dev.github.io", "index5.html");

        Mono<ContentsResponse.Put> create = file.create(ContentsRequest.Builder
                .fromPlainText("Hello")
                .committer("Concourse Bot", "makingx+bot@gmail.com ")
                .toCreate("hi"))
                .log("create");

        Mono<ContentsResponse.File> get = create.flatMap(f -> file.get()
                .log("get"));

        Mono<ContentsResponse.Put> update = get.flatMap(f -> file.update(ContentsRequest.Builder
                .fromPlainText("Updated!")
                .toUpdate("Update " + f.getName(), f.getSha()))
                .log("update"));

        Mono<ContentsResponse.Delete> delete = update.flatMap(p -> file.delete(ContentsRequest.Builder
                .noContent()
                .committer("Concourse Bot", "makingx+bot@gmail.com ")
                .toDelete("Delete " + p.getContent().getName(), p.getContent().getSha()))
                .log("delete"));

        ContentsResponse.Delete del = delete.block();
        System.out.println(del);
    }

    @Test
    public void testReadme() throws Exception {
        Mono<ContentsResponse.File> readme = this.client.readme("making", "blog.ik.am")
                .get()
                .log("reademe");
        ContentsResponse.File rm = readme.block();
        System.out.println(rm);
    }
}