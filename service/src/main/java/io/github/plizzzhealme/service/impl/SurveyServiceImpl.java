package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.*;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.SurveyService;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.Iterator;
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
    public List<Survey> searchAvailableSurveys(SearchCriteria criteria, int userId) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.INSTANCE;
        SurveyDao surveyDao = daoFactory.getSurveyDao();

        try {
            List<Survey> surveys = surveyDao.search(criteria);

            Iterator<Survey> surveyIterator = surveys.iterator();

            while (surveyIterator.hasNext()) {
                Survey survey = surveyIterator.next();

                if (surveyDao.isSurveyPassedByUser(survey.getId(), userId)) {
                    surveyIterator.remove();
                }
            }

            return surveys;
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public void addNewSurvey(Survey survey) throws ServiceException {


        // validator


        DaoFactory daoFactory = DaoFactory.INSTANCE;
        CategoryDao categoryDao = daoFactory.getCategoryDao();
        SurveyDao surveyDao = daoFactory.getSurveyDao();
        String category = survey.getCategory();

        try {
            if (!categoryDao.isPresent(category)) {
                categoryDao.create(category);
            }

            surveyDao.create(survey);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add new survey", e);
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
            return surveyDao.searchSurveysPassedByUser(userId);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
