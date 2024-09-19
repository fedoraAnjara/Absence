package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Absence;
import com.fedoraa.presencebackend.entity.Course;
import com.fedoraa.presencebackend.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AbsenceDAOimpl implements AbsenceDAO {
    private Connection connection;
    private StudentDAO studentDAO;  // Ajout de StudentDAO

    @Override
    public List<Absence> findAll() {
        List<Absence> absences = new ArrayList<>();
        String query = "SELECT * FROM absence ORDER BY id_absence ASC";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setIdAbsence(resultSet.getString("id_absence"));
                absence.setDate(resultSet.getTimestamp("date").toLocalDateTime());

                // Utilisation de StudentDAO pour obtenir les détails de l'étudiant
                Student student = studentDAO.findById(resultSet.getString("id_student"));
                absence.setStudent(student);

                // Création d'un objet Course
                Course course = new Course();
                course.setIdCourse(resultSet.getString("id_course"));
                absence.setCourse(course);

                absences.add(absence);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }

    @Override
    public Absence findById(String id) {
        Absence absence = null;
        String query = "SELECT * FROM absence WHERE id_absence = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    absence = new Absence();
                    absence.setIdAbsence(resultSet.getString("id_absence"));
                    absence.setDate(resultSet.getTimestamp("date").toLocalDateTime());

                    Student student = studentDAO.findById(resultSet.getString("id_student"));
                    absence.setStudent(student);

                    // Création d'un objet Course
                    Course course = new Course();
                    course.setIdCourse(resultSet.getString("id_course"));
                    absence.setCourse(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absence;
    }

    @Override
    public void save(Absence absence) {
        String query = "INSERT INTO absence (id_absence, date, id_course, id_student) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, absence.getIdAbsence());
            statement.setTimestamp(2, Timestamp.valueOf(absence.getDate()));
            statement.setString(3, absence.getCourse() != null ? absence.getCourse().getIdCourse() : null);
            statement.setString(4, absence.getStudent() != null ? absence.getStudent().getIdStudent() : null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Absence absence) {
        String query = "UPDATE absence SET date = ?, id_course = ?, id_student = ? WHERE id_absence = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setTimestamp(1, Timestamp.valueOf(absence.getDate()));  // Utilisez Timestamp directement
            statement.setString(2, absence.getCourse() != null ? absence.getCourse().getIdCourse() : null);
            statement.setString(3, absence.getStudent() != null ? absence.getStudent().getIdStudent() : null);
            statement.setString(4, absence.getIdAbsence());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String query = "DELETE FROM absence WHERE id_absence = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int countUnjustifiedAbsences(String studentId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM absence WHERE id_student = ? AND id_absence NOT IN (SELECT id_absence FROM justification)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
