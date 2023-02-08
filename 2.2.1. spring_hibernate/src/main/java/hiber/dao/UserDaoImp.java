package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   public void update(User user) { sessionFactory.getCurrentSession().update(user); }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> queryUsers = sessionFactory.getCurrentSession().createQuery("from User");
      List<User> users = queryUsers.getResultList();
      TypedQuery<Car> queryCars = sessionFactory.getCurrentSession().createQuery("from Car");
      List<Car> cars = queryCars.getResultList();
      for (User user : users) {
         user.setCar(cars.get(user.getId().intValue() - 1));
      }
      return users;
   }

   @Override
   public User findUserByCar(String model, int series) {
      User user = (User) sessionFactory.getCurrentSession()
              .createQuery("from User as u where u.car.model = :model and u.car.series = :series")
              .setParameter("model", model)
              .setParameter("series", series)
              .getSingleResult();
      return user;
   }
}
