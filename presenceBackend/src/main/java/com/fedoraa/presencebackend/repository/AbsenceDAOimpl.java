package com.fedoraa.presencebackend.repository;

import com.fedoraa.presencebackend.entity.Absence;
import com.fedoraa.presencebackend.entity.CorProcess;
import com.fedoraa.presencebackend.entity.Course;
import com.fedoraa.presencebackend.entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AbsenceDAOimpl implements AbsenceDAO {
    private final Connection connection;
    private final StudentDAO studentDAO;
    private final CorProcessDAO corProcessDAO;

    public AbsenceDAOimpl(Connection connection, StudentDAO studentDAO, CorProcessDAO corProcessDAO) {
        this.connection = connection;
        this.studentDAO = studentDAO;
        this.corProcessDAO = corProcessDAO;
    }

    @Override
    public void save(Absence absence) {
        if (absence.getIdAbsence() == null || absence.getIdAbsence().isEmpty()) {
            throw new IllegalArgumentException("L'ID de l'absence doit être fourni.");
        }

        if (absence.getStudent() == null || absence.getStudent().getIdStudent() == null) {
            throw new IllegalArgumentException("L'étudiant (id_student) comporte une erreur");
        }

        Student student = studentDAO.findById(absence.getStudent().getIdStudent());
        if (student == null) {
            throw new IllegalArgumentException("There's no Student with ID " + absence.getStudent().getIdStudent());
        }

        String query = "INSERT INTO absence (id_absence, date, id_course, id_student, is_justified) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, absence.getIdAbsence());
            statement.setTimestamp(2, Timestamp.valueOf(absence.getDate()));
            statement.setString(3, absence.getCourse() != null ? absence.getCourse().getIdCourse() : null);
            statement.setString(4, student.getIdStudent());
            statement.setBoolean(5, absence.isJustified());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Move the process creation to the service layer to break the circular dependency
    }

    @Override
    public Absence findById(String idAbsence) {
        String query = "SELECT * FROM absence WHERE id_absence = ?";
        Absence absence = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idAbsence);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                absence = mapRowToAbsence(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absence;
    }

    @Override
    public List<Absence> findAll() {
        String query = "SELECT * FROM absence";
        List<Absence> absences = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                absences.add(mapRowToAbsence(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

    @Override
    public void update(Absence absence) {
        if (absence.getIdAbsence() == null || absence.getIdAbsence().isEmpty()) {
            throw new IllegalArgumentException("L'ID de l'absence doit être fourni pour la mise à jour.");
        }

        Student student = studentDAO.findById(absence.getStudent().getIdStudent());
        if (student == null) {
            throw new IllegalArgumentException("L'étudiant avec l'ID " + absence.getStudent().getIdStudent() + " n'existe pas.");
        }

        String query = "UPDATE absence SET date = ?, id_course = ?, id_student = ?, is_justified = ? WHERE id_absence = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, Timestamp.valueOf(absence.getDate()));
            statement.setString(2, absence.getCourse() != null ? absence.getCourse().getIdCourse() : null);
            statement.setString(3, student.getIdStudent());
            statement.setBoolean(4, absence.isJustified());
            statement.setString(5, absence.getIdAbsence());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String idAbsence) {
        String query = "DELETE FROM absence WHERE id_absence = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idAbsence);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int countUnjustifiedAbsences(String idStudent) {
        String query = "SELECT COUNT(*) AS unjustified_count FROM absence WHERE id_student = ? AND is_justified = false";
        int unjustifiedCount = 0;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idStudent);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                unjustifiedCount = resultSet.getInt("unjustified_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unjustifiedCount;
    }

    private Absence mapRowToAbsence(ResultSet resultSet) throws SQLException {
        Absence absence = new Absence();
        absence.setIdAbsence(resultSet.getString("id_absence"));
        absence.setDate(resultSet.getTimestamp("date").toLocalDateTime());

        Course course = new Course();
        course.setIdCourse(resultSet.getString("id_course"));
        absence.setCourse(course);

        Student student = studentDAO.findById(resultSet.getString("id_student"));
        absence.setStudent(student);

        absence.setJustified(resultSet.getBoolean("is_justified"));

        return absence;
    }
}
