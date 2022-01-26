package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public interface SurveyService {

    Survey takeSurvey(int id) throws ServiceException;

    List<Survey> search(SearchCriteria searchCriteria) throws ServiceException;

    void completeSurvey(Survey survey, int userId) throws ServiceException;

    List<Survey> searchCompletedSurveys(int userId) throws ServiceException;
}
