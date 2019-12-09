package com.badumbl.petshop.dao;

import com.badumbl.petshop.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
