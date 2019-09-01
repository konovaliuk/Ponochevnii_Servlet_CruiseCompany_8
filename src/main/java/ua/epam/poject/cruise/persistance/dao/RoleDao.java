package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Role findById(int id);

    Role findByRole(String roleName);

    public boolean isRoleExist(Role role);

    void close();
}
