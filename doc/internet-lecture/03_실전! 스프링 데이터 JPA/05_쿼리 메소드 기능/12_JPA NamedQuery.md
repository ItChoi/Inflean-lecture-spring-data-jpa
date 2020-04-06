## JPA NamedQuery
- 실무에서 쓸 일이 없어서 편~하게 듣자. 거의 사용 안한다...... 
- 쿼리에 이름을 부여하고 호출하는 기능!
- @Entity에 @NamedQuery(name="", query="""), 사용하는 service에서 em.createNamedQuery 사용
- 바로 사용하는 법 -> @Query(name = "Member.findByUsername") List<Member> findByUsername(@Param("username") String username);
  - List<Member> findByUsername(@Param("username") String username); -> 이렇게 해도 동작 한다.
    - 관례가 있다. 존재하는 네임드 쿼리를 찾고, 없으면 우리가 아는 메소드명으로 쿼리를 날린다
- 네임드 쿼리의 큰 장점이 있다!
  - 애플리케이션 로딩 시점에 한 번 파싱하고, 에러가 있으면, 문법 오류를 알려준다.
  - 정적 쿼리기 떄문에 로딩 시점에 오류를 잡을 수 있다.
  