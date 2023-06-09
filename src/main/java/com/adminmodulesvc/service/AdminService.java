package com.adminmodulesvc.service;

import java.util.List;

import com.adminmodulesvc.model.Admin;

public interface AdminService {
	Admin saveAdmin(Admin admin);

	List<Admin> getAllAdmins();

	Admin getAdminById(Long id);

	Admin getAdminByUsername(String username);

	void deleteAdmin(Long id);
}
