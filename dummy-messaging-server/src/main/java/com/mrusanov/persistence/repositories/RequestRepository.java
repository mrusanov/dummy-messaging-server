package com.mrusanov.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrusanov.persistence.entities.RequestEntity;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

}
