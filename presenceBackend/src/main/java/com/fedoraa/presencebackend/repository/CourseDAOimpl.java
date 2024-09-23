package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Course;
import com.fedoraa.presencebackend.entity.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CourseDAOimpl implements CourseDAO {
    private Connection connection;

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM course ORDER BY id_course ASC";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Course course = new Course();
                course.setIdCourse(resultSet.getString("id_course"));
                course.setTitle(resultSet.getString("title"));

                // Assuming the teacher is stored as an ID, adjust if Teacher entity is more complex
                Teacher teacher = new Teacher();
                teacher.setIdTeacher(resultSet.getString("id_teacher"));
                course.setTeacher(teacher);

                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public Course findById(String id_course) {
        Course course = null;
        String query = "SELECT * FROM course WHERE id_course = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id_course);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    course.setIdCourse(resultSet.getString("id_course"));
                    course.setTitle(resultSet.getString("title"));

                    // Assuming the teacher is stored as an ID
                    Teacher teacher = new Teacher();
                    teacher.setIdTeacher(resultSet.getString("id_teacher"));
                    course.setTeacher(teacher);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public void save(Course course) {
        String query = "INSERT INTO course (id_course, title, id_teacher) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getIdCourse());
            statement.setString(2, course.getTitle());
            statement.setString(3, course.getTeacher().getIdTeacher());
            statement.executeUpdate();
            System.out.println("Success: Course added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course course) {
        String query = "UPDATE course SET title = ?, id_teacher = ? WHERE id_course = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getTeacher().getIdTeacher());
            statement.setString(3, course.getIdCourse());
            statement.executeUpdate();
            System.out.println("Success: Course updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id_course) {
        String query = "DELETE FROM course WHERE id_course = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id_course);
            statement.executeUpdate();
            System.out.println("Success: Course deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
