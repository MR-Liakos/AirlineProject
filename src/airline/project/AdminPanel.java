package airline.project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;



public class AdminPanel extends javax.swing.JFrame {
    


    
    public AdminPanel() {
        initComponents();
        jTable.getTableHeader().setReorderingAllowed(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        loadDataFromFile("src\\TXTfiles\\FlightInfo.txt");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LogInPage loginPage = new LogInPage(); // Create an instance of LogInPage
                loginPage.setVisible(true); // Make the JFrame visible
                loginPage.setLocationRelativeTo(null); 
            }
        });
         DefaultCellEditor editor = (DefaultCellEditor)jTable.getDefaultEditor(Object.class);
         editor.setClickCountToStart(500000);
        ImageIcon icon = new ImageIcon("src\\logoCopy.png");
        setIconImage(icon.getImage());
        
        
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");   
      
    
    
    private void loadDataFromFile(String filePath) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Flight Number", "From", "To", "Date", "Cost","Time"}, 0); // Use existing model of jTable
        jTable.setModel(model);
        jTable.setRowSelectionAllowed(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);       
        model.setRowCount(0); // Clear existing data in the model

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Read the first line for headers
            if (headerLine != null) {
            // No need to set headers again because you've already defined them in the JTable setup
        }

        String line;
        while ((line = br.readLine()) != null) {
            String[] rowData = line.split(","); // Split each line into fields
            model.addRow(rowData); // Add the row data to the table model
        }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE); // Show error message
        }
    }
    
    public static boolean isNumeric(String inputString) { 
        if (inputString == null || inputString.trim().isEmpty()) {
            return false; // Null or empty string is not a valid double
        }
        if (!inputString.matches("-?\\d*(\\.\\d+)?")) {
            return false; // String does not match a valid double format
        }
        try {
            Double.valueOf(inputString);
            return true; // It is a valid double
        } catch (NumberFormatException e) {
            return false; // Not a valid double
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        timePicker1 = new com.raven.swing.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        FlightNumber = new javax.swing.JTextField();
        From = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        To = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Cost = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        Date = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Time = new javax.swing.JTextField();

        dateChooser1.setTextRefernce(Date);

        timePicker1.setDisplayText(Time);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 102, 0));

        jPanel2.setBackground(new java.awt.Color(102, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME TO ADMIN PANEL");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(102, 0, 102));
        jButton1.setText("LogOut");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(207, 207, 207))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight Number", "From", "To", "Date", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jTable.setRowHeight(30);
        jTable.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(jTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("Flight Number");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("From");

        FlightNumber.setBackground(new java.awt.Color(255, 255, 255));
        FlightNumber.setForeground(new java.awt.Color(0, 0, 0));
        FlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightNumberActionPerformed(evt);
            }
        });

        From.setBackground(new java.awt.Color(255, 255, 255));
        From.setForeground(new java.awt.Color(0, 0, 0));
        From.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paris", "Berlin", "Rome", "Madrid", "Lisbon", "Vienna", "Amsterdam", "Athens", "Stockholm", "Prague" }));
        From.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Date");

        To.setBackground(new java.awt.Color(255, 255, 255));
        To.setForeground(new java.awt.Color(0, 0, 0));
        To.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Paris", "Berlin", "Rome", "Madrid", "Lisbon", "Vienna", "Amsterdam", "Athens", "Stockholm", "Prague" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("To");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Cost");

        Cost.setBackground(new java.awt.Color(255, 255, 255));
        Cost.setForeground(new java.awt.Color(0, 0, 0));
        Cost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CostActionPerformed(evt);
            }
        });

        SaveButton.setBackground(new java.awt.Color(255, 255, 255));
        SaveButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(102, 0, 102));
        SaveButton.setText("Add Flight");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        DeleteButton.setBackground(new java.awt.Color(255, 255, 255));
        DeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeleteButton.setForeground(new java.awt.Color(102, 0, 102));
        DeleteButton.setText("Delete Flight");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        Date.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Time");

        Time.setBackground(new java.awt.Color(255, 255, 255));
        Time.setForeground(new java.awt.Color(0, 0, 0));
        Time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TimeMouseClicked(evt);
            }
        });
        Time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(FlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(From, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(To, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cost, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel3)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel5)
                        .addGap(112, 112, 112)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeleteButton)
                .addGap(386, 386, 386))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(From, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(To, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cost, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FlightNumberActionPerformed

    private void CostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CostActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
         // TODO add your handling code here:
        LocalDate inputDate = LocalDate.parse(Date.getText(), formatter);
        LocalDate today = LocalDate.now();
        File file = new File("src\\TXTfiles\\"+FlightNumber.getText()+".txt");
        

        if(FlightNumber.getText().equals("") || Cost.getText().equals("") || Date.getText().equals("") || From.getSelectedItem().equals("") || To.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(jPanel1, "You need to fiil all the fields");
        }else if(From.getSelectedItem().equals(To.getSelectedItem())){
            JOptionPane.showMessageDialog(jPanel1, "The destination must be different");
        }else if(inputDate.isBefore(today)){
            JOptionPane.showMessageDialog(jPanel1, "The date must be after totay");
        }else if(!isNumeric(Cost.getText())){
            JOptionPane.showMessageDialog(jPanel1, "Please input cost");
        }
        else if(file.exists()){
            JOptionPane.showMessageDialog(jPanel1, "There is a flight with that name");
        }
        else{
            String FL = FlightNumber.getText();
            String Co = Cost.getText();
            String Da = Date.getText();
            Object Fr = From.getSelectedItem();
            Object T = To.getSelectedItem();
            String TI = Time.getText();
            
            String data = FL+","+Fr+","+T+","+Da+","+Co+","+TI;
            try{
                FileWriter writer = new FileWriter("src\\TXTfiles\\FlightInfo.txt",true);
                writer.write(data);
                writer.write(System.getProperty("line.separator"));
                writer.close();
            // Display success message only after successfully writing to the file
                JOptionPane.showMessageDialog(jPanel1, "Success"); 
                Cost.setText("");
                FlightNumber.setText("");
                
                FileWriter creator = new FileWriter("src\\TXTfiles\\"+FL+".txt",false);
                String data2 = "0,0,0,0";
                for(int i=0;i<10;i++){
                    creator.write(data2);
                    creator.write(System.getProperty("line.separator"));
                }
                
                creator.close();
                     
        }catch(Exception e){
            System.out.println("An error occurred."); 
        }
        }
        
        loadDataFromFile("src\\TXTfiles\\FlightInfo.txt");
        
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable.getSelectedRow();
        boolean k = false;
        
        if (selectedRow != -1) { 
            String FLnumber = (String) jTable.getValueAt(selectedRow, 0);

            // File paths
            Path inputFile = Paths.get("src\\TXTfiles\\FlightInfo.txt");
            Path tempFile = Paths.get("src\\TXTfiles\\temp.txt");
            Path flightFile = Paths.get("src\\TXTfiles\\"+FLnumber + ".txt"); // File with the same name as FLnumber

            try (BufferedReader reader = Files.newBufferedReader(inputFile);
                BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(FLnumber)) {
                    writer.write(line);
                    writer.newLine();
                    } else {
                        found = true; // Flag if the flight was found and removed
                    }
                }

            if (found) {
                Files.delete(inputFile); // Delete the original file
                Files.move(tempFile, inputFile); // Rename temp file to original
                JOptionPane.showMessageDialog(jPanel1, "Flight deleted successfully!");
            
                // Delete the file corresponding to the flight number
                if (Files.exists(flightFile)) {
                    Files.delete(flightFile);
                
                } else {
                    JOptionPane.showMessageDialog(jPanel1, "Flight deleted, but associated file not found.");
                }
                // Remove the row from the table
                ((DefaultTableModel) jTable.getModel()).removeRow(selectedRow);
            } else {
            JOptionPane.showMessageDialog(jPanel1, "Flight not found in the file.");
            }
        
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(jPanel1, "Error occurred while deleting the flight.");
        
        }
        } else {
            JOptionPane.showMessageDialog(jPanel1, "Please Select a Flight to Delete.");
        }        
    loadDataFromFile("src\\TXTfiles\\FlightInfo.txt");
        
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();                
        LogInPage loginPage = new LogInPage(); // Create an instance of LogInPage
        loginPage.setVisible(true);
        loginPage.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FromActionPerformed

    private void TimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimeActionPerformed

    private void TimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimeMouseClicked
        // TODO add your handling code here:
        timePicker1.showPopup(this, 100, 100);
    }//GEN-LAST:event_TimeMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cost;
    private javax.swing.JTextField Date;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JTextField FlightNumber;
    private javax.swing.JComboBox<String> From;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField Time;
    private javax.swing.JComboBox<String> To;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private com.raven.swing.TimePicker timePicker1;
    // End of variables declaration//GEN-END:variables
}
