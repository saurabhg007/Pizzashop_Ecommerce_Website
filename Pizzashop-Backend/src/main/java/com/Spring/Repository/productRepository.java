package com.Spring.Repository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.stereotype.Repository;

import com.Spring.entity.product;


@Repository   // denotes that it contains all crud methods
@EnableJpaRepositories  // to enable all the configurations and methods under jpa repository
public interface productRepository extends JpaRepository<product,Integer>
{

	
	
	
}

