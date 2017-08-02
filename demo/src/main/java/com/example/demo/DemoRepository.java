package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "demo", path = "demo")
public interface DemoRepository extends CrudRepository<Demo, Long>{

	Demo findById(@Param("id") Long id);

	Demo findByName(@Param("name") String name);

	Demo findByValue(@Param("value") String value);
	
	@Query("select d from Demo d where d.id = :id and d.name = :name and d.value = :value")
	Demo findByIdAndNameAndValue(@Param("id") Long id, @Param("name") String name, @Param("value")String value);
	
}
