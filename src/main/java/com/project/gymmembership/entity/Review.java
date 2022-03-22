package com.project.gymmembership.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "message")
    private String message;

    @ManyToOne()
    @JoinColumn(name = "class_id")
    private Class theClass;


    public Review(){}


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class getTheClass() {
        return theClass;
    }

    public void setTheClass(Class theClass) {
        this.theClass = theClass;
    }
}
