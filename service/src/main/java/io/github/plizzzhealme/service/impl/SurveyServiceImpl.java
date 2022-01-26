package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.OptionDao;
import io.github.plizzzhealme.dao.QuestionDao;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.SurveyService;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public class SurveyServiceImpl implements SurveyService {

    @Override
    public Survey takeSurvey(int id) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.INSTANCE;
            SurveyDao surveyDao = daoFactory.getSurveyDao();
            QuestionDao questionDao = daoFactory.getQuestionDao();
            OptionDao optionDao = daoFactory.getOptionDao();

            Survey survey = surveyDao.find(id);

            List<Question> questions = questionDao.search(survey.getId());
            survey.setQuestions(questions);

            for (Question question : questions) {
                question.setOptions(optionDao.search(question.getId()));
            }

            return survey;
        } catch (DaoException e) {
            throw new ServiceException("Error while reading survey from database", e);
        }
    }

    @Override
    public List<Survey> search(SearchCriteria searchCriteria) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getSurveyDao().search(searchCriteria);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public void completeSurvey(Survey survey, int userId) throws ServiceException {
        try {
            DaoFactory.INSTANCE.getSurveyDao().addSurveyResult(survey, userId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to complete survey", e);
        }
    }

    @Override
    public List<Survey> searchCompletedSurveys(int userId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.INSTANCE;
        SurveyDao surveyDao = daoFactory.getSurveyDao();

        try {
            return surveyDao.searchCompleted(userId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("", e);
        }
    }
}
