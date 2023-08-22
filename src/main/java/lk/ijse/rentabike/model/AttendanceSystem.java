package lk.ijse.rentabike.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class AttendanceSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static int[] main(String[] args) {


        int presentEmployees = 0;
        int totalEmployees = 0;

        try {
            // 1. Connect to the MySQL database using JDBC driver and establish a connection.
            Connection conn = DriverManager.getConnection(URL, props);

            // 2. Get the total number of employees from the Employee table
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Employee");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalEmployees = rs.getInt(1);
            }

            // 3. Get the current date
            LocalDate today = LocalDate.now();

            // 4. Retrieve the attendance data for today's date from the Attendance table.
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM Attendance WHERE date = ?");
            stmt.setDate(1, java.sql.Date.valueOf(today));
            rs = stmt.executeQuery();
            if (rs.next()) {
                presentEmployees = rs.getInt(1);
            }

            // 5. Display the value of "presentEmployees" in the "present" label.
            System.out.println("Present Employees: " + presentEmployees);

            // 6. Calculate the value of "absentEmployees"
            int absentEmployees = totalEmployees - presentEmployees;

            // 7. Display the value of "totalEmployees" in the "number of employees" label.
            System.out.println("Total Employees: " + totalEmployees);

            // 8. Display the value of "absentEmployees" in the "absent" label.
            System.out.println("Absent Employees: " + absentEmployees);



        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 10. Return presentEmployees and totalEmployees
        return new int[] { presentEmployees, totalEmployees };

    }
}
