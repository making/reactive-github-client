package am.ik.github.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tree {
    private final String sha;
    private final String url;

    @JsonCreator
    public Tree(@JsonProperty("sha") String sha,
                @JsonProperty("url") String url) {
        this.sha = sha;
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
