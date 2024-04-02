package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Cuisinier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuisinierRepository extends JpaRepository<Cuisinier,Integer> {
    Optional<?> findCuisinierByUser(Integer user);
}
