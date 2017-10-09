package am.ik.github.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Content {
    private final String name;
    private final String path;
    private final String sha;
    private final String url;
    private final String gitUrl;
    private final String htmlUrl;
    private final String downloadUrl;
    private final String type;

    @JsonCreator
    public Content(@JsonProperty("name") String name,
                   @JsonProperty("path") String path,
                   @JsonProperty("sha") String sha,
                   @JsonProperty("url") String url,
                   @JsonProperty("git_url") String gitUrl,
                   @JsonProperty("html_url") String htmlUrl,
                   @JsonProperty("download_url") String downloadUrl,
                   @JsonProperty("type") String type) {
        this.name = name;
        this.path = path;
        this.sha = sha;
        this.url = url;
        this.gitUrl = gitUrl;
        this.htmlUrl = htmlUrl;
        this.downloadUrl = downloadUrl;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Content{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
