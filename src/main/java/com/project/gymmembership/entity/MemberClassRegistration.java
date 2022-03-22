package com.project.gymmembership.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(MemberClassRegistrationID.class)
@Table(name = "member_class_registrations")
public class MemberClassRegistration {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;

    @Id
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne()
    @JoinColumn(name = "class_id")
    private Class theClass;

    @Column(name = "paid_on")
    private Date paidOn;

    @Column(name = "paid_until")
    private Date paidUntil;


    public MemberClassRegistration(){}


    public MemberClassRegistration(Member member, Class theClass, Date paidOn, Date paidUntil){
        this.member = member;
        this.theClass = theClass;
        this.paidOn = paidOn;
        this.paidUntil = paidUntil;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Class getTheClass() {
        return theClass;
    }

    public void setTheClass(Class theClass) {
        this.theClass = theClass;
    }

    public Date getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(Date paidOn) {
        this.paidOn = paidOn;
    }

    public Date getPaidUntil() {
        return paidUntil;
    }

    public void setPaidUntil(Date paidUntil) {
        this.paidUntil = paidUntil;
    }


}
