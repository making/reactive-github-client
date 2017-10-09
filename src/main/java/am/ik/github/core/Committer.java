package am.ik.github.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Committer {
    private final String name;
    private final String email;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final OffsetDateTime date;

    public Committer(String name,
                     String email) {
        this(name, email, null);
    }

    @JsonCreator
    public Committer(@JsonProperty("name") String name,
                     @JsonProperty("email") String email,
                     @JsonProperty("date") OffsetDateTime date) {
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Committer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
