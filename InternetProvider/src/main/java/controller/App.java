package controller;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws Exception
    {
    	DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.createUserDao();
//        dao.create(new User("user2","password1",User.ROLE.USER));
//        dao.create(new User("user3","password2",User.ROLE.ADMIN));
//        dao.create(new User("user4","password3",User.ROLE.USER));
//        dao.create(new User("user5","password4",User.ROLE.ADMIN));
        
        
        User user=dao.findById(3);
        System.out.println(user);
        user.setLogin("xxxxx");
        dao.update(user);
        System.out.println(dao.findById(3));
    }
}

