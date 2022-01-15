package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.dao.exception.DaoException;

public interface SurveyDao {

    Survey read(int id) throws DaoException;

    int search(String surveyName) throws DaoException;
}
