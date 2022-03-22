package com.project.gymmembership.entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberClassRegistrationID implements Serializable {

    private Member member;

    private Class theClass;

    public MemberClassRegistrationID(){}

    public MemberClassRegistrationID(Member member, Class theClass){
        this.member = member;
        this.theClass = theClass;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberClassRegistrationID that = (MemberClassRegistrationID) o;
        return member.equals(that.member) && Objects.equals(theClass, that.theClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, theClass);
    }
}
