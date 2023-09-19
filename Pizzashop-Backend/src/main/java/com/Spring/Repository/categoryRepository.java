package com.Spring.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Spring.entity.category;


@Repository   // denotes that it contains all crud methods
@EnableJpaRepositories  // to enable all the configurations and methods under jpa repository
public interface categoryRepository extends JpaRepository<category,Integer>
{

	@Query("select c from category c")
	Page<category> allcategories(Pageable pg);
	
	
}
