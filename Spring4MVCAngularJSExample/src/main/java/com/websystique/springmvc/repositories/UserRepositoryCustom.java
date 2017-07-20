package com.websystique.springmvc.repositories;

import org.springframework.data.repository.NoRepositoryBean;

//@NoRepositoryBean
public interface UserRepositoryCustom {
	int updateAddress(String adress);
}
