package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.QuestionDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlQuestionDao implements QuestionDao {

    public static final String QUESTIONS_ID = "questions.id";
    public static final String QUESTIONS_BODY = "questions.body";
    public static final String QUESTIONS_INDEX_NUMBER = "questions.index_number";
    public static final String OPTION_TYPES_TYPE = "option_types.type";
    public static final String QUESTIONS_IMAGE_URL = "questions.image_url";
    public static final String QUESTIONS_DESCRIPTION = "questions.description";
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_QUESTIONS_BY_SURVEY_ID = "" +
            "SELECT questions.id, questions.index_number, questions.body, questions.image_url, " +
            "questions.description, option_types.type " +
            "FROM forbidden_polls.questions " +
            "JOIN forbidden_polls.option_types ON option_types.id = questions.option_type_id " +
            "WHERE questions.survey_id = ? " +
            "ORDER BY questions.index_number";

    @Override
    public List<Question> search(int surveyId) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_QUESTIONS_BY_SURVEY_ID);
            preparedStatement.setInt(1, surveyId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();

                int questionId = resultSet.getInt(QUESTIONS_ID);
                question.setId(questionId);
                question.setIndex(resultSet.getInt(QUESTIONS_INDEX_NUMBER));
                question.setBody(resultSet.getString(QUESTIONS_BODY));
                question.setImageUrl(resultSet.getString(QUESTIONS_IMAGE_URL));
                question.setDescription(resultSet.getString(QUESTIONS_DESCRIPTION));
                question.setOptionType(resultSet.getString(OPTION_TYPES_TYPE));

                question.setOptions(DaoFactory.INSTANCE.getOptionDao().search(questionId));

                questions.add(question);

            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading question data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return questions;
    }
}
