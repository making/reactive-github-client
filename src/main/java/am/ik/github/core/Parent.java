package am.ik.github.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
    private final String sha;
    private final String url;
    private final String htmlUrl;

    @JsonCreator
    public Parent(@JsonProperty("sha") String sha,
                  @JsonProperty("url") String url,
                  @JsonProperty("html_url") String htmlUrl) {
        this.sha = sha;
        this.url = url;
        this.htmlUrl = htmlUrl;
    }


    @Override
    public String toString() {
        return "Parent{" +
                "sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}';
    }
}
