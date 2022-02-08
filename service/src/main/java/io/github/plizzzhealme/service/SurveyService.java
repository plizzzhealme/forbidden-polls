package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.service.exception.ServiceException;
import io.github.plizzzhealme.service.exception.ValidatorException;

import java.util.List;

public interface SurveyService {

    Survey takeSurvey(int id) throws ServiceException;

    @SuppressWarnings("unused")
    List<Survey> search(SearchCriteria searchCriteria) throws ServiceException;

    void completeSurvey(Survey survey, int userId) throws ServiceException;

    List<Survey> searchCompletedSurveys(int userId) throws ServiceException;

    List<Survey> searchAvailableSurveys(SearchCriteria criteria, int userId) throws ServiceException;

    void addNewSurvey(Survey survey) throws ServiceException, ValidatorException;

    Survey searchSurveyStatistics(int surveyId) throws ServiceException;
}
