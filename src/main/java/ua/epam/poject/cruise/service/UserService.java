package ua.epam.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Role;
import ua.epam.poject.cruise.entity.User;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.RoleDao;
import ua.epam.poject.cruise.persistance.dao.UserDao;
import ua.epam.poject.cruise.persistance.dao.impl.UserDaoImpl;
import ua.epam.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.epam.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;
import ua.epam.poject.cruise.service.constants.UserServiceConst;
import ua.epam.poject.cruise.util.PasswordEncryptor;

public class UserService {

    private AbstractDaoFactory daoFactory = new MySqlDaoFactory();
    private PasswordEncryptor encryptor = new PasswordEncryptor();

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    // войти
    public User findUserByLoginPassword(String login, String password){
        User user = new User();
        if(login == null || login.equals("") || password == null || password.equals(""))
            return user;
        try {
            UserDao userDao = daoFactory.getUserDaoImpl();
            user = userDao.findByLoginAndPassword(login, encryptor.encode(password));
            userDao.close();
        } catch (GeneralCheckedException e) {
            LOGGER.info(e);
        }
        return user;
    }

    public User findUserById(int id){
        User user = new User();
        if(id < 1) return user;

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
            if(user.getId() != -1)
                return UserServiceConst.USER_ALREADY_EXIST;

            user.setLogin(login);
            user.setPassword(encryptor.encode(password));
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setEmail(email);
            user.setTel(tel);
            user.setRole(roleDao.findByRole(Role.ROLE_CUSTOMER));

            int result = userDao.create(user);
            return result;

        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if(userDao != null)
                userDao.close();
            if(roleDao != null)
                roleDao.close();
        }
        return UserServiceConst.UNSUCCESSFULL_USER_CREATION;
    }

    public int editAccount(User oldUser, User newUser) {
        if(oldUser == null || oldUser.getId() == -1 || oldUser.getLogin() == null || oldUser.getPassword() == null)
            return UserServiceConst.USER_DOES_NOT_EXIST;

        if(newUser == null || newUser.getLogin() == null || newUser.getPassword() == null)
            return UserServiceConst.UNSUCCESSFULL_USER_UPDATE;

        if(oldUser.getLogin() != newUser.getLogin())
            return UserServiceConst.USER_CANNOT_CHANGE_LOGIN;
        UserDaoImpl userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDaoImpl();
            roleDao = daoFactory.getRoleDaoImpl();

            User userFromDB = userDao.findByLoginAndPassword(oldUser.getLogin(), oldUser.getPassword());
            if(userFromDB.getId() == -1)
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
            if(userDao != null)
                userDao.close();
            if(roleDao != null)
                roleDao.close();
        }
        return UserServiceConst.UNSUCCESSFULL_USER_CREATION;
    }

    public int changeUserRole(User oldUser, User adminUser, Role newRole){
        if(oldUser == null || oldUser.getId() == -1 || oldUser.getLogin() == null || oldUser.getPassword() == null)
            return UserServiceConst.USER_DOES_NOT_EXIST;

        if(adminUser == null || adminUser.getId() == -1 || adminUser.getLogin() == null || adminUser.getPassword() == null)
            return UserServiceConst.USER_DOES_NOT_EXIST;

        User adminFromDB = null;
        User oldUserFromDB = null;
        UserDaoImpl userDao = null;
        RoleDao roleDao = null;
        try {
            userDao = daoFactory.getUserDaoImpl();
            roleDao = daoFactory.getRoleDaoImpl();

            adminFromDB = userDao.findByLoginAndPassword(adminFromDB.getLogin(), adminFromDB.getPassword());
            if(adminFromDB.getId() == -1)
                return UserServiceConst.ADMIN_DOES_NOT_EXIST;

            if(!adminFromDB.getRole().equals(roleDao.findByRole(Role.ROLE_ADMIN)))
                return UserServiceConst.ADMIN_DOES_NOT_EXIST;

            if(!roleDao.isRoleExist(newRole))
                return UserServiceConst.ROLE_DOES_NOT_EXIST;

            oldUserFromDB.setRole(newRole);
            return userDao.update(oldUserFromDB);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if(userDao != null)
                userDao.close();
            if(roleDao != null)
                roleDao.close();
        }
        return UserServiceConst.UNSUCCESSFULL_ROLE_UPDATING;
    }

    public User fillFieldsUser(int id, String login, String password, String firstName, String secondName, String email, String tel, Role role){
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setTel(tel);
        user.setRole(role);
        return user;
    }
}
