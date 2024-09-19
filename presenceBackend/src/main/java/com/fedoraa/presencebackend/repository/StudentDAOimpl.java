package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Genre;
import com.fedoraa.presencebackend.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@AllArgsConstructor
public class StudentDAOimpl implements StudentDAO {
    private Connection connection;

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student order by id_student ASC";

        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent(resultSet.getString("id_student"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setGenre(Genre.valueOf(resultSet.getString("genre")));
                student.setBirthDate(resultSet.getString("birth_date"));
                student.setObservation(resultSet.getString("observation"));
                student.setGroupId(resultSet.getString("group_id"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public Student findById(String id_student) {
        Student student = null;
        String query = "SELECT * FROM student WHERE id_student = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id_student);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student();
                    student.setIdStudent(resultSet.getString("id_student"));
                    student.setFirstName(resultSet.getString("first_name"));
                    student.setLastName(resultSet.getString("last_name"));
                    student.setEmail(resultSet.getString("email"));
                    student.setGenre(Genre.valueOf(resultSet.getString("genre")));
                    student.setBirthDate(resultSet.getString("birth_date"));
                    student.setObservation(resultSet.getString("observation"));
                    student.setGroupId(resultSet.getString("group_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void save(Student student) {
        String query = "INSERT INTO student (id_student, first_name, last_name, email, genre, birth_date, observation, group_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getIdStudent());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, String.valueOf(student.getGenre()));
            statement.setString(6, student.getBirthDate());
            statement.setString(7, student.getObservation());
            statement.setString(8, student.getGroupId());
            statement.executeUpdate();
            System.out.println("Success: Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        String query = "UPDATE student SET first_name = ?, last_name = ?, email = ?, genre = ?, birth_date = ?, observation = ?, group_id = ? WHERE id_student = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, String.valueOf(student.getGenre()));
            statement.setString(5, student.getBirthDate());
            statement.setString(6, student.getObservation());
            statement.setString(7, student.getGroupId());
            statement.setString(8, student.getIdStudent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id_student) {
        String query = "DELETE FROM student WHERE id_student = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id_student);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
