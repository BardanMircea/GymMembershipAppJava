package com.project.gymmembership.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "theClass", cascade = CascadeType.ALL)
    private List<MemberClassRegistration> memberClassRegistrations;

    @OneToMany(mappedBy = "theClass", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "members_classes",
                joinColumns = @JoinColumn(name = "class_id"),
                inverseJoinColumns = @JoinColumn(name = "member_id")
                )
    private List<Member> members;


    public Class(){
        members = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<MemberClassRegistration> getMemberCourseRegistrations() {
        return memberClassRegistrations;
    }

    public void setMemberCourseRegistrations(List<MemberClassRegistration> memberClassRegistrations) {
        this.memberClassRegistrations = memberClassRegistrations;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return description + " / " + cost + " €";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return getId() == aClass.getId() && Double.compare(aClass.getCost(), getCost()) == 0 && getDescription().equals(aClass.getDescription()) && getInstructor().equals(aClass.getInstructor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCost(), getInstructor());
    }
}
