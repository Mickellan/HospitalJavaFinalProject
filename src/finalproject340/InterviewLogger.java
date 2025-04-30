/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject340;

import java.io.*;
import java.time.LocalDate;

public class InterviewLogger {
     public static void saveLogFile(String patientName, int patientID, String content) {
        String filename = "interview_" + patientID + "_" + LocalDate.now() + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Patient Name: " + patientName + "\n");
            writer.write("Patient ID: " + patientID + "\n\n");
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
