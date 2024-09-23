package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.CorProcess;
import com.fedoraa.presencebackend.entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CorProcessDAOimpl implements CorProcessDAO {
    private Connection connection;
    private StudentDAO studentDAO;

    public CorProcessDAOimpl(Connection connection, StudentDAO studentDAO) {
        this.connection = connection;
        this.studentDAO = studentDAO;
    }

    @Override
    public void save(CorProcess corProcess) {
        String query = "INSERT INTO cor_process (id_process, reason, id_student) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, corProcess.getIdProcess());
            statement.setString(2, corProcess.getReason());
            statement.setString(3, corProcess.getStudent().getIdStudent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CorProcess findById(String idProcess) {
        String query = "SELECT * FROM cor_process WHERE id_process = ?";
        CorProcess corProcess = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idProcess);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                corProcess = mapRowToCorProcess(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return corProcess;
    }

    @Override
    public List<CorProcess> findAll() {
        String query = "SELECT * FROM cor_process";
        List<CorProcess> corProcesses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                corProcesses.add(mapRowToCorProcess(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return corProcesses;
    }

    @Override
    public void deleteById(String idProcess) {
        String query = "DELETE FROM cor_process WHERE id_process = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idProcess);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CorProcess mapRowToCorProcess(ResultSet resultSet) throws SQLException {
        CorProcess corProcess = new CorProcess();
        corProcess.setIdProcess(resultSet.getString("id_process"));
        corProcess.setReason(resultSet.getString("reason"));

        Student student = studentDAO.findById(resultSet.getString("id_student"));
        corProcess.setStudent(student);

        return corProcess;
    }
}
