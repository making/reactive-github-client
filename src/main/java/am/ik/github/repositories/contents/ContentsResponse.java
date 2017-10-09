package am.ik.github.repositories.contents;

import am.ik.github.core.Commit;
import am.ik.github.core.Content;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Base64;

public abstract class ContentsResponse {

    public static final class Put {
        private final Content content;
        private final Commit commit;

        @JsonCreator
        public Put(@JsonProperty("content") Content content,
                   @JsonProperty("commit") Commit commit) {
            this.content = content;
            this.commit = commit;
        }

        public Content getContent() {
            return content;
        }

        public Commit getCommit() {
            return commit;
        }

        @Override
        public String toString() {
            return "Create{" +
                    "content=" + content +
                    ", commit=" + commit +
                    '}';
        }
    }

    public static final class Delete {
        private final Commit commit;

        @JsonCreator
        public Delete(@JsonProperty("commit") Commit commit) {
            this.commit = commit;
        }

        public Commit getCommit() {
            return commit;
        }

        @Override
        public String toString() {
            return "Delete{" +
                    "commit=" + commit +
                    '}';
        }
    }

    public static final class File extends Content {
        private final String content;

        @JsonCreator
        public File(@JsonProperty("name") String name,
                    @JsonProperty("path") String path,
                    @JsonProperty("sha") String sha,
                    @JsonProperty("url") String url,
                    @JsonProperty("git_url") String gitUrl,
                    @JsonProperty("html_url") String htmlUrl,
                    @JsonProperty("download_url") String downloadUrl,
                    @JsonProperty("content") String content) {
            super(name, path, sha, url, gitUrl, htmlUrl, downloadUrl, "file");
            this.content = content;
        }

        public String decode() {
            return new String(Base64.getMimeDecoder().decode(this.content));
        }

        public String getContent() {
            return content;
        }
    }
}