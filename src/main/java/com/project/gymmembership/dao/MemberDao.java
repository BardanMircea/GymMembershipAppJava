package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Member;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface MemberDao {

    List<Member> findAll();

    Member findById(int memberId);

    void save (Member member) throws SQLIntegrityConstraintViolationException;

    void deleteById(int memberId);

    void updateMember(Member member);

    List<Member> searchForMembers(String searchWord);
}
