package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.RoleDao;
import ua.study.poject.cruise.persistance.dao.UserDao;
import ua.study.poject.cruise.persistance.dao.impl.UserDaoImpl;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;
import ua.study.poject.cruise.service.constants.UserServiceConst;
import ua.study.poject.cruise.util.PasswordEncryptor;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();
    private PasswordEncryptor encryptor = new PasswordEncryptor();

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    // войти
    public User findUserByLoginPassword(String login, String password) {
        User user = new User();
        if (login == null || login.equals("") || password == null || password.equals(""))
            return user;
        UserDao userDao = null;
        try {
            userDao = daoFactory.getUserDaoImpl();
            user = userDao.findByLoginAndPassword(login, encryptor.encode(password));
        } catch (GeneralCheckedException e) {
            LOGGER.info(e);
        } finally {
            if (userDao != null)
                userDao.close();
        }
        return user;
    }

    public User findUserById(Long id) {
        User user = new User();
        if (id < 1) return user;

        try {
            UserDao userDao = daoFactory.getUserDaoImpl();
            user = userDao.findById(id);
            userDao.close();
        } catch (GeneralCheckedException e) {
            LOGGER.info(e);
        }
        return user;
    }

    // зарегистрировать нового
    public int addNewUser(String login, String password, String firstName, String secondName, String email, String tel) {
        UserDaoImpl userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDaoImpl();
            roleDao = daoFactory.getRoleDaoImpl();

            User user = userDao.findByLoginAndPassword(login, encryptor.encode(password));
            if (user.getId() != -1)
                return UserServiceConst.USER_ALREADY_EXIST;

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
            if (userDao != null)
                userDao.close();
            if (roleDao != null)
                roleDao.close();
        }
        return UserServiceConst.UNSUCCESSFULL_USER_CREATION;
    }

    public int editAccount(User oldUser, User newUser) {
        if (oldUser == null || oldUser.getId() == -1 || oldUser.getLogin() == null || oldUser.getPassword() == null)
            return UserServiceConst.USER_DOES_NOT_EXIST;

        if (newUser == null || newUser.getLogin() == null || newUser.getPassword() == null)
            return UserServiceConst.UNSUCCESSFULL_USER_UPDATE;

        if (!oldUser.getLogin().equals(newUser.getLogin()))
            return UserServiceConst.USER_CANNOT_CHANGE_LOGIN;
        UserDaoImpl userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDaoImpl();
            roleDao = daoFactory.getRoleDaoImpl();

            User userFromDB = userDao.findByLoginAndPassword(oldUser.getLogin(), oldUser.getPassword());
            if (userFromDB.getId() == -1)
                return UserServiceConst.USER_DOES_NOT_EXIST;

            userFromDB.setPassword(newUser.getPassword());
            userFromDB.setFirstName(newUser.getFirstName());
            userFromDB.setSecondName(newUser.getSecondName());
            userFromDB.setEmail(newUser.getEmail());
            userFromDB.setTel(newUser.getTel());

            return userDao.update(newUser);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null)
                userDao.close();
            if (roleDao != null)
                roleDao.close();
        }
        return UserServiceConst.UNSUCCESSFULL_USER_CREATION;
    }

    public int changeUserRole(User oldUser, User adminUser, Role newRole) {
        if (oldUser == null || oldUser.getId() <= 1 || oldUser.getLogin() == null || oldUser.getPassword() == null)
            return UserServiceConst.USER_DOES_NOT_EXIST;

        if (adminUser == null || adminUser.getId() <= 1 || adminUser.getLogin() == null || adminUser.getPassword() == null)
            return UserServiceConst.USER_DOES_NOT_EXIST;

        User adminFromDB;
        UserDaoImpl userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDaoImpl();
            roleDao = daoFactory.getRoleDaoImpl();

            adminFromDB = userDao.findByLoginAndPassword(adminUser.getLogin(), adminUser.getPassword());
            if (adminFromDB.getId() == -1)
                return UserServiceConst.ADMIN_DOES_NOT_EXIST;

            if (!adminFromDB.getRole().equals(roleDao.findByRole(Role.ROLE_ADMIN)))
                return UserServiceConst.ADMIN_DOES_NOT_EXIST;

            if (!roleDao.isRoleExist(newRole))
                return UserServiceConst.ROLE_DOES_NOT_EXIST;

            oldUser.setRole(newRole);
            return userDao.update(oldUser);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userDao != null)
                    userDao.close();
            if (roleDao != null)
                    roleDao.close();
        }
        return UserServiceConst.UNSUCCESSFULL_ROLE_UPDATING;
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

    public Role findRoleByRoleName(String roleName) {
        RoleDao roleDao = null;
        Role role = new Role();
        try {
            roleDao = daoFactory.getRoleDaoImpl();
            role = roleDao.findByRole(roleName);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (roleDao != null)
                roleDao.close();
        }
        return role;
    }

    public List<Role> findAllRoles() {
        RoleDao roleDao = null;
        List<Role> list = new ArrayList<>();
        try {
            roleDao = daoFactory.getRoleDaoImpl();
            list = roleDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (roleDao != null)
                    roleDao.close();
        }
        return list;
    }
}
