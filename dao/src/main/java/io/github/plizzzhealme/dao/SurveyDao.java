package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Criteria;
import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface SurveyDao {

    Survey read(int id) throws DaoException;

    List<Survey> search(Criteria criteria) throws DaoException;
}
