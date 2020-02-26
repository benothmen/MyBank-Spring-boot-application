package com.sid.dao;

import com.sid.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,String> {
}
