# 나머지 기능들
- 실무에서 많이 쓰이지 않는다. 다른 좋은 대안들이 많다. 그래도 알고 있으면 편리하게 가끔식 사용 가능하다!
  - Specifications (명세)
  - Query By Example
  - Projections
  - 네이티브 쿼리
    - 그래도 이건 좀 들어두면 좋다.
  
## Specifications (명세)
- 책 도메인 주도 설계는 Specification(명세)를 소개 한다.
- JPA Criteria (스프링 데이터 JPA)
  - 실무에서 조금만 복잡해지면, 가독성이 굉장히 떨어진다. 실무에서 거의 금지!
  - 이런게 있구나~ 정도만!
  - Repository(interface)에 extends JpaSpecificationExecutor<Entity> 상속
    - findOne, findAll 제공하는데 Specification을 파라미터로 받고 있구나~
    - 자바 코드로 쿼리를 짤 수 있꾸나~ 라는 장점! (다른 효율적인 대안이 많다.)
      - 직관적이지 않다.
- 실무에서는 JPA Criteria 대신 QueryDSL을 사용!!!!!!!!!!!!!!!!!!!!!!!!