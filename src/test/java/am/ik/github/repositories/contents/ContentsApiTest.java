package am.ik.github.repositories.contents;

import am.ik.github.AccessToken;
import am.ik.github.GitHubClient;
import am.ik.github.core.Content;
import am.ik.github.core.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(del.getCommit()).isNotNull();
        assertThat(del.getCommit().getMessage()).isEqualTo("Delete index5.html");
        assertThat(del.getCommit().getCommitter()).isNotNull();
        assertThat(del.getCommit().getCommitter().getName()).isEqualTo("Concourse Bot");
    }

    @Test
    public void testReadme() throws Exception {
        Mono<ContentsResponse.File> readme = this.client.readme("making", "ik.am")
                .get()
                .log("readme");
        ContentsResponse.File rm = readme.block();
        System.out.println(rm);
        assertThat(rm.decode()).isEqualTo("https://ik.am");
        assertThat(rm.getPath()).isEqualTo("README.md");
        assertThat(rm.getName()).isEqualTo("README.md");
    }

    @Test
    public void testContents() {
        Flux<Content> flux = this.client.contents("making-dev", "making-dev.github.io").get();
        List<Content> contents = flux.collectList().block();
        System.out.println(contents);
        assertThat(contents).isNotEmpty();
        assertThat(contents).hasSize(3);
        assertThat(contents.get(0).getName()).isEqualTo("foo");
        assertThat(contents.get(0).getPath()).isEqualTo("foo");
        assertThat(contents.get(0).getType()).isEqualTo(ContentType.DIR);
        assertThat(contents.get(1).getName()).isEqualTo("index.html");
        assertThat(contents.get(1).getPath()).isEqualTo("index.html");
        assertThat(contents.get(1).getType()).isEqualTo(ContentType.FILE);
        assertThat(contents.get(2).getName()).isEqualTo("index4.html");
        assertThat(contents.get(2).getPath()).isEqualTo("index4.html");
        assertThat(contents.get(2).getType()).isEqualTo(ContentType.FILE);
    }
}