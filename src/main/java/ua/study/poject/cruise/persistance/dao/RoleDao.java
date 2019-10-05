package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Role;

import java.util.List;

/**
 * The interface RoleDao defines an interaction contract with a Role entity
 */
public interface RoleDao {

    /**
     * This method finds all the Roles
     *
     * @return List of Role
     */
    List<Role> findAll();

    /**
     * This method finds Role by "Role name"
     *
     * @param roleName
     * @return Role
     */
    Role findByRole(String roleName);

    /**
     * This method allows check if Role exist in system
     * @param role
     * @return true if Role exist
     */
    boolean isRoleExist(Role role);

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
