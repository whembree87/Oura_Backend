package com.oura.backend.repo;

import com.oura.backend.entity.KedHeavyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKedHeavy extends JpaRepository<KedHeavyEntity, Integer> {
    List<KedHeavyEntity> findAll();
}
