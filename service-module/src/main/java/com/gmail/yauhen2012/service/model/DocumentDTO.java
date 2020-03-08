package com.gmail.yauhen2012.service.model;

public class DocumentDTO {

    private Integer id;
    private String uniqueNumber;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id=" + id +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
