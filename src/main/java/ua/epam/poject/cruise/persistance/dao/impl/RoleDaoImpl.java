package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Role;
import ua.epam.poject.cruise.persistance.dao.RoleDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    private Connection connection;
    private static final String FIND_ALL = "SELECT * FROM role";

    private static HashMap<Integer, Role> roles = new HashMap<>();
    private volatile static RoleDaoImpl instance;

    private RoleDaoImpl(Connection connection){
        this.connection = connection;
        fillRolesMap();
    }

    public static RoleDaoImpl getInstance(Connection connection){
        if(instance == null){
            synchronized (RoleDaoImpl.class){
                if(instance == null){
                    instance = new RoleDaoImpl(connection);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        list.addAll(roles.values());
        return list;
    }

    @Override
    public Role findById(int id) {
        Role role = new Role();
        if (roles.get(id) != null)
            role = roles.get(id);
        return role;
    }

    @Override
    public Role findByRole(String roleName) {
        Role role = new Role();
        if(roleName == null)
            return role;
        for (Role r : roles.values()) {
            if (r.getRole().equalsIgnoreCase(roleName))
                role = r;
        }
        return role;
    }

    public boolean isRoleExist(Role role){
        if(role.equals(roles.get(role.getId()))){
            return true;
        }
        return false;
    }

    private void fillRolesMap() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            Role tempRole = null;
            while (rs.next()) {
                tempRole = createRole(rs);
                roles.put(tempRole.getId(), tempRole);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private Role createRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
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
