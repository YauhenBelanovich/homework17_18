package com.gmail.yauhen2012.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddDocumentDTO {

    @NotNull(message = "is required")
    @Size(min = 3, max = 100, message = "three characters or more")
    private String description;

    public AddDocumentDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
