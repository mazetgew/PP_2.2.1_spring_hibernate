package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   void update(User user);

   User findUserByCar(String model, int series);
   List<User> listUsers();
}
