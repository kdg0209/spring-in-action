package com.springinaction.taco.repository;

import com.springinaction.taco.entity.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
