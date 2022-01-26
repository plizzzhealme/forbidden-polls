package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface SurveyDao {

    Survey find(int id) throws DaoException;

    List<Survey> search(SearchCriteria searchCriteria) throws DaoException;

    void addSurveyResult(Survey survey, int id) throws DaoException;

    boolean isCompleted(int surveyId, int userId) throws DaoException;

    List<Survey> searchCompleted(int userId) throws DaoException;
}
