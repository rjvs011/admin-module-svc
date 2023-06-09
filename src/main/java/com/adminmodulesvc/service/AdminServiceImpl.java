package com.adminmodulesvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminmodulesvc.dao.AdminRepository;
import com.adminmodulesvc.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminRepository adminRepository;

	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Admin getAdminById(Long id) {
		return adminRepository.findById(id).orElse(null);
	}

	@Override
	public Admin getAdminByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}
}
