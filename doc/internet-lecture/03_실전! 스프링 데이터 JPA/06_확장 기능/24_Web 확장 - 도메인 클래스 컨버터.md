## Web 확장 - 도메인 클래스 컨버터
- 권장하진 않는다. 
  - pk를 외부에 공개...
  - 복잡해지면 이걸 못 쓴다.
  - Http 요청은 회원 id를 받지만, 도메인 클래스 컨버터가 중간에 동작해서 회원 엔티티 객체를 반환
  - 주의: 조회용으로만 사용해야 한다. 트랜잭션 범위가 없기에 영속성 컨텍스트가 애매하다. (설정에 따라 다름)
```java
@GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        // 스프링 부트를 쓰기에 스프링 데이터 JPA가 알아서 해당 id의 member를 찾아서 리턴해준다.
        return member.getUsername();
    }
```