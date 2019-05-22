package ssu.org.epam.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<T> {
    void transformFromTableRecord(ResultSet dbResponse, T entity) throws SQLException;
    void findAllFIO(ResultSet dbResponse, List<T> entity) throws SQLException;
    void findAllFullInfo(ResultSet dbResponse, List<T> entity) throws SQLException;
    void findByName(ResultSet dbResponse, List<T> entity) throws SQLException;
}
