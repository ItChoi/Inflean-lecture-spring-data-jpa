package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;
    /*
    // 생성자 하나만 딱 있으면, 스프링이 알아서 인젝션 해준다!
    private final EntityManager em;
    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
    }*/

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
