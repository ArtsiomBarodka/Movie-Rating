package epam.my.project.service.impl;

import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.UserDAO;
import epam.my.project.exception.DataStorageException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Account;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.UserForm;
import epam.my.project.model.validation.Violations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDAO.class, AccountDAO.class})
public class UserServiceImplTest {
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userDAO = mock(UserDAO.class);
        accountDAO = mock(AccountDAO.class);
        userService = new UserServiceImpl(userDAO, accountDAO);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetUserById_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int userId = 1;
        when(userDAO.getUserById(anyInt())).thenThrow(new DataStorageException(""));
        userService.getUserById(userId);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetUserById_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int userId = 1;
        when(userDAO.getUserById(anyInt())).thenReturn(Optional.ofNullable(null));
        userService.getUserById(userId);
    }

    @Test
    public void testGetUserById_SHOULD_RETURN_USER() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int userId = 1;
        when(userDAO.getUserById(anyInt()))
                .thenReturn(Optional.ofNullable(mock(User.class)));
        User result = userService.getUserById(userId);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetUserByUId_NULL_MOVIE_UID_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        String userUId = null;
        userService.getUserByUId(userUId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetUserByUId_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        String userUId = "";
        when(userDAO.getUserByUId(anyString())).thenThrow(new DataStorageException(""));
        userService.getUserByUId(userUId);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetUserByUId_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        String userUId = "";
        when(userDAO.getUserByUId(anyString())).thenReturn(Optional.ofNullable(null));
        userService.getUserByUId(userUId);
    }

    @Test
    public void testGetUserByUId_SHOULD_RETURN_USER() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        String userUId = "";
        when(userDAO.getUserByUId(anyString()))
                .thenReturn(Optional.ofNullable(mock(User.class)));
        User result = userService.getUserByUId(userUId);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetUserByName_NULL_NAME_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        String name = null;
        userService.getUserByName(name);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetUserByName_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        String name = "";
        when(userDAO.getUserByName(anyString())).thenThrow(new DataStorageException(""));
        userService.getUserByName(name);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetUserByName_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        String name = "";
        when(userDAO.getUserByName(anyString())).thenReturn(Optional.ofNullable(null));
        userService.getUserByName(name);
    }

    @Test
    public void testGetUserByName_SHOULD_RETURN_USER() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        String name = "";
        when(userDAO.getUserByName(anyString()))
                .thenReturn(Optional.ofNullable(mock(User.class)));
        User result = userService.getUserByName(name);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetUserByAccountId_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int accountId = 1;
        when(userDAO.getUserByAccountId(anyInt())).thenThrow(new DataStorageException(""));
        userService.getUserByAccountId(accountId);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetUserByAccountId_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int accountId = 1;
        when(userDAO.getUserByAccountId(anyInt())).thenReturn(Optional.ofNullable(null));
        userService.getUserByAccountId(accountId);
    }

    @Test
    public void testGetUserByAccountId_SHOULD_RETURN_USER() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int accountId = 1;
        when(userDAO.getUserByAccountId(anyInt()))
                .thenReturn(Optional.ofNullable(mock(User.class)));
        User result = userService.getUserByAccountId(accountId);

        assertNotNull(result);
    }


    @Test(expected = InternalServerErrorException.class)
    public void testCreateUser_NULL_ACCOUNT_NAME_PARAMETER() throws InternalServerErrorException {
        int accountId = 1;
        String accountName = null;
        userService.createUser(accountId, accountName);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateUser_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        int accountId = 1;
        String accountName = "";
        when(userDAO.createUser(any(User.class))).thenThrow(new DataStorageException(""));
        userService.createUser(accountId, accountName);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateUser_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, DataStorageException {
        int accountId = 1;
        String accountName = "";
        when(userDAO.createUser(any(User.class))).thenReturn(1);
        when(userDAO.getUserByAccountId(anyInt())).thenReturn(Optional.ofNullable(null));
        userService.createUser(accountId, accountName);
    }

    @Test
    public void testCreateUser_SHOULD_RETURN_NEW_USER() throws InternalServerErrorException, DataStorageException {
        int accountId = 1;
        String accountName = "";
        when(userDAO.createUser(any(User.class)))
                .thenReturn(1);
        when(userDAO.getUserByAccountId(anyInt()))
                .thenReturn(Optional.ofNullable(mock(User.class)));
        User result = userService.createUser(accountId, accountName);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateUser_NULL_USER_FORM_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        UserForm userForm = null;
        int userId = 1;
        userService.updateUser(userForm, userId);
    }

    @Test(expected = ValidationException.class)
    public void testUpdateUser_USER_FORM_HAS_ERROR() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        UserForm userForm = mock(UserForm.class);
        int userId = 1;
        when(userForm.getViolations()).thenReturn(mock(Violations.class));
        when(userForm.getViolations().hasErrors()).thenReturn(true);
        userService.updateUser(userForm, userId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateUser_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ObjectNotFoundException, ValidationException, DataStorageException {
        UserForm userForm = mock(UserForm.class);
        int userId = 1;
        when(userForm.getViolations()).thenReturn(mock(Violations.class));
        when(userForm.getViolations().hasErrors()).thenReturn(false);
        when(userDAO.getUserById(anyInt())).thenThrow(new DataStorageException(""));
        userService.updateUser(userForm, userId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateUser_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, ObjectNotFoundException, ValidationException, DataStorageException {
        UserForm userForm = mock(UserForm.class);
        int userId = 1;
        when(userForm.getViolations()).thenReturn(mock(Violations.class));
        when(userForm.getViolations().hasErrors()).thenReturn(false);
        when(userDAO.getUserById(anyInt())).thenReturn(Optional.ofNullable(null));
        userService.updateUser(userForm, userId);
    }

    @Test
    public void testUpdateUser_SHOULD_RETURN_USER() throws Exception {
        UserForm userForm = spy(new UserForm());
        Account account = spy(new Account());
        account.setId(1);
        User user = spy(new User());
        user.setAccount(account);
        int userId = 1;

        when(userForm.getViolations())
                .thenReturn(mock(Violations.class));
        when(userForm.getViolations().hasErrors())
                .thenReturn(false);
        when(userDAO.getUserById(anyInt()))
                .thenReturn(Optional.of(user));
        doNothing().when(userDAO).updateUser(anyInt(), any(User.class));
        doNothing().when(accountDAO).updateAccount(anyInt(), any(Account.class));
        User result = userService.updateUser(userForm, userId);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDeleteUser_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        int accountId = 1;
        when(accountDAO.deleteAccount(anyInt())).thenThrow(new DataStorageException(""));
        userService.deleteUser(accountId);
    }

    @Test
    public void testDeleteUser_SHOULD_RETURN_FALSE() throws DataStorageException, InternalServerErrorException {
        int accountId = 1;
        when(accountDAO.deleteAccount(anyInt())).thenReturn(false);
        boolean result = userService.deleteUser(accountId);

        assertFalse(result);
    }

    @Test
    public void testDeleteUser_SHOULD_RETURN_TRUE() throws DataStorageException, InternalServerErrorException {
        int accountId = 1;
        when(accountDAO.deleteAccount(anyInt())).thenReturn(true);
        boolean result = userService.deleteUser(accountId);

        assertTrue(result);
    }
}