package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

// @Repository 생략 가능 ->
public interface TeamRepository extends JpaRepository<Team, Long> {

}
