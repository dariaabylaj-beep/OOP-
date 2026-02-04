package dao;

import database.DatabaseConnection;
import model.Member;
import model.PremiumMember;
import model.StudentMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class MemberDAO {

    private static final String SELECT_COLUMNS =
            "member_id, membership_type, name, age, university, has_personal_trainer";

    // -------------------- INSERT --------------------

    public boolean insertStudentMember(StudentMember m) throws SQLException {
        String sql = "INSERT INTO gym_members (member_id, membership_type, name, age, university, has_personal_trainer) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, m.getMemberId());
            statement.setString(2, m.getMembershipType());
            statement.setString(3, m.getName());
            statement.setInt(4, m.getAge());
            statement.setString(5, m.getUniversity());
            statement.setNull(6, Types.BOOLEAN);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean insertPremiumMember(PremiumMember m) throws SQLException {
        String sql = "INSERT INTO gym_members (member_id, membership_type, name, age, university, has_personal_trainer) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, m.getMemberId());
            statement.setString(2, m.getMembershipType());
            statement.setString(3, m.getName());
            statement.setInt(4, m.getAge());
            statement.setNull(5, Types.VARCHAR);
            statement.setBoolean(6, m.isHasPersonalTrainer());

            return statement.executeUpdate() > 0;
        }
    }

    // -------------------- SELECT --------------------

    public ArrayList<Member> getAllMembers() throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT " + SELECT_COLUMNS + " FROM gym_members ORDER BY member_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                members.add(mapMember(resultSet));
            }
        }

        return members;
    }

    public Member getMemberById(int memberId) throws SQLException {
        String sql = "SELECT " + SELECT_COLUMNS + " FROM gym_members WHERE member_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, memberId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapMember(resultSet);
                }
            }
        }

        return null;
    }

    public ArrayList<Member> getMembersByType(String membershipType) throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT " + SELECT_COLUMNS + " FROM gym_members WHERE membership_type = ? ORDER BY member_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, membershipType);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    members.add(mapMember(resultSet));
                }
            }
        }

        return members;
    }

    // -------------------- UPDATE --------------------

    public boolean updateStudentMember(int memberId, String name, int age, String university) throws SQLException {
        String sql = "UPDATE gym_members SET name = ?, age = ?, university = ?, has_personal_trainer = NULL " +
                "WHERE member_id = ? AND membership_type = 'Student'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, university);
            statement.setInt(4, memberId);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updatePremiumMember(int memberId, String name, int age, boolean hasTrainer) throws SQLException {
        String sql = "UPDATE gym_members SET name = ?, age = ?, has_personal_trainer = ?, university = NULL " +
                "WHERE member_id = ? AND membership_type = 'Premium'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setBoolean(3, hasTrainer);
            statement.setInt(4, memberId);

            return statement.executeUpdate() > 0;
        }
    }

    // -------------------- DELETE --------------------

    public boolean deleteMember(int memberId) throws SQLException {
        String sql = "DELETE FROM gym_members WHERE member_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, memberId);
            return statement.executeUpdate() > 0;
        }
    }

    // -------------------- SEARCH --------------------

    public ArrayList<Member> searchByName(String namePart) throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT " + SELECT_COLUMNS + " FROM gym_members WHERE name ILIKE ? ORDER BY member_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + namePart + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    members.add(mapMember(resultSet));
                }
            }
        }

        return members;
    }

    public ArrayList<Member> searchByAgeRange(int minAge, int maxAge) throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT " + SELECT_COLUMNS + " FROM gym_members WHERE age BETWEEN ? AND ? ORDER BY age DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    members.add(mapMember(resultSet));
                }
            }
        }

        return members;
    }

    public ArrayList<Member> searchByMinAge(int minAge) throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT " + SELECT_COLUMNS + " FROM gym_members WHERE age >= ? ORDER BY age DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, minAge);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    members.add(mapMember(resultSet));
                }
            }
        }

        return members;
    }

    // -------------------- Helper --------------------

    private Member mapMember(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("member_id");
        String type = resultSet.getString("membership_type");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");

        if (type != null && type.equalsIgnoreCase("Student")) {
            String uni = resultSet.getString("university");
            return new StudentMember(id, name, age, uni);
        }

        boolean trainer = resultSet.getBoolean("has_personal_trainer");
        return new PremiumMember(id, name, age, trainer);
    }
}
