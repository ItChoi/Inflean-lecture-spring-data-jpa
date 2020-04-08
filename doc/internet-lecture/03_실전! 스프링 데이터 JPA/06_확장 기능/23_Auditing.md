## Auditing
- 엔티티를 생성, 변경할 때 변경한 사람과 시간을 추적하고 싶다면?
  - 등록일
  - 수정일
  - 등록자
  - 수정자

- 순수 JPA 사용
```java
public class JpaBaseEntity {
    @Column(updatable = false, insertable = true)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // 펄시스트하기 전에 이벤트 발생
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}

class Member extends JpaBaseEntity { }
```
- 이렇게 하고 실행하면 Member에 원하는 컬럼이 들어가지 않는다.
  - JPA에서 진짜 상속 관계가 있고, 속성만 가져와서 사용하는 상속관계가 있다.
  - @MappedSuperclass : 진짜 상속 관계가 아니고, 속성들을 컬럼으로 같이 쓸 수 있게 공유, JpaBaseEntity에 추가

- 스프링 데이터 JPA 사용
  - 더 깔끔하게 사용하는 방식을 제공한다!
  - 설정 필수로 넣어야 한다. 
    - @EnableJpaAuditing -> 스프링 부트 설정 클래스에 적용
    - @EntityListeners(AuditingEntityListener.class) -> 엔티티에 적용
  - 사용 어노테이션
    - @CreatedDate
    - @LastModifiedDate
    - @CreatedBy
    - @LastModifiedBy 
   
```java
//@EnableJpaAuditing(modifyOnCreate = false) // 업데이트 시 null로 들어간다 (비권장) 
@EnableJpaAuditing(
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        // 실제로는 세션 정보를 가져와서 아이디를 꺼내서 넣어준다.
        // HttpSession에서 꺼내던가.
        return () -> Optional.of(UUID.randomUUID().toString());
    }
}


@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    // 값이 어떻게 들어갈까? application.java에 @Bean 설정 필요
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}

```
- @EntityListeners(AuditingEntityListener.class)를 생략하고 스프링 데이터 JPA가 제공하는 이벤트를 엔티티 전체에 적용하려면 orm.xml에 다음과 같이 등록!
  - META-INF/orm.xml
  - JPA 스펙에 있는 기능 (글로벌 적용)
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm" 
                xmlns:xsi="http://w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http:/xmlns.jcp.org/xml/ns/persistence/orm http://xmlns.jcp.org/xml/ns/persistence/org_2_2.xsd"
                version="2.2">
    
    <persistence-unit-metadata>
        <persistence-unit-default>
            <entity-listener>
                <entity-listener class="org.springframework.data.jpa.domain.support.AuditingEntityListener"
            </entity-listener>
        </persistence-unit-default>
    </persistence-unit-metadata>


</entity-mappings>
```