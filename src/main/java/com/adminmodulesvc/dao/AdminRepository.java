package com.adminmodulesvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminmodulesvc.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByUsername(String username);
}
