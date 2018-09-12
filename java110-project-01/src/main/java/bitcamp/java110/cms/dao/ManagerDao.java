package bitcamp.java110.cms.dao;

import java.util.List;

import bitcamp.java110.cms.domain.Manager;

public interface ManagerDao {
    public int insert(Manager manager) throws MandatoryValueDaoException, DuplicationDaoException;
    public List<Manager> findAll();
    public Manager findByEmail(String email);
    public int delete(String email);
}
