package com.experiments.springbootexperiment03.Repository;
import com.experiments.springbootexperiment03.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address,String> {
    @Query("SELECT a FROM Address a WHERE a.detail=:detail")
    List<Address> list(@Param("detail")String detail);
}
