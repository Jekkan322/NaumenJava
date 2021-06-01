package kkv.spring.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.stream.Collectors;


public class RequestImages {
    private String path;

    public String getPath() {
        if(path==null) return null;
       /* var result = path.split("/");

        return String.join("\\",result);*/
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
