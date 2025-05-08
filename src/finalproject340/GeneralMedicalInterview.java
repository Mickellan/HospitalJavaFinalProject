/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject340;
import java.sql.*;
import javax.swing.JOptionPane;

public class GeneralMedicalInterview {
    public static String tobacco = "";
    public static String tobaccoQuantity = "";
    public static String tobaccoDuration = "";

    public static String alcohol = "";
    public static String alcoholQuantity = "";
    public static String alcoholDuration = "";

    public static String drug = "";
    public static String drugType = "";
    public static String drugDuration = "";

    public static String bloodType = "";
    public static String rh = "";
    
    public static StringBuilder log = new StringBuilder();

    public static void startInterview(String patientName, int patientID) {
        InterviewNode root = new InterviewNode("Would you like to answer questions about your general medical history?");

        InterviewNode nodeTobacco = new InterviewNode("Have you used tobacco?");
        InterviewNode nodeAlcohol = new InterviewNode("Have you consumed alcohol?");
        InterviewNode nodeDrugs = new InterviewNode("Have you used drugs?");
        InterviewNode nodeBlood = new InterviewNode("Do you know your blood type?");
        InterviewNode nodeThankYou = new InterviewNode("Thank you for your time.");

        root.yesBranch = nodeTobacco;
        root.noBranch = nodeThankYou;

        nodeTobacco.yesBranch = nodeAlcohol;
        nodeTobacco.noBranch = nodeAlcohol;

        nodeAlcohol.yesBranch = nodeDrugs;
        nodeAlcohol.noBranch = nodeDrugs;

        nodeDrugs.yesBranch = nodeBlood;
        nodeDrugs.noBranch = nodeBlood;

        nodeBlood.yesBranch = null; 
        nodeBlood.noBranch = null;

        walkTree(root);

        InterviewLogger.saveLogFile(patientName, patientID, log.toString());
    }

    private static void walkTree(InterviewNode node) {
        if (node == null) return;

        int response = JOptionPane.showConfirmDialog(null, node.question, "Interview", JOptionPane.YES_NO_OPTION);
        log.append(node.question).append(" Answer: ").append(response == JOptionPane.YES_OPTION ? "Yes\n" : "No\n");

        if (node.question.contains("tobacco") && response == JOptionPane.YES_OPTION) {
            tobacco = "Yes";
            tobaccoQuantity = JOptionPane.showInputDialog("How much tobacco do you use?");
            tobaccoDuration = JOptionPane.showInputDialog("For how long have you used tobacco?");
            log.append("Tobacco Quantity: ").append(tobaccoQuantity).append("\n");
            log.append("Tobacco Duration: ").append(tobaccoDuration).append("\n");
        } else if (node.question.contains("tobacco")) {
            tobacco = "No";
        }

        if (node.question.contains("alcohol") && response == JOptionPane.YES_OPTION) {
            alcohol = "Yes";
            alcoholQuantity = JOptionPane.showInputDialog("How much alcohol do you consume?");
            alcoholDuration = JOptionPane.showInputDialog("For how long have you consumed alcohol?");
            log.append("Alcohol Quantity: ").append(alcoholQuantity).append("\n");
            log.append("Alcohol Duration: ").append(alcoholDuration).append("\n");
        } else if (node.question.contains("alcohol")) {
            alcohol = "No";
        }

        if (node.question.contains("drugs") && response == JOptionPane.YES_OPTION) {
            drug = "Yes";
            drugType = JOptionPane.showInputDialog("What type of drugs have you used?");
            drugDuration = JOptionPane.showInputDialog("For how long have you used drugs?");
            log.append("Drug Type: ").append(drugType).append("\n");
            log.append("Drug Duration: ").append(drugDuration).append("\n");
        } else if (node.question.contains("drugs")) {
            drug = "No";
        }

        if (node.question.contains("blood type") && response == JOptionPane.YES_OPTION) {
            bloodType = JOptionPane.showInputDialog("What is your blood type? (A, B, AB, or O)");
            rh = JOptionPane.showInputDialog("Is your Rh factor positive or negative? (Enter + or -)");
            log.append("Blood Type: ").append(bloodType).append("\n");
            log.append("Rh: ").append(rh).append("\n");
        }
        

        if (response == JOptionPane.YES_OPTION) {
            walkTree(node.yesBranch);
        } else {
            walkTree(node.noBranch);
        }
    }
    public static String getPatientNameByID(int patientID) {
    String name = "";
    try {
        Connection con = DBConnection.getConnection();
        String sql = "select FirstName, LastName from patientdemographic where PatientID = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, patientID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            name = rs.getString("FirstName") + " " + rs.getString("LastName");
        }
        rs.close();
        pst.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return name;
}

}
