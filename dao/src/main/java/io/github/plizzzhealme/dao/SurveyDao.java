package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;

import java.util.List;

public interface SurveyDao {

    void create(Survey survey) throws DaoException;

    Survey find(int id) throws DaoException, EntityNotFoundException;

    List<Survey> search(SearchCriteria searchCriteria) throws DaoException;

    void addSurveyResult(Survey survey, int userId) throws DaoException;

    boolean isSurveyPassedByUser(int surveyId, int userId) throws DaoException;

    List<Survey> searchSurveysPassedByUser(int userId) throws DaoException, EntityNotFoundException;
}
