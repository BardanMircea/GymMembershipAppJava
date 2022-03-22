package com.project.gymmembership.service;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.MemberClassRegistration;
import com.project.gymmembership.entity.Member;


import java.util.List;

public interface MemberClassRegistrationService {
    List<MemberClassRegistration> findAll();

    MemberClassRegistration findByCompositePrimaryKey(Member member, Class theClass);

    void save(MemberClassRegistration memberClassRegistration);

    void deleteByCompositePrimaryKey(int memberId, int classId);

    List<MemberClassRegistration> getClassRegistrationsForMember(Member member);

    void update(MemberClassRegistration memberClassRegistration);
}
