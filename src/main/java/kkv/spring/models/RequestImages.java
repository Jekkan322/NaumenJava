package kkv.spring.models;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Image {
    private String path;

    public String getPath() {
        if(path==null) return null;
        var result = path.split("/");

        return String.join("\\",result);
    }

    public void setPath(String path) {
        this.path = path;
    }
}
