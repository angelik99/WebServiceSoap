package com.cidenet.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "type_document")
    private String typeDocument;
    @Id
    @Column(name = "num_document")
    private int numDocument;
    
    public void setName(String name) {
        this.name = name;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }
    public void setNumDocument(int numDocument) {
        this.numDocument = numDocument;
    }
    public String getName() {
        return name;
    }
    public String getTypeDocument() {
        return typeDocument;
    }
    public int getNumDocument() {
        return numDocument;
    }

    
}
