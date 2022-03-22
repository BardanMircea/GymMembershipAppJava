package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public class MemberDaoImpl implements  MemberDao{

    private final EntityManager entityManager;


    @Autowired
    public MemberDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public List<Member> findAll(){
        Query query = entityManager.createQuery("select m from Member m order by m.lastName asc");

        return query.getResultList();
    }

    @Override
    public Member findById(int memberId) {

        return entityManager.find(Member.class, memberId);
    }

    @Override
    public void save(Member member) throws SQLIntegrityConstraintViolationException {
        entityManager.persist(member);
    }

    @Override
    public void deleteById(int memberId) {
        Query query = entityManager.createQuery("delete from Member m where m.id=:memberId");

        query.setParameter("memberId", memberId);

        query.executeUpdate();
    }

    @Override
    public void updateMember(Member member) {
        entityManager.merge(member);
    }

    @Override
    public List<Member> searchForMembers(String searchWord) {

        Query query = entityManager.createQuery("select m from Member m where m.firstName like :searchWord or" +
                                                        " m.lastName like :searchWord or m.email like :searchWord");
        query.setParameter("searchWord", "%" + searchWord + "%");

        List<Member> members = query.getResultList();

        return members;
    }
}
