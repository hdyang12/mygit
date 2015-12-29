package yh.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yh.model.Privilege;

@Repository("privilegeDao")
@Transactional
public class PrivilegeDaoImpl  implements PrivilegeDao{

	@Resource
	private SessionFactory sessionFactory;
	
	public List<Privilege> findAll() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Privilege")//
				.list();
	}


}
