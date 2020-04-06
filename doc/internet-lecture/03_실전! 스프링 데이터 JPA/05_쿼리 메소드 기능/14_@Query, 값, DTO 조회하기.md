## @Query, 값, DTO 조회하기
- 쿼리를 통해 값이나 DTO 조회!
  - 실무에서 많이 사용하는 기능 중 하나!
  - @Query("select m.username from Member m") List<String> findUsernameList();
  - DTO 조회시 New 생성을 꼭 써야 한다.
    - @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t") List<MemberDto> findMemberDto();
- 사실 뭐 이것도... QueryDSL.... 짱짱    