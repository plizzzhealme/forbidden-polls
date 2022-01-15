package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.service.exception.ServiceException;

public interface SurveyService {

    Survey takeSurvey(int id) throws ServiceException;
}
