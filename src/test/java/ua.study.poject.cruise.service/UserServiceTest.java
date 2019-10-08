package ua.study.poject.cruise.service;

import org.junit.Before;
import org.junit.Test;
import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.RoleDao;
import ua.study.poject.cruise.persistance.dao.UserDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.util.PasswordEncryptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    static AbstractDaoFactory daoFactory;
    static UserDao userDao;
    static RoleDao roleDao;

    @Before
    public void initBeforeEachMeth() throws GeneralCheckedException {
        User admin = getAdmin();
        User manager = getManager();
        User unregistred = getUnregistred();
        User emptyUser = getEmptyUser();

        userDao = mock(UserDao.class);

        when(userDao.findByLoginAndPassword("admin", new PasswordEncryptor().encode("admin"))).thenReturn(admin);
        when(userDao.findByLoginAndPassword("admin2", new PasswordEncryptor().encode("admin"))).thenReturn(emptyUser);
        when(userDao.findByLoginAndPassword("admin", new PasswordEncryptor().encode("admin2"))).thenReturn(emptyUser);
        when(userDao.findByLoginAndPassword("unregistred", new PasswordEncryptor().encode("unregistred"))).thenReturn(emptyUser);

        when(userDao.findByLogin("admin")).thenReturn(admin);
        when(userDao.findByLogin("admin2")).thenReturn(getEmptyUser());
        when(userDao.findByLogin("")).thenReturn(getEmptyUser());

        when(userDao.create(unregistred)).thenReturn(5);
        when(userDao.findByLoginAndPassword("manager", new PasswordEncryptor().encode("manager"))).thenReturn(manager);
        when(userDao.findByLoginAndPassword("manager2", new PasswordEncryptor().encode("manager"))).thenReturn(emptyUser);

        roleDao = mock(RoleDao.class);
        Role customerRole = new Role();
        customerRole.setId(3L);
        customerRole.setRole("customer");
        when(roleDao.findByRole(Role.ROLE_CUSTOMER)).thenReturn(customerRole);


        daoFactory = mock(AbstractDaoFactory.class);
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(daoFactory.getRoleDao()).thenReturn(roleDao);
    }


    @Test
    public void findUserByLoginOkTest() {
        User user = new UserService(daoFactory).findUserByLogin("admin");
        assertEquals(user, getAdmin());
    }

    @Test
    public void findUserByLoginIncorrectTest() {
        User user = new UserService(daoFactory).findUserByLogin("admin2");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginNullTest() {
        User user = new UserService(daoFactory).findUserByLogin(null);
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginEmptyTest() {
        User user = new UserService(daoFactory).findUserByLogin("");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginAndPasswordOkTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword("admin", "admin");
        assertEquals(user, getAdmin());
    }

    @Test
    public void findUserByLoginIncorrectAndPasswordTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword("admin2", "admin");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginAndPasswordIncorrectTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword("admin2", "admin");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginNullAndPasswordTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword(null, "admin");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginEmptyAndPasswordTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword("", "admin");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginAndPasswordNullTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword("admin", null);
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void findUserByLoginAndPasswordEmptyTest() {
        User user = new UserService(daoFactory).findUserByLoginPassword("admin", "");
        assertEquals(user, getEmptyUser());
    }

    @Test
    public void addNewUserOkExistTest() {
        int res = new UserService(daoFactory).addNewUser("manager", "manager", "John",
                "Klay", "borman33@ukr.net", "0445640788");
        assertEquals(-1, res);
    }

    @Test
    public void addNewUserOkTest() {
        int res = new UserService(daoFactory).addNewUser("unregistred", "unregistred", "John",
                "Doe", "doe@gmail.com", "0996661313");
        assertEquals(5, res);
    }




    private User getAdmin() {
        User admin = new User();
        admin.setId(1L);
        admin.setLogin("admin");
        admin.setPassword(new PasswordEncryptor().encode("admin"));
        admin.setFirstName("Alex");
        admin.setSecondName("Key");
        admin.setEmail("borman22@ukr.net");
        admin.setTel("0445640787");

        Role adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setRole("admin");
        admin.setRole(adminRole);
        return admin;
    }

    private User getManager() {
        User manager = new User();
        manager.setId(2L);
        manager.setLogin("manager");
        manager.setPassword(new PasswordEncryptor().encode("manager"));
        manager.setFirstName("John");
        manager.setSecondName("Klay");
        manager.setEmail("borman33@ukr.net");
        manager.setTel("0445640788");

        Role managerRole = new Role();
        managerRole.setId(2L);
        managerRole.setRole("manager");
        manager.setRole(managerRole);
        return manager;
    }

    private User getUnregistred() {
        User unregistred = new User();
        unregistred.setLogin("unregistred");
        unregistred.setPassword(new PasswordEncryptor().encode("unregistred"));
        unregistred.setFirstName("John");
        unregistred.setSecondName("Doe");
        unregistred.setEmail("doe@gmail.com");
        unregistred.setTel("0996661313");

        Role role = new Role();
        role.setId(3L);
        role.setRole("customer");
        unregistred.setRole(role);
        return unregistred;
    }

    private User getEmptyUser() {
        return new User();
    }

}