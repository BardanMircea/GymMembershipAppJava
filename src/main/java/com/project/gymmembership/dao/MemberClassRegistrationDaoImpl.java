package com.project.gymmembership.dao;
import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.MemberClassRegistration;

import com.project.gymmembership.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MemberClassRegistrationDaoImpl implements MemberClassRegistrationDao {

    private final EntityManager entityManager;

    @Autowired
    public MemberClassRegistrationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<MemberClassRegistration> findAll() {
        return null;
    }

    @Override
    public MemberClassRegistration findByCompositePrimaryKey(Member member, Class theCLass) {

        Query query = entityManager.createQuery("select mcr from MemberClassRegistration mcr where mcr.member=:member" +
                                                        " and mcr.theClass=:theClass");

        query.setParameter("member", member);
        query.setParameter("theClass", theCLass);

        return (MemberClassRegistration) query.getResultStream().findFirst().get();
    }

    @Override
    public void save(MemberClassRegistration memberClassRegistration) throws EntityExistsException {
        entityManager.persist(memberClassRegistration);
    }

    @Override
    public void deleteByCompositePrimaryKey(Member member, Class theClass) {
        Query query = entityManager.createQuery("delete from MemberClassRegistration mcr " +
                                                        "where mcr.member=:member and mcr.theClass=:theClass");
        query.setParameter("member", member);
        query.setParameter("theClass", theClass);

        query.executeUpdate();

    }

    @Override
    public List<MemberClassRegistration> getClassRegistrationsForMember(Member member){
        Query query = entityManager.createQuery("select mcr from MemberClassRegistration mcr where mcr.member=:member");

        query.setParameter("member", member);

        return query.getResultList();
    }

    @Override
    public void update(MemberClassRegistration memberClassRegistration) {
        entityManager.merge(memberClassRegistration);
    }
}
