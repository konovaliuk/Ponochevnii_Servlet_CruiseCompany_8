package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.RoleDao;
import ua.study.poject.cruise.persistance.dao.UserDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;
import ua.study.poject.cruise.util.PasswordEncryptor;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements all the necessary logic for working with the User entity
 */
public class UserService {

    private AbstractDaoFactory daoFactory;
    private PasswordEncryptor encryptor = new PasswordEncryptor();

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public UserService() {
        daoFactory = MySqlDaoFactory.getInstance();
    }

    public UserService(AbstractDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * The method finds the user by login and password
     *
     * @param login
     * @param password
     * @return User or empty User if the search failed
     */
    public User findUserByLoginPassword(String login, String password) {
        User user = new User();
        if (login == null || login.equals("") || password == null || password.equals("")) {
            return user;
        }
        UserDao userDao = null;
        try {
            userDao = daoFactory.getUserDao();
            user = userDao.findByLoginAndPassword(login, encryptor.encode(password));
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null) {
                userDao.close();
            }
        }
        return user;
    }

    /**
     * The method finds the user by login
     *
     * @param login
     * @return User or empty User if the search failed
     */
    public User findUserByLogin(String login) {
        User user = new User();
        if (login == null || login.equals("")) {
            return user;
        }
        UserDao userDao = null;
        try {
            userDao = daoFactory.getUserDao();
            user = userDao.findByLogin(login);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null) {
                userDao.close();
            }
        }
        return user;
    }


    /**
     * The method adds (registers) a new user to the system
     *
     * @param login
     * @param password
     * @param firstName
     * @param secondName
     * @param email
     * @param tel
     * @return "User id"
     */
    public int addNewUser(String login, String password, String firstName, String secondName, String email, String tel) {
        UserDao userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDao();
            roleDao = daoFactory.getRoleDao();

            User user = userDao.findByLoginAndPassword(login, encryptor.encode(password));
            if (user.getId() != -1) {
                return -1;
            }

            user.setLogin(login);
            user.setPassword(encryptor.encode(password));
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setEmail(email);
            user.setTel(tel);
            user.setRole(roleDao.findByRole(Role.ROLE_CUSTOMER));

            return userDao.create(user);

        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null) {
                userDao.close();
            }
            if (roleDao != null) {
                roleDao.close();
            }
        }
        return -1;
    }

    /**
     * The method edits the user account. Old User and New User is the same user,
     * but in New User some fields are changed.
     * This method updates User to the New User state.
     * Login field cannot be changed
     *
     * @param oldUser Old User
     * @param newUser Same user as Old User, with modified data only
     * @return number of entries made
     */
    public int editAccount(User oldUser, User newUser) {
        if (newUser.getPassword().equals("")) {
            return -1;
        }
        if (!oldUser.getLogin().equals(newUser.getLogin())) {
            return -1;
        }
        UserDao userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDao();
            roleDao = daoFactory.getRoleDao();

            User userFromDB = userDao.findByLoginAndPassword(oldUser.getLogin(), oldUser.getPassword());
            if (userFromDB.getId() == -1) {
                return -1;
            }
            userFromDB.setPassword(newUser.getPassword());
            userFromDB.setFirstName(newUser.getFirstName());
            userFromDB.setSecondName(newUser.getSecondName());
            userFromDB.setEmail(newUser.getEmail());
            userFromDB.setTel(newUser.getTel());
            return userDao.update(newUser);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null) {
                userDao.close();
            }
            if (roleDao != null) {
                roleDao.close();
            }
        }
        return -1;
    }

    /**
     * The method allows the Admin to change the role of the selected User
     *
     * @param oldUser   selected User
     * @param adminUser Admin
     * @param newRole   new Role
     * @return number of entries made
     */
    public int changeUserRole(User oldUser, User adminUser, Role newRole) {
        if (oldUser == null || oldUser.getId() < 1 || oldUser.getLogin() == null || oldUser.getPassword() == null) {
            return -1;
        }

        if (adminUser == null || adminUser.getId() < 1 || adminUser.getLogin() == null || adminUser.getPassword() == null) {
            return -1;
        }

        User adminFromDB;
        UserDao userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDao();
            roleDao = daoFactory.getRoleDao();

            adminFromDB = userDao.findByLoginAndPassword(adminUser.getLogin(), adminUser.getPassword());
            if (adminFromDB.getId() == -1) {
                return -1;
            }

            if (!adminFromDB.getRole().equals(roleDao.findByRole(Role.ROLE_ADMIN))) {
                return -1;
            }

            if (!roleDao.isRoleExist(newRole)) {
                return -1;
            }

            oldUser.setRole(newRole);
            return userDao.update(oldUser);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null) {
                userDao.close();
            }
            if (roleDao != null) {
                roleDao.close();
            }
        }
        return -1;
    }


    public User fillFieldsUser(Long id, String login, String password, String firstName, String secondName, String email, String tel, Role role) {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(encryptor.encode(password));
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setTel(tel);
        user.setRole(role);
        return user;
    }

    /**
     * The method finds a Role by name
     *
     * @param roleName
     * @return Role
     */
    public Role findRoleByRoleName(String roleName) {
        RoleDao roleDao = null;
        Role role = new Role();
        try {
            roleDao = daoFactory.getRoleDao();
            role = roleDao.findByRole(roleName);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (roleDao != null) {
                roleDao.close();
            }
        }
        return role;
    }

    /**
     * The method finds all Roles in system
     *
     * @return
     */
    public List<Role> findAllRoles() {
        RoleDao roleDao = null;
        List<Role> list = new ArrayList<>();
        try {
            roleDao = daoFactory.getRoleDao();
            list = roleDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (roleDao != null) {
                roleDao.close();
            }
        }
        return list;
    }
}
