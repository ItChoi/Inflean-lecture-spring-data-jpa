package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.username = :username"
)
@ToString(of = {"id", "username", "age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team"))
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // JPA 표준 스펙에 엔티티를 만들 때, 기본 생성자가 있어야 하는데,
    // JPA가 프록시 기술을 쓸 때 private으로 막으면 막힐 수 있다. 따라서 protecjted하라고 스펙에 되어 있다.
    // protected Member() { }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
