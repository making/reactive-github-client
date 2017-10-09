package am.ik.github.repositories.contents;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentsResponseTest {

    @Test
    public void testCreate() throws Exception {
        String json = "{\n" +
                "  \"content\": {\n" +
                "    \"name\": \"index5.html\",\n" +
                "    \"path\": \"index5.html\",\n" +
                "    \"sha\": \"5ab2f8a4323abafb10abb68657d9d39f1a775057\",\n" +
                "    \"size\": 5,\n" +
                "    \"url\": \"https://api.github.com/repos/making-dev/making-dev.github.io/contents/index5.html?ref=master\",\n" +
                "    \"html_url\": \"https://github.com/making-dev/making-dev.github.io/blob/master/index5.html\",\n" +
                "    \"git_url\": \"https://api.github.com/repos/making-dev/making-dev.github.io/git/blobs/5ab2f8a4323abafb10abb68657d9d39f1a775057\",\n" +
                "    \"download_url\": \"https://raw.githubusercontent.com/making-dev/making-dev.github.io/master/index5.html\",\n" +
                "    \"type\": \"file\",\n" +
                "    \"_links\": {\n" +
                "      \"self\": \"https://api.github.com/repos/making-dev/making-dev.github.io/contents/index5.html?ref=master\",\n" +
                "      \"git\": \"https://api.github.com/repos/making-dev/making-dev.github.io/git/blobs/5ab2f8a4323abafb10abb68657d9d39f1a775057\",\n" +
                "      \"html\": \"https://github.com/making-dev/making-dev.github.io/blob/master/index5.html\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"commit\": {\n" +
                "    \"sha\": \"c0c6dcec470e4e951fcdcb2535a7a5b4a5648254\",\n" +
                "    \"url\": \"https://api.github.com/repos/making-dev/making-dev.github.io/git/commits/c0c6dcec470e4e951fcdcb2535a7a5b4a5648254\",\n" +
                "    \"html_url\": \"https://github.com/making-dev/making-dev.github.io/commit/c0c6dcec470e4e951fcdcb2535a7a5b4a5648254\",\n" +
                "    \"author\": {\n" +
                "      \"name\": \"Concourse Bot\",\n" +
                "      \"email\": \"makingx+bot@gmail.com\",\n" +
                "      \"date\": \"2017-10-08T18:02:52Z\"\n" +
                "    },\n" +
                "    \"committer\": {\n" +
                "      \"name\": \"Concourse Bot\",\n" +
                "      \"email\": \"makingx+bot@gmail.com\",\n" +
                "      \"date\": \"2017-10-08T18:02:52Z\"\n" +
                "    },\n" +
                "    \"tree\": {\n" +
                "      \"sha\": \"abe17eb04955465c233442ec7e980942bde60bb9\",\n" +
                "      \"url\": \"https://api.github.com/repos/making-dev/making-dev.github.io/git/trees/abe17eb04955465c233442ec7e980942bde60bb9\"\n" +
                "    },\n" +
                "    \"message\": \"hi\",\n" +
                "    \"parents\": [\n" +
                "      {\n" +
                "        \"sha\": \"8ddaf26f7185a194c3f905cc896cd2086afd691a\",\n" +
                "        \"url\": \"https://api.github.com/repos/making-dev/making-dev.github.io/git/commits/8ddaf26f7185a194c3f905cc896cd2086afd691a\",\n" +
                "        \"html_url\": \"https://github.com/making-dev/making-dev.github.io/commit/8ddaf26f7185a194c3f905cc896cd2086afd691a\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();
        ContentsResponse.Put put = objectMapper.readValue(json, ContentsResponse.Put.class);

        assertThat(put.getCommit()).isNotNull();
        assertThat(put.getContent()).isNotNull();
        assertThat(put.getCommit().getMessage()).isEqualTo("hi");
        assertThat(put.getCommit().getSha()).isEqualTo("c0c6dcec470e4e951fcdcb2535a7a5b4a5648254");
        assertThat(put.getCommit().getUrl()).isEqualTo("https://api.github.com/repos/making-dev/making-dev.github.io/git/commits/c0c6dcec470e4e951fcdcb2535a7a5b4a5648254");
        assertThat(put.getCommit().getCommitter()).isNotNull();
        assertThat(put.getCommit().getCommitter().getName()).isEqualTo("Concourse Bot");
        assertThat(put.getCommit().getCommitter().getEmail()).isEqualTo("makingx+bot@gmail.com");
        assertThat(put.getCommit().getCommitter().getDate().toString()).isEqualTo("2017-10-08T18:02:52Z");
        assertThat(put.getContent().getName()).isEqualTo("index5.html");
        assertThat(put.getContent().getPath()).isEqualTo("index5.html");
        assertThat(put.getContent().getSha()).isEqualTo("5ab2f8a4323abafb10abb68657d9d39f1a775057");
        assertThat(put.getContent().getUrl()).isEqualTo("https://api.github.com/repos/making-dev/making-dev.github.io/contents/index5.html?ref=master");
    }
}
