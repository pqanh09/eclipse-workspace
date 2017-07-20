package com.websystique.springmvc.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.User;

//@NoRepositoryBean
public interface UserRepository extends IGenericRepository<User, ObjectId>{
	List<User> findByAddress(String address);
	User findByUsername(String username);
//	@Query("{ 'name' : ?0 }")
//    Employee getEmployeeByName(String name);
//    
//    @Query(value="{ 'age' : ?0}", fields="{ 'name' : 1, 'id' : 1}")
//    List getEmployeeByAge(int age);
}
