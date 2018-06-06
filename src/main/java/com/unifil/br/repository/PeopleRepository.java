package com.unifil.br.repository;

import com.unifil.br.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long>{

}
