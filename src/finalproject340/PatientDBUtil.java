/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject340;

import java.sql.*;
import javax.swing.JOptionPane;

public class PatientDBUtil {

    public static boolean savePatient(
            String patientID, String ptLastName, String ptPreviousLastName, String ptFirstName,
            String homeAddress1, String homeCity, String homeState, String homeZip,
            String country, String citizenship, String ptMobilePhone, String emergencyPhoneNumber,
            String emailAddress, String ptSSN, String dob, String gender, String ethnicAssociation,
            String maritalStatus, String currentPrimaryHCP, String comments, String nextOfKin,
            String nextOfKinRelationshipToPatient
    ) {
        try { 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
            CallableStatement stmt = con.prepareCall("{CALL insertPatient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            stmt.setString(1, patientID);
            stmt.setString(2, ptLastName);
            stmt.setString(3, ptPreviousLastName);
            stmt.setString(4, ptFirstName);
            stmt.setString(5, homeAddress1);
            stmt.setString(6, homeCity);
            stmt.setString(7, homeState);
            stmt.setString(8, homeZip);
            stmt.setString(9, country);
            stmt.setString(10, citizenship);
            stmt.setString(11, ptMobilePhone);
            stmt.setString(12, emergencyPhoneNumber);
            stmt.setString(13, emailAddress);
            stmt.setString(14, ptSSN);
            stmt.setString(15, dob);
            stmt.setString(16, gender);
            stmt.setString(17, ethnicAssociation);
            stmt.setString(18, maritalStatus);
            stmt.setString(19, currentPrimaryHCP);
            stmt.setString(20, comments);
            stmt.setString(21, nextOfKin);
            stmt.setString(22, nextOfKinRelationshipToPatient);

            stmt.execute();
            con.close();
            JOptionPane.showMessageDialog(null, "Patient saved successfully!");
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving patient: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean saveGeneralMedicalHistory(
            String patientID,
            String tobacco, String tobaccoQuantity, String tobaccoDuration,
            String alcohol, String alcoholQuantity, String alcoholDuration,
            String drug, String drugType, String drugDuration,
            String bloodType, String rh
    ) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
            CallableStatement stmt = con.prepareCall("{CALL insertGeneralMedicalHistory(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            stmt.setString(1, patientID);
            stmt.setString(2, tobacco);
            stmt.setString(3, tobaccoQuantity);
            stmt.setString(4, tobaccoDuration);
            stmt.setString(5, alcohol);
            stmt.setString(6, alcoholQuantity);
            stmt.setString(7, alcoholDuration);
            stmt.setString(8, drug);
            stmt.setString(9, drugType);
            stmt.setString(10, drugDuration);
            stmt.setString(11, bloodType);
            stmt.setString(12, rh);

            stmt.execute();
            con.close();
            JOptionPane.showMessageDialog(null, "General Medical History saved successfully!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving General Medical History: " + e.getMessage());
            return false;
        }
    }
    public static void retrievePatient(int patientID) {
        String query = "SELECT * FROM patientdemographics WHERE PatientID = ?";
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Set the patientID parameter
            stmt.setInt(1, patientID);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if the patient exists
            if (rs.next()) {
                // Retrieve the patient details from the ResultSet
                String ptLastName = rs.getString("PtLastName");
                String ptFirstName = rs.getString("PtFirstName");
                String homeAddress1 = rs.getString("HomeAddress1");
                String homeCity = rs.getString("HomeCity");
                String homeState = rs.getString("HomeState");
                String homeZip = rs.getString("HomeZip");
                String country = rs.getString("Country");
                String citizenship = rs.getString("Citizenship");
                String ptMobilePhone = rs.getString("PtMobilePhone");
                String emailAddress = rs.getString("EmailAddress");
                String ptSSN = rs.getString("ptSSN");
                Date dob = rs.getDate("DOB");
                String gender = rs.getString("Gender");
                String ethnicAssociation = rs.getString("EthnicAssociation");
                String maritalStatus = rs.getString("MaritalStatus");
                String currentPrimaryHCP = rs.getString("CurrentPrimaryHCP");
                String comments = rs.getString("Comments");
                String nextOfKin = rs.getString("NextOfKin");
                String nextOfKinRelationshipToPatient = rs.getString("NextOfKinRelationshipToPatient");

                // Display the patient details (you can adjust this to use a dialog or other UI)
                JOptionPane.showMessageDialog(null,
                        "Patient Info:\n" +
                        "Name: " + ptFirstName + " " + ptLastName + "\n" +
                        "Address: " + homeAddress1 + ", " + homeCity + ", " + homeState + " " + homeZip + ", " + country + "\n" +
                        "Phone: " + ptMobilePhone + "\n" +
                        "Email: " + emailAddress + "\n" +
                        "SSN: " + ptSSN + "\n" +
                        "DOB: " + dob.toString() + "\n" +
                        "Gender: " + gender + "\n" +
                        "Ethnicity: " + ethnicAssociation + "\n" +
                        "Marital Status: " + maritalStatus + "\n" +
                        "Primary HCP: " + currentPrimaryHCP + "\n" +
                        "Comments: " + comments + "\n" +
                        "Next of Kin: " + nextOfKin + " (" + nextOfKinRelationshipToPatient + ")");
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving patient: " + e.getMessage());
        }
    }

    // Method to delete a patient from the database
    public static boolean deletePatient(int patientID) {
      try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
        CallableStatement stmt = con.prepareCall("{CALL deletePatient(?)}");
        stmt.setInt(1, patientID);  // No need to parse again
        stmt.execute();
        con.close();
        JOptionPane.showMessageDialog(null, "Patient deleted successfully!");
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error deleting patient: " + e.getMessage());
        return false;
    }
    }


}
