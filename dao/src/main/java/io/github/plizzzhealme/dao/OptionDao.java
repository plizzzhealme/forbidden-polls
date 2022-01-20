package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface OptionDao {

    List<Option> search(int questionId) throws DaoException;
}
