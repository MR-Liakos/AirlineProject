package airline.project;
import javax.swing.*;

public class AirlineProject {


    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            LogInPage loginPage = new LogInPage(); // Create an instance of LogInPage
            loginPage.setVisible(true); // Make the JFrame visible
            loginPage.setLocationRelativeTo(null);
        });       
    }
    
}
