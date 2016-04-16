package com.andieguo.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.andieguo.spring.bean.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return getJdbcTemplate().query(
				"select * from tb_user", new Object[] {},
				new BeanPropertyRowMapper(User.class));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().queryForObject("select * from tb_user where uid=?", new BeanPropertyRowMapper(User.class), new Object[]{id});
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().update(
				"insert into tb_user(name,age,gender) values (?,?,?)",
				new Object[] {user.getName(),user.getAge(),user.getGender()});
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().update("delete from tb_user where uid = ?", new Object[]{id});
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().update("update tb_user set name=?,age=?,gender=? where uid=?", new Object[]{user.getName(),user.getAge(),user.getGender(),user.getUid()});
	}

}
