package com.taurus.db.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.taurus.db.model.User;

@Repository("UserRepository")
//@Component
public interface UserRepository extends GenericRepository<User, ObjectId> {
	User findFirstByUsername(String username);
}
