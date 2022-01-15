package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.SurveyService;
import io.github.plizzzhealme.service.exception.ServiceException;

public class SurveyServiceImpl implements SurveyService {

    @Override
    public Survey takeSurvey(int id) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getSurveyDao().read(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while reading survey from database", e);
        }
    }
}
