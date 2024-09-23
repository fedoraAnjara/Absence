package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Genre;
import com.fedoraa.presencebackend.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherDAOimpl implements TeacherDAO {
    private Connection connection;

    public TeacherDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teacher ORDER BY id_teacher ASC";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setIdTeacher(resultSet.getString("id_teacher"));
                teacher.setFirstName(resultSet.getString("first_name"));
                teacher.setLastName(resultSet.getString("last_name"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setGenre(Genre.valueOf(resultSet.getString("genre")));
                teacher.setBirthDate(resultSet.getString("birth_date"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    @Override
    public Teacher findById(String idTeacher) {
        Teacher teacher = null;
        String query = "SELECT * FROM teacher WHERE id_teacher = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idTeacher);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setIdTeacher(resultSet.getString("id_teacher"));
                    teacher.setFirstName(resultSet.getString("first_name"));
                    teacher.setLastName(resultSet.getString("last_name"));
                    teacher.setEmail(resultSet.getString("email"));
                    teacher.setGenre(Genre.valueOf(resultSet.getString("genre")));
                    teacher.setBirthDate(resultSet.getString("birth_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    @Override
    public void save(Teacher teacher) {
        String query = "INSERT INTO teacher (id_teacher, first_name, last_name, email, genre, birth_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teacher.getIdTeacher());
            statement.setString(2, teacher.getFirstName());
            statement.setString(3, teacher.getLastName());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getGenre().toString());
            statement.setString(6, teacher.getBirthDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Teacher teacher) {
        String query = "UPDATE teacher SET first_name = ?, last_name = ?, email = ?, genre = ?, birth_date = ? WHERE id_teacher = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getGenre().toString());
            statement.setString(5, teacher.getBirthDate());
            statement.setString(6, teacher.getIdTeacher());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String idTeacher) {
        String query = "DELETE FROM teacher WHERE id_teacher = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idTeacher);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
