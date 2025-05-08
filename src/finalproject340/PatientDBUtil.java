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
            
            if(patientID.equals("")){
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
            CallableStatement stmt = con.prepareCall("{CALL insertPatient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            stmt.setString(1, ptLastName);
            stmt.setString(2, ptPreviousLastName);
            stmt.setString(3, ptFirstName);
            stmt.setString(4, homeAddress1);
            stmt.setString(5, homeCity);
            stmt.setString(6, homeState);
            stmt.setString(7, homeZip);
            stmt.setString(8, country);
            stmt.setString(9, citizenship);
            stmt.setString(10, ptMobilePhone);
            stmt.setString(11, emergencyPhoneNumber);
            stmt.setString(12, emailAddress);
            stmt.setString(13, ptSSN);
            stmt.setString(14, dob);
            stmt.setString(15, gender);
            stmt.setString(16, ethnicAssociation);
            stmt.setString(17, maritalStatus);
            stmt.setString(18, currentPrimaryHCP);
            stmt.setString(19, comments);
            stmt.setString(20, nextOfKin);
            stmt.setString(21, nextOfKinRelationshipToPatient);


            stmt.execute();
            con.close();
            JOptionPane.showMessageDialog(null, "Patient saved successfully!");
            
            return true;
            }
            else{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
            CallableStatement stmt = con.prepareCall("{CALL updatePatient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            return false;
        }
    }
    public static Patient retrievePatient(int patientID) {
        String query = "SELECT * FROM patientdemographics WHERE PatientID = ?";
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, patientID);
            ResultSet rs = stmt.executeQuery();
            
        rs.next();
            Patient patient = new Patient(
                rs.getString("PtLastName"),
                rs.getString("PtFirstName"),
               rs.getString("PtPreviousLastName"),
                rs.getString("HomeAddress1"),
                rs.getString("HomeCity"),
                rs.getString("HomeState"),
                rs.getString("HomeZip"),
               rs.getString("Country"),
               rs.getString("Citizenship"),
               rs.getString("PtMobilePhone"),
                rs.getString("EmergencyPhoneNumber"),
                rs.getString("EmailAddress"),
               rs.getString("ptSSN"),
                rs.getDate("DOB"),
               rs.getString("Gender"),
                rs.getString("EthnicAssociation"),
                rs.getString("MaritalStatus"),
                rs.getString("CurrentPrimaryHCP"),
                rs.getString("Comments"),
                rs.getString("NextOfKin"),
                rs.getString("NextOfKinRelationshipToPatient")
            );     
                
         return patient;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving patient: " + e.getMessage());
        }
        return null;
    }
     public static Patient retrieveGeneral(String patientID) {
        String query = "SELECT * FROM generalmedicalhistorytable WHERE PatientID = ?";
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(patientID));
            ResultSet rs = stmt.executeQuery();
            
        rs.next();
            Patient patient = new Patient(
                rs.getInt("PatientID"),
                rs.getString("Tobacco"),
                rs.getString("TobaccoQuantity"),
                rs.getString("Tobaccoduraton"),
                rs.getString("Alcohol"),
                rs.getString("AlcoholQuantity"),
                rs.getString("Alcoholduration"),
                rs.getString("Drug"),
                rs.getString("DrugType"),
                rs.getString("Drugduration"),
                rs.getString("BloodType"),
                rs.getString("Rh")

            );     
                
         return patient;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deletePatient(int patientID) {
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
        CallableStatement stmt = con.prepareCall("{CALL deletePatient(?)}");
        stmt.setInt(1, patientID); 
        stmt.execute();
        con.close();
        JOptionPane.showMessageDialog(null, "Patient deleted successfully!");
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }


}
