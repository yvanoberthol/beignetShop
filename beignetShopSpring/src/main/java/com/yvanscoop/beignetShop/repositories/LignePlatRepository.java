package com.yvanscoop.beignetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvanscoop.beignetShop.entities.LignePlat;

@Repository
public interface LignePlatRepository extends JpaRepository<LignePlat, Long> {

}
