package com.project.gymmembership.service;

import com.project.gymmembership.dao.MemberDao;
import com.project.gymmembership.entity.Member;
import com.project.gymmembership.entity.MemberClassRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao){
        this.memberDao = memberDao;
    }


    @Override
    @Transactional
    public List<Member> findAll() {

        List<Member> members = memberDao.findAll();

        for(Member m : members ){
            checkActiveMembership(m);
        }

        return members;
    }

    @Override
    @Transactional
    public Member findById(int memberId) {

        Member member = memberDao.findById(memberId);

        checkActiveMembership(member);

        return member;
    }

    @Override
    @Transactional
    public void save(Member member) throws SQLIntegrityConstraintViolationException {
        memberDao.save(member);
        checkActiveMembership(member);
    }

    @Override
    @Transactional
    public void deleteById(int memberId) {
        memberDao.deleteById(memberId);
    }

    @Override
    @Transactional
    public void updateMember(Member member) {
        memberDao.updateMember(member);
        checkActiveMembership(member);
    }

    @Override
    @Transactional
    public List<Member> searchForMembers(String searchWord) {

        if (searchWord != null && searchWord.trim().length() > 0) {
            return memberDao.searchForMembers(searchWord.trim().toLowerCase());
        }

        return this.findAll();
    }


    private void checkActiveMembership(Member member) {
        if(member.getMemberClassRegistrations() != null){
            for(MemberClassRegistration mcr : member.getMemberClassRegistrations()) {
                if(mcr.getPaidUntil().after(Date.valueOf(LocalDate.now()))) {
                    member.setActiveMembership(true);
                    break;
                } else {
                    member.setActiveMembership(false);
                }
            }
        }
    }
}
