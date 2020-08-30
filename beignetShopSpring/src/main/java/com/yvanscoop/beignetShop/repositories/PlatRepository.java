package com.yvanscoop.beignetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvanscoop.beignetShop.entities.Plat;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long>{

}
