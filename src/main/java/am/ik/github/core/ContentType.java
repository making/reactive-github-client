package am.ik.github.core;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContentType {
    FILE, DIR;

    @JsonCreator
    public static ContentType from(String s) {
        if (s == null) {
            return null;
        }
        return ContentType.valueOf(s.toUpperCase());
    }
}
