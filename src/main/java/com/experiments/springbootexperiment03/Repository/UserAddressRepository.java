package com.experiments.springbootexperiment03.Repository;
import com.experiments.springbootexperiment03.entity.*;
import com.experiments.springbootexperiment03.Repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserAddressRepository extends JpaRepository<UserAddress,Integer> {
    @Query("SELECT  ua FROM UserAddress ua WHERE ua.user.name=:name AND ua.address.detail=:detail")
    List<UserAddress> list(@Param("name")String name,@Param("detail")String detail);
}
