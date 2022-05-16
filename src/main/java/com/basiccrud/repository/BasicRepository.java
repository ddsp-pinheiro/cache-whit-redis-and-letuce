package com.basiccrud.repository;

import com.basiccrud.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicRepository extends JpaRepository<BasicEntity,Long> {
    Optional<BasicEntity> findById(Long id);
    Optional<BasicEntity> findByName(String name);
}
