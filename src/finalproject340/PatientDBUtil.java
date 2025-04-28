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
            String homeAddress1, String homeCity, String homeStateProvinceRegion, String homeZip,
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
            stmt.setString(7, homeStateProvinceRegion);
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

}
