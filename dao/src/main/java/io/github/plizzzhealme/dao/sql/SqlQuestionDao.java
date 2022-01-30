package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.dao.QuestionDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlQuestionDao implements QuestionDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String SELECT_QUESTIONS_BY_SURVEY_ID = "" + "SELECT questions.id, questions.index_number, questions.body, questions.image_url, " + "questions.description, option_types.type " + "FROM forbidden_polls.questions " + "JOIN forbidden_polls.option_types ON option_types.id = questions.option_type_id " + "WHERE questions.survey_id = ? " + "ORDER BY questions.index_number";

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

                question.setId(resultSet.getInt(SqlParameter.QUESTIONS_ID));
                question.setIndex(resultSet.getInt(SqlParameter.QUESTIONS_INDEX_NUMBER));
                question.setBody(resultSet.getString(SqlParameter.QUESTIONS_BODY));
                question.setImageUrl(resultSet.getString(SqlParameter.QUESTIONS_IMAGE_URL));
                question.setDescription(resultSet.getString(SqlParameter.QUESTIONS_DESCRIPTION));
                question.setOptionType(resultSet.getString(SqlParameter.OPTION_TYPES_TYPE));

                questions.add(question);
            }

            return questions;
        } catch (SQLException e) {
            throw new DaoException("Error while reading question data from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
