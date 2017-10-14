package am.ik.github.repositories.contents;

import am.ik.github.core.Committer;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.util.UriBuilder;

import java.util.Base64;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class ContentsRequest {
    private static final String DEFAULT_BRANCH = "master";
    protected final String message;
    protected final String branch;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected Committer committer;

    public ContentsRequest(String message, String branch, Committer committer) {
        this.message = Objects.requireNonNull(message);
        this.branch = Objects.requireNonNull(branch);
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public String getBranch() {
        return branch;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    public static final class Create extends ContentsRequest {
        private final String content;

        public Create(String message, String content, String branch, Committer committer) {
            super(message, branch, committer);
            this.content = Objects.requireNonNull(content);
        }

        public String getContent() {
            return content;
        }
    }

    public static final class Update extends ContentsRequest {
        private final String content;
        private final String sha;

        public Update(String message, String content, String branch, Committer committer, String sha) {
            super(message, branch, committer);
            this.sha = Objects.requireNonNull(sha);
            this.content = Objects.requireNonNull(content);

        }

        public String getContent() {
            return content;
        }

        public String getSha() {
            return sha;
        }
    }

    public static final class Delete extends ContentsRequest {
        private final String sha;

        public Delete(String message, String branch, Committer committer, String sha) {
            super(message, branch, committer);
            this.sha = Objects.requireNonNull(sha);
        }

        public String getSha() {
            return sha;
        }

        public UriBuilder buildingUri(UriBuilder b) {
            b.queryParam("message", message)
                    .queryParam("sha", sha) //
                    .queryParam("branch", branch);
            if (committer != null) {
                b.queryParam("committer.name", committer.getName());
                b.queryParam("committer.email", committer.getEmail());
            }
            return b;
        }
    }

    public static final class Builder {
        private final String content;
        private Committer committer;
        private String branch = DEFAULT_BRANCH;

        private Builder(String content) {
            this.content = content;
        }

        public Builder committer(Committer committer) {
            this.committer = committer;
            return this;
        }

        public Builder committer(String name, String email) {
            return committer(new Committer(name, email));
        }

        public Builder branch(String branch) {
            this.branch = branch;
            return this;
        }

        public Create toCreate(String message) {
            return new Create(message, content, branch, committer);
        }

        public Update toUpdate(String message, String sha) {
            return new Update(message, content, branch, committer, sha);
        }

        public Delete toDelete(String message, String sha) {
            return new Delete(message, branch, committer, sha);
        }

        public static Builder fromPlainText(String content) {
            byte[] bytes = Objects.requireNonNull(content).getBytes(UTF_8);
            String encoded = Base64.getEncoder().encodeToString(bytes);
            return new Builder(encoded);
        }

        public static Builder noContent() {
            return new Builder(null);
        }

        public static Builder fromBase64Encoded(String content) {
            return new Builder(content);
        }
    }
}
