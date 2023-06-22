package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.King;
public interface KingRepository extends JpaRepository<King, Long> {

    //Derived query methods built into our repository. How cool!
    List<King> findByIsHeathen(boolean isHeathen);
    List<King> findByTitleContaining(String title);
}
