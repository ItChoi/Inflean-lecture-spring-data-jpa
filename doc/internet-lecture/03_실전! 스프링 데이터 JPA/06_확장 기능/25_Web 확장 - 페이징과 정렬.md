## Web 확장 - 페이징과 정렬
- 스프링 데이터는 페이징과 정렬을 웹에서 편리하게 사용 가능하도록 해준다.
- Pageable을 이용한 Page<Entity> 사용 시  쿼리스트링으로 page, size, sort를 조절 할 수 있다.
  - http://localhost:8080/members?page=2&size=3&sort=id,desc
  - default는 20개 데이터 가져온다.
    - spring:
        data:
          web:
            pageable:
              default-page-size: 10
              max-page-size: 2000
  - 해당 메소드에만 적용하기 위해서는 
    - public Page<Member> list(@PageableDefault(size = 5, sort = "username") Pageable pageable) { }
  - 20개 말고 가져오려면 글로벌 세팅하려면 된다. .yml에
- 스프링 데이터 JPA 기능이 있는데 부트가 알아서 자동으로 세팅해준다.
  - 컨트롤러에서, 파라미터들이 컨트롤러에서 바인딩 될 때 페이지에이블이 있으면, 페이지 리퀘스틀 생성해서 값을 채워서 pageable에 인잭션 해준다!
- 접두사
  - 한 페이지에 페이징이 두 개 있으면, 접두사로 구분 가능
    - member_page=0, ...
- Page를 1부터 시작하기
  - 스프링 데이터는 Page를 0부터 시작한다.
  - 두 가지 방법
    - Page를 직접 정의!! .....
    - spring.data.web.pageable.one-indexed-parameters를 true로 설정하면 된다. 근데 한계가 있다.
      - pageable 데이터랑 일치가 안된다.
  - 그냥 디폴트 0으로 쓰자!