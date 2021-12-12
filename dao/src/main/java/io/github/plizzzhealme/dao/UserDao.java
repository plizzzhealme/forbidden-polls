package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.SearchCriteria;
import io.github.plizzzhealme.bean.User;

public interface UserDao extends Dao<User> {

    User search(SearchCriteria criteria);
}
