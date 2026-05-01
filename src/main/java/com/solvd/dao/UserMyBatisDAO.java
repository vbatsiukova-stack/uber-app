package com.solvd.dao;

import com.solvd.model.User;
import com.solvd.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class UserMyBatisDAO implements IUserDAO {

    @Override
    public User create(User user) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            session.insert("com.solvd.dao.IUserDAO.create", user);
            return user;
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            User user = session.selectOne("com.solvd.dao.IUserDAO.getById", id);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> getAll() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            return session.selectList("com.solvd.dao.IUserDAO.getAll");
        }
    }

    @Override
    public User update(User user) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            session.update("com.solvd.dao.IUserDAO.update", user);
            return user;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            int result = session.delete("com.solvd.dao.IUserDAO.deleteById", id);
            return result > 0;
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            User user = session.selectOne("com.solvd.dao.IUserDAO.getByEmail", email);
            return Optional.ofNullable(user);
        }
    }
}
