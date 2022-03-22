package com.project.gymmembership.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="members",
        uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone"}))
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name="active_membership")
    private boolean activeMembership;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberClassRegistration> memberClassRegistrations;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "members_classes",
                joinColumns = @JoinColumn(name = "member_id"),
                inverseJoinColumns = @JoinColumn(name = "class_id")
                )
    private List<Class> classes;


    public Member(){
        this.classes = new ArrayList<>();
    }


    public String getPhone() {
        return phone;
    }

    public List<MemberClassRegistration> getMemberClassRegistrations() {
        return memberClassRegistrations;
    }

    public void setMemberClassRegistrations(List<MemberClassRegistration> memberClassRegistrations) {
        this.memberClassRegistrations = memberClassRegistrations;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActiveMembership() {
        return activeMembership;
    }

    public void setActiveMembership(boolean activeMembership) {
        this.activeMembership = activeMembership;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
