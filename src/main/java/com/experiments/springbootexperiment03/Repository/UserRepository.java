package com.experiments.springbootexperiment03.Repository;
import com.experiments.springbootexperiment03.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer>{
@Query("SELECT u FROM User u WHERE u.id=:id")
User find(@Param("id")int id);
}
