package com.project.gymmembership.service;

import com.project.gymmembership.dao.ClassDao;
import com.project.gymmembership.dao.MemberClassRegistrationDao;
import com.project.gymmembership.dao.MemberDao;
import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.MemberClassRegistration;
import com.project.gymmembership.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class MemberMemberClassRegistrationServiceImpl implements MemberClassRegistrationService {

    private final MemberClassRegistrationDao memberClassRegistrationDao;
    private final MemberDao memberDao;
    private final ClassDao classDao;

    @Autowired
    public MemberMemberClassRegistrationServiceImpl(MemberClassRegistrationDao memberClassRegistrationDao,
                                                    MemberDao memberDao, ClassDao classDao){
        this.memberClassRegistrationDao = memberClassRegistrationDao;
        this.memberDao = memberDao;
        this.classDao = classDao;
    }


    @Override
    @Transactional
    public List<MemberClassRegistration> findAll() {
        return null;
    }

    @Override
    @Transactional
    public MemberClassRegistration findByCompositePrimaryKey(Member member, Class theClass) {
        return memberClassRegistrationDao.findByCompositePrimaryKey(member, theClass);
    }

    @Override
    @Transactional
    public void save(MemberClassRegistration memberClassRegistration) throws EntityExistsException {
        memberClassRegistrationDao.save(memberClassRegistration);
    }

    @Override
    @Transactional
    public void deleteByCompositePrimaryKey(int memberId, int classId) {

        Member member = memberDao.findById(memberId);
        Class theClass = classDao.findById(classId);

        memberClassRegistrationDao.deleteByCompositePrimaryKey(member, theClass);
    }

    @Override
    @Transactional
    public List<MemberClassRegistration> getClassRegistrationsForMember(Member member) {

        List<MemberClassRegistration> registrations = memberClassRegistrationDao.getClassRegistrationsForMember(member);

        if (registrations.size() > 0){
            for(MemberClassRegistration mcr : registrations){
                if (mcr.getPaidUntil().after(Date.valueOf(LocalDate.now()))){
                    mcr.getMember().setActiveMembership(true);
                    return registrations;
                }
            }
        }

        member.setActiveMembership(false);
        return registrations;
    }

    @Override
    @Transactional
    public void update(MemberClassRegistration memberClassRegistration) {
        memberClassRegistrationDao.update(memberClassRegistration);
    }
}

