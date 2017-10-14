package am.ik.github.repositories.commits;

import am.ik.github.core.Parent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class CommitsResponse {

    public static final class Commit extends Parent {
        private final String commentsUrl;
        private final am.ik.github.core.Commit commit;
        private final Committer author;
        private final Committer committer;
        private final List<Parent> parents;

        @JsonCreator
        public Commit(@JsonProperty("sha") String sha,
                      @JsonProperty("url") String url,
                      @JsonProperty("html_url") String htmlUrl,
                      @JsonProperty("comments_url") String commentsUrl,
                      @JsonProperty("commit") am.ik.github.core.Commit commit,
                      @JsonProperty("author") Committer author,
                      @JsonProperty("committer") Committer committer,
                      @JsonProperty("parents") List<Parent> parents) {
            super(sha, url, htmlUrl);
            this.commentsUrl = commentsUrl;
            this.commit = commit;
            this.author = author;
            this.committer = committer;
            this.parents = parents;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public am.ik.github.core.Commit getCommit() {
            return commit;
        }

        public Committer getAuthor() {
            return author;
        }

        public Committer getCommitter() {
            return committer;
        }

        public List<Parent> getParents() {
            return parents;
        }

        @Override
        public String toString() {
            return "Commit{" +
                    "commentsUrl='" + commentsUrl + '\'' +
                    ", commit=" + commit +
                    ", author=" + author +
                    ", committer=" + committer +
                    ", parents=" + parents +
                    '}';
        }
    }

    public static final class Committer {
        private final String login;
        private final String id;
        private final String avatarUrl;
        private final String gravaterId;
        private final String url;
        private final String htmlUrl;
        private final String followersUrl;
        private final String followingUrl;
        private final String gistsUrl;
        private final String starredUrl;
        private final String subscriptionsUrl;
        private final String organizationsUrl;
        private final String reposUrl;
        private final String eventsUrl;
        private final String receivedEventsUrl;
        private final String type;
        private final boolean siteAdmin;

        public Committer(@JsonProperty("login") String login,
                         @JsonProperty("id") String id,
                         @JsonProperty("avatar_url") String avatarUrl,
                         @JsonProperty("gravater_id") String gravaterId,
                         @JsonProperty("url") String url,
                         @JsonProperty("html_url") String htmlUrl,
                         @JsonProperty("followers_url") String followersUrl,
                         @JsonProperty("following_url") String followingUrl,
                         @JsonProperty("gists_url") String gistsUrl,
                         @JsonProperty("starred_url") String starredUrl,
                         @JsonProperty("subscriptions_url") String subscriptionsUrl,
                         @JsonProperty("organizations_url") String organizationsUrl,
                         @JsonProperty("repos_url") String reposUrl,
                         @JsonProperty("events_url") String eventsUrl,
                         @JsonProperty("received_events_url") String receivedEventsUrl,
                         @JsonProperty("type") String type,
                         @JsonProperty("site_admin") boolean siteAdmin) {
            this.login = login;
            this.id = id;
            this.avatarUrl = avatarUrl;
            this.gravaterId = gravaterId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        public String getLogin() {
            return login;
        }

        public String getId() {
            return id;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getGravaterId() {
            return gravaterId;
        }

        public String getUrl() {
            return url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public boolean isSiteAdmin() {
            return siteAdmin;
        }

        @Override
        public String toString() {
            return "Committer{" +
                    "login='" + login + '\'' +
                    ", id='" + id + '\'' +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    ", gravaterId='" + gravaterId + '\'' +
                    ", url='" + url + '\'' +
                    ", htmlUrl='" + htmlUrl + '\'' +
                    ", followersUrl='" + followersUrl + '\'' +
                    ", followingUrl='" + followingUrl + '\'' +
                    ", gistsUrl='" + gistsUrl + '\'' +
                    ", starredUrl='" + starredUrl + '\'' +
                    ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                    ", organizationsUrl='" + organizationsUrl + '\'' +
                    ", reposUrl='" + reposUrl + '\'' +
                    ", eventsUrl='" + eventsUrl + '\'' +
                    ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                    ", type='" + type + '\'' +
                    ", siteAdmin=" + siteAdmin +
                    '}';
        }
    }
}