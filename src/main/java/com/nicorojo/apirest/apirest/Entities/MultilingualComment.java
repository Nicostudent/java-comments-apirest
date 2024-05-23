package com.nicorojo.apirest.apirest.Entities;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;

@Entity
public class MultilingualComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String subject;
    private boolean positiveComment;

    @ElementCollection
    @MapKeyColumn(name = "language")
    private Map<String, String> response;


    public String getId() {
        return id;
    }

    public boolean isPositiveComment() {
        return positiveComment;
    }

    public void setPositiveComment(boolean positiveComment) {
        this.positiveComment = positiveComment;
    }

    public Map<String, String> getResponse() {
        return response;
    }

    public void setResponse(Map<String, String> response) {
        this.response = response;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    } 

}
