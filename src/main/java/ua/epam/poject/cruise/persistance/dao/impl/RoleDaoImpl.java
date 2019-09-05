package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Role;
import ua.epam.poject.cruise.persistance.dao.RoleDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    private Connection connection;
    private static final String FIND_ALL = "SELECT * FROM role";

    private static Map<Long, Role> roles = null;

    public RoleDaoImpl(Connection connection) {
        this.connection = connection;
        if (roles == null)
            fillRolesMap();
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(roles.values());
    }

    @Override
    public Role findById(Long id) {
        Role role = new Role();
        if (roles.get(id) != null)
            role = roles.get(id);
        return role;
    }

    @Override
    public Role findByRole(String roleName) {
        Role role = new Role();
        if (roleName == null)
            return role;
        for (Role r : roles.values()) {
            if (r.getRole().equalsIgnoreCase(roleName))
                role = r;
        }
        return role;
    }

    public boolean isRoleExist(Role role) {
        return role.equals(roles.get(role.getId()));
    }

    private synchronized void fillRolesMap() {
        if (roles == null) {
            roles = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(FIND_ALL);
                Role tempRole;
                while (rs.next()) {
                    tempRole = createRole(rs);
                    roles.put(tempRole.getId(), tempRole);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    private Role createRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setRole(rs.getString("role"));
        return role;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close connection ", e);
            }
        }
    }
}
