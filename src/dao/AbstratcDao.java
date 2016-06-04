package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junior on 19/05/2016.
 */
public abstract class AbstratcDao<T> {

    public List<T> buscarTodos() {

        List<T> tList = new ArrayList<>();

        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getBUSCAR_TODOS())) {
                    while (resultSet.next()) {
                        T t = criar(resultSet);
                        tList.add(t);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tList;
    }


    protected abstract String getBUSCAR_TODOS();

    protected abstract T criar(ResultSet resultSet) throws SQLException;

}
