package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface QuestionDao {

    List<Question> search(int surveyId) throws DaoException;
}
