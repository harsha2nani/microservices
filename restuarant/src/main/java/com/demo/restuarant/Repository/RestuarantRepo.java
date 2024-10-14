package com.demo.restuarant.Repository;

import com.demo.restuarant.Model.RestuarantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestuarantRepo extends JpaRepository<RestuarantModel,Long> {
}
