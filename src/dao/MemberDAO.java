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

    public boolean insertStudentMember(StudentMember m) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return false;
            }

            String sql = "INSERT INTO gym_members (member_id, membership_type, name, age, university, has_personal_trainer) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, m.getMemberId());
            statement.setString(2, m.getMembershipType());
            statement.setString(3, m.getName());
            statement.setInt(4, m.getAge());
            statement.setString(5, m.getUniversity());
            statement.setNull(6, Types.BOOLEAN);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Insert student error: " + e.getMessage());
            return false;

        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean insertPremiumMember(PremiumMember m) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return false;
            }

            String sql = "INSERT INTO gym_members (member_id, membership_type, name, age, university, has_personal_trainer) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, m.getMemberId());
            statement.setString(2, m.getMembershipType());
            statement.setString(3, m.getName());
            statement.setInt(4, m.getAge());
            statement.setNull(5, Types.VARCHAR);
            statement.setBoolean(6, m.isHasPersonalTrainer());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Insert premium error: " + e.getMessage());
            return false;

        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return members;
            }

            String sql = "SELECT member_id, membership_type, name, age, university, has_personal_trainer " +
                    "FROM gym_members ORDER BY member_id";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String type = resultSet.getString("membership_type");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                if (type != null && type.equalsIgnoreCase("Student")) {
                    String uni = resultSet.getString("university");
                    members.add(new StudentMember(id, name, age, uni));
                } else if (type != null && type.equalsIgnoreCase("Premium")) {
                    boolean trainer = resultSet.getBoolean("has_personal_trainer");
                    members.add(new PremiumMember(id, name, age, trainer));
                }
            }

        } catch (SQLException e) {
            System.out.println("Get all error: " + e.getMessage());

        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close error");
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    public Member getMemberById(int memberId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return null;
            }

            String sql = "SELECT member_id, membership_type, name, age, university, has_personal_trainer " +
                    "FROM gym_members WHERE member_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String type = resultSet.getString("membership_type");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                if (type != null && type.equalsIgnoreCase("Student")) {
                    String uni = resultSet.getString("university");
                    return new StudentMember(id, name, age, uni);
                } else if (type != null && type.equalsIgnoreCase("Premium")) {
                    boolean trainer = resultSet.getBoolean("has_personal_trainer");
                    return new PremiumMember(id, name, age, trainer);
                }
            }

            return null;

        } catch (SQLException e) {
            System.out.println("Get by id error: " + e.getMessage());
            return null;

        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close error");
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public ArrayList<Member> getMembersByType(String membershipType) {
        ArrayList<Member> members = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return members;
            }

            String sql = "SELECT member_id, membership_type, name, age, university, has_personal_trainer " +
                    "FROM gym_members WHERE membership_type = ? ORDER BY member_id";
            statement = connection.prepareStatement(sql);
            statement.setString(1, membershipType);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String type = resultSet.getString("membership_type");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                if (type != null && type.equalsIgnoreCase("Student")) {
                    String uni = resultSet.getString("university");
                    members.add(new StudentMember(id, name, age, uni));
                } else if (type != null && type.equalsIgnoreCase("Premium")) {
                    boolean trainer = resultSet.getBoolean("has_personal_trainer");
                    members.add(new PremiumMember(id, name, age, trainer));
                }
            }

        } catch (SQLException e) {
            System.out.println("Filtered select error: " + e.getMessage());

        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close error");
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    public boolean updateStudentMember(int memberId, String name, int age, String university) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return false;
            }

            String sql = "UPDATE gym_members SET name = ?, age = ?, university = ?, has_personal_trainer = NULL " +
                    "WHERE member_id = ? AND membership_type = 'Student'";
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, university);
            statement.setInt(4, memberId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update student error: " + e.getMessage());
            return false;

        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean updatePremiumMember(int memberId, String name, int age, boolean hasTrainer) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return false;
            }

            String sql = "UPDATE gym_members SET name = ?, age = ?, has_personal_trainer = ?, university = NULL " +
                    "WHERE member_id = ? AND membership_type = 'Premium'";
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setBoolean(3, hasTrainer);
            statement.setInt(4, memberId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update premium error: " + e.getMessage());
            return false;

        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean deleteMember(int memberId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return false;
            }

            String sql = "DELETE FROM gym_members WHERE member_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;

        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public ArrayList<Member> searchByName(String namePart) {
        ArrayList<Member> members = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return members;
            }

            String sql = "SELECT member_id, membership_type, name, age, university, has_personal_trainer " +
                    "FROM gym_members WHERE name ILIKE ? ORDER BY member_id";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + namePart + "%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String type = resultSet.getString("membership_type");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                if (type != null && type.equalsIgnoreCase("Student")) {
                    String uni = resultSet.getString("university");
                    members.add(new StudentMember(id, name, age, uni));
                } else if (type != null && type.equalsIgnoreCase("Premium")) {
                    boolean trainer = resultSet.getBoolean("has_personal_trainer");
                    members.add(new PremiumMember(id, name, age, trainer));
                }
            }

        } catch (SQLException e) {
            System.out.println("Search name error: " + e.getMessage());

        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close error");
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    public ArrayList<Member> searchByAgeRange(int minAge, int maxAge) {
        ArrayList<Member> members = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return members;
            }

            String sql = "SELECT member_id, membership_type, name, age, university, has_personal_trainer " +
                    "FROM gym_members WHERE age BETWEEN ? AND ? ORDER BY age DESC";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String type = resultSet.getString("membership_type");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                if (type != null && type.equalsIgnoreCase("Student")) {
                    String uni = resultSet.getString("university");
                    members.add(new StudentMember(id, name, age, uni));
                } else if (type != null && type.equalsIgnoreCase("Premium")) {
                    boolean trainer = resultSet.getBoolean("has_personal_trainer");
                    members.add(new PremiumMember(id, name, age, trainer));
                }
            }

        } catch (SQLException e) {
            System.out.println("Search range error: " + e.getMessage());

        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close error");
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    public ArrayList<Member> searchByMinAge(int minAge) {
        ArrayList<Member> members = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("No connection.");
                return members;
            }

            String sql = "SELECT member_id, membership_type, name, age, university, has_personal_trainer " +
                    "FROM gym_members WHERE age >= ? ORDER BY age DESC";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String type = resultSet.getString("membership_type");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                if (type != null && type.equalsIgnoreCase("Student")) {
                    String uni = resultSet.getString("university");
                    members.add(new StudentMember(id, name, age, uni));
                } else if (type != null && type.equalsIgnoreCase("Premium")) {
                    boolean trainer = resultSet.getBoolean("has_personal_trainer");
                    members.add(new PremiumMember(id, name, age, trainer));
                }
            }

        } catch (SQLException e) {
            System.out.println("Search min error: " + e.getMessage());

        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close error");
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close error");
            }
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }
}
