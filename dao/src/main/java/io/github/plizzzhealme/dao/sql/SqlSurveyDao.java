package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlSurveyDao implements SurveyDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String SELECT_SURVEY_BY_ID_SQL = "" +
            "SELECT S.name, S.creation_date, S.description, S.instructions, S.image_url, C.name " +
            "FROM forbidden_polls.surveys AS S " +
            "JOIN categories AS C on C.id = S.category_id " +
            "WHERE S.id = ?";

    private static final String SELECT_QUESTIONS_BY_SURVEY_ID = "" +
            "SELECT Q.id, Q.index_number, Q.body, Q.image_url, Q.question_description, OT.type " +
            "FROM questions AS Q " +
            "JOIN option_types AS OT on OT.id = Q.option_type_id " +
            "WHERE survey_id = ? " +
            "ORDER BY Q.index_number";

    private static final String SELECT_OPTIONS_BY_QUESTION_ID = "" +
            "SELECT id, index_number, body FROM forbidden_polls.options WHERE question_id = ? ORDER BY index_number";

    @Override
    public Survey read(int id) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Survey survey = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_SURVEY_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                survey = new Survey();
                survey.setId(id);
                survey.setCreationDate(DaoUtil.toJavaTime(resultSet.getTimestamp("S.creation_date")));
                survey.setName(resultSet.getString("S.name"));
                survey.setCategory(resultSet.getString("C.name"));
                survey.setDescription(resultSet.getString("S.description"));
                survey.setImageUrl(resultSet.getString("S.image_url"));
                survey.setInstructions(resultSet.getString("S.instructions"));

                survey.setQuestions(readQuestions(id));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }


        return survey;
    }

    private List<Question> readQuestions(int surveyID) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_QUESTIONS_BY_SURVEY_ID);
            preparedStatement.setInt(1, surveyID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();

                int questionID = resultSet.getInt("Q.id");
                question.setId(questionID);
                question.setBody(resultSet.getString("Q.body"));
                question.setIndex(resultSet.getInt("Q.index_number"));
                question.setOptionType(resultSet.getString("OT.type"));
                question.setOptions(readOptions(questionID));

                questions.add(question);

            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading question data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return questions;
    }

    private List<Option> readOptions(int questionID) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Option> options = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_OPTIONS_BY_QUESTION_ID);
            preparedStatement.setInt(1, questionID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Option option = new Option();

                option.setId(resultSet.getInt("id"));
                option.setIndex(resultSet.getInt("index_number"));
                option.setBody(resultSet.getString("body"));

                options.add(option);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading option data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return options;
    }
}
