package am.ik.github.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Commit extends Parent {
    private final Committer author;
    private final Committer committer;
    private final Tree tree;
    private final String message;
    private final Parent parent;

    @JsonCreator
    public Commit(
            @JsonProperty("sha") String sha,
            @JsonProperty("url") String url,
            @JsonProperty("html_url") String htmlUrl,
            @JsonProperty("author") Committer author,
            @JsonProperty("committer") Committer committer,
            @JsonProperty("tree") Tree tree,
            @JsonProperty("message") String message,
            @JsonProperty("parent") Parent parent) {
        super(sha, url, htmlUrl);
        this.author = author;
        this.committer = committer;
        this.tree = tree;
        this.message = message;
        this.parent = parent;
    }

    public Committer getAuthor() {
        return author;
    }

    public Committer getCommitter() {
        return committer;
    }

    public Tree getTree() {
        return tree;
    }

    public String getMessage() {
        return message;
    }

    public Parent getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "author=" + author +
                ", committer=" + committer +
                ", tree=" + tree +
                ", message='" + message + '\'' +
                ", parent=" + parent +
                '}';
    }
}
