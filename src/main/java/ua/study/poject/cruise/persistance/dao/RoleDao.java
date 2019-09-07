package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Role findById(Long id);

    Role findByRole(String roleName);

    boolean isRoleExist(Role role);

    void close();
}
