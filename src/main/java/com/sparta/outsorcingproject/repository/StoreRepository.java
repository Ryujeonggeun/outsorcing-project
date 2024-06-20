package com.sparta.outsorcingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.outsorcingproject.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
