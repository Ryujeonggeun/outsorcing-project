package com.sparta.outsorcingproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.outsorcingproject.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
