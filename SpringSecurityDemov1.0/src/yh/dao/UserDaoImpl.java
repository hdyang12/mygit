package yh.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yh.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl  implements UserDao{
	@Resource
	private SessionFactory sessionFactory;
	
	public User findByName(String loginName) {
		User user = (User)sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = ?").setParameter(0, loginName).list().get(0);
		return user;
	}

}
