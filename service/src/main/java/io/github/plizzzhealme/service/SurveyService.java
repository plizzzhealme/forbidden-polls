package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Criteria;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public interface SurveyService {

    Survey takeSurvey(int id) throws ServiceException;

    List<Survey> search(Criteria criteria) throws ServiceException;
}
