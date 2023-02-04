package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User_1", "Lastname_1", "user_1@mail.ru");
      userService.add(user1);
      user1.setCar(new Car("mod_1", 1));
      userService.update(user1);

      User user2 = new User("User_2", "Lastname_2", "user_2@mail.ru");
      userService.add(user2);
      user2.setCar(new Car("mod_2", 2));
      userService.update(user2);

      User user3 = new User("User_3", "Lastname_3", "user_3@mail.ru");
      userService.add(user3);
      user3.setCar(new Car("mod_3", 3));
      userService.update(user3);

      User user4 = new User("User_4", "Lastname_4", "user_4@mail.ru");
      userService.add(user4);
      user4.setCar(new Car("mod_4", 4));
      userService.update(user4);

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().getModel());
         System.out.println();
         System.out.println();
      }

      User foundedUser1 = userService.findUserByCar("mod_4", 4);
      User foundedUser2 = userService.findUserByCar("mod_2", 2);
      System.out.println(foundedUser1.getFirstName());
      System.out.println(foundedUser2.getFirstName());

      context.close();
   }
}