package org.trams.hello.web.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bryanlee on 4/24/17.
 */
public class OtherCertificate {

    private Integer         id;
    private MultipartFile   file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
