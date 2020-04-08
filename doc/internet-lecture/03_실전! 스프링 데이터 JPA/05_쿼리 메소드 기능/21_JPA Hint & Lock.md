## JPA Hint & Lock
- JPA Hint
  - JPA 쿼리 힌트(SQL 힌트가 아니라 JPA 구현체에게 제공하는 힌트)
- 더티체킹 자체가 두 개의 데이터를 통해 변경을 감지하여 업데이트를 해주는데, 만약 "나는 변경 안할꺼야!" 한다면, 이미 findById 할 때 원본이랑 템프를 가져오는데, JPA 표준은 제공안하고 Hibernate가 제공한다!
  - @QueryHints(value = @QueryHint(name ="org.hibernate.readOnly", value = "true")) Member findByReadOnlyByUsername(String username);
  - readOnly로 주면 변경감지 체크를 안한다, 읽기로 쓰인다고 가정하고 조회만!
  - 딱히 쓸 일은 없다....
  - 성능 테스트를 해 보고 쓸 지 정한다. 
  - 이거로 인해 얻을 수 있는 이점은 크지 않다.
  - 레디스 없이 버틸 수 있는데 이거 정도만 넣어서 튜닝 해보면 괜찮을거 같다 생각이 들면 사용해보는 거도 나쁘지 않다.
- Lock (JPA 지원)
  - DB select 할 때, 다른 애들은 손대지마! 하고 Lock을 걸어줄 수 있다.
  - @Lock(LockModeType.PESSIMISTIC_WRITE) List<Member> findLockByUsername(String username);
  - 실시간 서비스가 많은 웹에서는 웬만하면 Lock을 걸면 안된다.
  