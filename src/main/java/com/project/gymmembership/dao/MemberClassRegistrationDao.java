package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.MemberClassRegistration;
import com.project.gymmembership.entity.Member;

import java.util.List;

public interface MemberClassRegistrationDao {
    List<MemberClassRegistration> findAll();

    MemberClassRegistration findByCompositePrimaryKey(Member member, Class theClass);

    void save(MemberClassRegistration memberClassRegistration);

    void deleteByCompositePrimaryKey(Member member, Class theClass);

    List<MemberClassRegistration> getClassRegistrationsForMember(Member member);

    void update(MemberClassRegistration memberClassRegistration);
}
