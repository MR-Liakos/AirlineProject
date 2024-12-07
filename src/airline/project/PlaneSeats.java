package airline.project;
import java.awt.Color;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.io.FileWriter;
import java.io.*;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;

public class PlaneSeats extends javax.swing.JFrame {
    
    Color COLOR = new Color(51,102,0);
    String name1;String Us1;
    public int[][] seats = new int[10][4]; 
    String FLnumber;
    JButton[][] seatButtons;
    String K1,K2;
    int X,Y;
    double COST;
    String Date;
    String Time;
    
    
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
    public boolean GetSelectedRow(){
        int selectedRow = jTable1.getSelectedRow();
        boolean k = false;
        if (selectedRow != -1) { 
        
            FLnumber = (String) jTable1.getValueAt(selectedRow, 0);  
            Date = (String) jTable1.getValueAt(selectedRow, 1);
            Time = (String) jTable1.getValueAt(selectedRow, 2);
            String cost = (String) jTable1.getValueAt(selectedRow, 3);
            COST = Double.parseDouble(cost);
        

            k = true;
        } else {
            JOptionPane.showMessageDialog(jPanel1, "Please Select a Flight.");
        }
        return k;
    }
    
    
    public final void FLCheck(Object From,Object To){
        DefaultTableModel model = new DefaultTableModel(new String[] { "FlightNumber","Date","Time", "Cost" },0);
        jTable1.setModel(model);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        try(FileReader fr = new FileReader("src\\TXTfiles\\FlightInfo.txt"); Scanner reader = new Scanner(fr)){

            reader.useDelimiter("[,\n]");
            
            while(reader.hasNext()){
                String FlightNumber = reader.next();
                String FROM = reader.next();
                String TO = reader.next();
                String date = reader.next();
                String cost = reader.next();
                String TI = reader.next();
                
                
                if(From.equals(FROM) && To.equals(TO)){
                    model.addRow(new Object[]{FlightNumber,date,TI,cost});
                    
                }
            }

        }catch(Exception e){
            System.out.println("An error occurred in FLCheck: " + e.getMessage());
            
        }
    }
    public void FealArray(String FLnumber){
        
       
        try(FileReader fr = new FileReader("src\\TXTfiles\\"+FLnumber+".txt"); Scanner reader = new Scanner (fr);){


            for (int i = 0; i < seats.length; i++) {
                 if (reader.hasNextLine()) {
                    String line = reader.nextLine();           // Read the entire line
                    String[] values = line.split(",");          // Split by commas

                    for (int j = 0; j < values.length; j++) {
                        seats[i][j] = Integer.parseInt(values[j].trim()); // Parse each value to int and store
                        
                    }
                }
            }
            
               
        }catch(Exception e){
           System.out.println("An error occurred in FealArray ."); 
           
        }
    }
    public void FillSeats(){
        
        seatButtons = new JButton[][] {
            { A1, B1, C1, D1 }, 
            { A2, B2, C2, D2 },  
            { A3, B3, C3, D3 },  
            { A4, B4, C4, D4 },
            { A5, B5, C5, D5 },
            { A6, B6, C6, D6 },
            { A7, B7, C7, D7 },
            { A8, B8, C8, D8 },
            { A9, B9, C9, D9 },
            { A10,B10, C10, D10 },
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (seats[i][j] == 1) {  
                    seatButtons[i][j].setBackground(new Color (204, 204, 204));
                    seatButtons[i][j].setEnabled(false);
                }else{
                    seatButtons[i][j].setBackground(new Color(102,0,102));
                    seatButtons[i][j].setEnabled(true);
                }
            }
        }

    }
    
    public void FillFlightList(String FLnumber,String Date,String seat,String Time,String cost){
        
       // System.out.println("Flight:"+FLnumber+" | Date:"+Date+" | Time:"+Time.trim()+" | Price:"+cost+" | "+seat);
        
        try{
            FileWriter writer = new FileWriter("src\\TXTfiles\\"+Us1+".txt",true);
            writer.write(K1+" to "+K2+" | "+"Flight:"+FLnumber.trim()+" | "+"Date:"+Date.trim()+" | "+"Time:"+Time.trim()+" | "+"Price:"+cost.trim()+" | "+seat);
            
            writer.write(System.getProperty("line.separator"));
             writer.close();
        }catch(Exception e){
            System.out.println("An error occurred in pay info save."); 
        }
    }
    
    
    public PlaneSeats(Object From,Object To,String name,String Us) {
        
        initComponents();
        TabbedPane.setSelectedIndex(0);
        name1 = name;
        Us1 = Us;
        K1 = From.toString();
        K2 = To.toString();
        FLCheck(From,To);
        FROM.setText(From.toString());
        TO.setText(To.toString());
        A12.setEnabled(false);
        A13.setEnabled(false);
        A11.setEnabled(false);
        ImageIcon icon = new ImageIcon("src\\logoCopy.png");
        setIconImage(icon.getImage());
        DefaultCellEditor editor = (DefaultCellEditor)jTable1.getDefaultEditor(Object.class);
        editor.setClickCountToStart(50000);
        jTable1.getTableHeader().setReorderingAllowed(false);
        
  
    }
    public void CheackSeats(){
                seatButtons = new JButton[][] {
            { A1, B1, C1, D1 },  // Row A
            { A2, B2, C2, D2 },  // Row B
            { A3, B3, C3, D3 },  // Row C
            { A4, B4, C4, D4 },
            { A5, B5, C5, D5 },
            { A6, B6, C6, D6 },
            { A7, B7, C7, D7 },
            { A8, B8, C8, D8 },
            { A9, B9, C9, D9 },
            { A10,B10, C10, D10 },// Row D
        };
        
           for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (seatButtons[i][j].getBackground()== COLOR) {  
                    seatButtons[i][j].setBackground(new Color(102,0,102));
   
                }
            }
        }
           
    }
    
    public void ReturnPosition(){
                seatButtons = new JButton[][] {
            { A1, B1, C1, D1 },  // Row A
            { A2, B2, C2, D2 },  // Row B
            { A3, B3, C3, D3 },  // Row C
            { A4, B4, C4, D4 },
            { A5, B5, C5, D5 },
            { A6, B6, C6, D6 },
            { A7, B7, C7, D7 },
            { A8, B8, C8, D8 },
            { A9, B9, C9, D9 },
            { A10,B10, C10, D10 },// Row D
        }; 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (seatButtons[i][j].getBackground()== COLOR) {  
                    X = i;
                    Y = j;
   
                }
            }
        }               
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        FlSelection = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        NextButton = new javax.swing.JButton();
        CanselButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TO = new javax.swing.JLabel();
        FROM = new javax.swing.JLabel();
        SeatSelection = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jButtonCansel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        A11 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        A2 = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        A5 = new javax.swing.JButton();
        A6 = new javax.swing.JButton();
        A7 = new javax.swing.JButton();
        A8 = new javax.swing.JButton();
        A9 = new javax.swing.JButton();
        A10 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();
        B10 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        C8 = new javax.swing.JButton();
        C9 = new javax.swing.JButton();
        C10 = new javax.swing.JButton();
        D1 = new javax.swing.JButton();
        D2 = new javax.swing.JButton();
        D3 = new javax.swing.JButton();
        D4 = new javax.swing.JButton();
        D5 = new javax.swing.JButton();
        D6 = new javax.swing.JButton();
        D7 = new javax.swing.JButton();
        D8 = new javax.swing.JButton();
        D9 = new javax.swing.JButton();
        D10 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        A1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        A12 = new javax.swing.JButton();
        A13 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        SE = new javax.swing.JLabel();
        Payment = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        FROMTO = new javax.swing.JLabel();
        INFOS = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        NextButton1 = new javax.swing.JButton();
        NextButton2 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabbedPane.setForeground(new java.awt.Color(255, 255, 255));
        TabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        TabbedPane.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(102, 0, 102));
        jPanel2.setAlignmentY(0.0F);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Please Select Your Flight");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Date", "Cost"
            }

        ));
        jTable1.setRowHeight(35);
        jTable1.setShowHorizontalLines(true);
        jScrollPane3.setViewportView(jTable1);

        NextButton.setBackground(new java.awt.Color(0, 102, 102));
        NextButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NextButton.setForeground(new java.awt.Color(255, 255, 255));
        NextButton.setText("Next");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        CanselButton.setBackground(new java.awt.Color(0, 102, 102));
        CanselButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CanselButton.setForeground(new java.awt.Color(255, 255, 255));
        CanselButton.setText("Back");
        CanselButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanselButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Here are the available flights ");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TO:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("FROM:");

        TO.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        TO.setForeground(new java.awt.Color(0, 0, 0));
        TO.setText("jLabel7");

        FROM.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        FROM.setForeground(new java.awt.Color(0, 0, 0));
        FROM.setText("jLabel7");

        javax.swing.GroupLayout FlSelectionLayout = new javax.swing.GroupLayout(FlSelection);
        FlSelection.setLayout(FlSelectionLayout);
        FlSelectionLayout.setHorizontalGroup(
            FlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FlSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(FlSelectionLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(CanselButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(302, 302, 302)
                .addComponent(NextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(FlSelectionLayout.createSequentialGroup()
                .addGroup(FlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FlSelectionLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FROM)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FlSelectionLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(63, 63, 63)))
                .addGap(73, 73, 73))
        );
        FlSelectionLayout.setVerticalGroup(
            FlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FlSelectionLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addGroup(FlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FROM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addGroup(FlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CanselButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        TabbedPane.addTab("tab2", FlSelection);

        SeatSelection.setBackground(new java.awt.Color(255, 255, 255));
        SeatSelection.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setAlignmentY(0.0F);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Please Select Your Seat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel28)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jButtonCansel.setBackground(new java.awt.Color(0, 102, 102));
        jButtonCansel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonCansel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCansel.setText("Back");
        jButtonCansel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCanselActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        A11.setBackground(new java.awt.Color(51, 102, 0));
        A11.setForeground(new java.awt.Color(0, 255, 0));
        A11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A11MouseClicked(evt);
            }
        });
        A11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A11ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText(" A  B        C   D");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 250, 50));

        A2.setBackground(new java.awt.Color(204, 204, 204));
        A2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A2MouseClicked(evt);
            }
        });
        A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A2ActionPerformed(evt);
            }
        });
        jPanel3.add(A2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 40, 40));

        A3.setBackground(new java.awt.Color(204, 204, 204));
        A3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A3MouseClicked(evt);
            }
        });
        A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3ActionPerformed(evt);
            }
        });
        jPanel3.add(A3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 40, 40));

        A4.setBackground(new java.awt.Color(204, 204, 204));
        A4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A4MouseClicked(evt);
            }
        });
        A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4ActionPerformed(evt);
            }
        });
        jPanel3.add(A4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 40, 40));

        A5.setBackground(new java.awt.Color(204, 204, 204));
        A5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A5MouseClicked(evt);
            }
        });
        A5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A5ActionPerformed(evt);
            }
        });
        jPanel3.add(A5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 40, 40));

        A6.setBackground(new java.awt.Color(204, 204, 204));
        A6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A6MouseClicked(evt);
            }
        });
        A6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A6ActionPerformed(evt);
            }
        });
        jPanel3.add(A6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 40, 40));

        A7.setBackground(new java.awt.Color(204, 204, 204));
        A7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A7MouseClicked(evt);
            }
        });
        A7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A7ActionPerformed(evt);
            }
        });
        jPanel3.add(A7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 40, 40));

        A8.setBackground(new java.awt.Color(204, 204, 204));
        A8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A8MouseClicked(evt);
            }
        });
        A8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A8ActionPerformed(evt);
            }
        });
        jPanel3.add(A8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 40, 40));

        A9.setBackground(new java.awt.Color(204, 204, 204));
        A9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A9MouseClicked(evt);
            }
        });
        A9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A9ActionPerformed(evt);
            }
        });
        jPanel3.add(A9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 40, 40));

        A10.setBackground(new java.awt.Color(204, 204, 204));
        A10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A10MouseClicked(evt);
            }
        });
        A10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A10ActionPerformed(evt);
            }
        });
        jPanel3.add(A10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 40, 40));

        B1.setBackground(new java.awt.Color(204, 204, 204));
        B1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B1MouseClicked(evt);
            }
        });
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });
        jPanel3.add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 40, 40));

        B2.setBackground(new java.awt.Color(204, 204, 204));
        B2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B2MouseClicked(evt);
            }
        });
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });
        jPanel3.add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 40, 40));

        B3.setBackground(new java.awt.Color(204, 204, 204));
        B3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B3MouseClicked(evt);
            }
        });
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });
        jPanel3.add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 40, 40));

        B4.setBackground(new java.awt.Color(204, 204, 204));
        B4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B4MouseClicked(evt);
            }
        });
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });
        jPanel3.add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 40, 40));

        B5.setBackground(new java.awt.Color(204, 204, 204));
        B5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B5MouseClicked(evt);
            }
        });
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });
        jPanel3.add(B5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 40, 40));

        B6.setBackground(new java.awt.Color(204, 204, 204));
        B6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B6MouseClicked(evt);
            }
        });
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });
        jPanel3.add(B6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 40, 40));

        B7.setBackground(new java.awt.Color(204, 204, 204));
        B7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B7MouseClicked(evt);
            }
        });
        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });
        jPanel3.add(B7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 40, 40));

        B8.setBackground(new java.awt.Color(204, 204, 204));
        B8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B8MouseClicked(evt);
            }
        });
        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });
        jPanel3.add(B8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 40, 40));

        B9.setBackground(new java.awt.Color(204, 204, 204));
        B9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B9MouseClicked(evt);
            }
        });
        B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B9ActionPerformed(evt);
            }
        });
        jPanel3.add(B9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 40, 40));

        B10.setBackground(new java.awt.Color(204, 204, 204));
        B10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B10MouseClicked(evt);
            }
        });
        B10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B10ActionPerformed(evt);
            }
        });
        jPanel3.add(B10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 570, 40, 40));

        C1.setBackground(new java.awt.Color(102, 0, 102));
        C1.setForeground(new java.awt.Color(102, 0, 102));
        C1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C1MouseClicked(evt);
            }
        });
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });
        jPanel3.add(C1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 40, 40));

        C2.setBackground(new java.awt.Color(102, 0, 102));
        C2.setForeground(new java.awt.Color(102, 0, 102));
        C2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C2MouseClicked(evt);
            }
        });
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });
        jPanel3.add(C2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 40, 40));

        C3.setBackground(new java.awt.Color(204, 204, 204));
        C3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C3MouseClicked(evt);
            }
        });
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });
        jPanel3.add(C3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 40, 40));

        C4.setBackground(new java.awt.Color(204, 204, 204));
        C4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C4MouseClicked(evt);
            }
        });
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });
        jPanel3.add(C4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 40, 40));

        C5.setBackground(new java.awt.Color(204, 204, 204));
        C5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C5MouseClicked(evt);
            }
        });
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });
        jPanel3.add(C5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 40, 40));

        C6.setBackground(new java.awt.Color(204, 204, 204));
        C6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C6MouseClicked(evt);
            }
        });
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });
        jPanel3.add(C6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 40, 40));

        C7.setBackground(new java.awt.Color(204, 204, 204));
        C7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C7MouseClicked(evt);
            }
        });
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });
        jPanel3.add(C7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 40, 40));

        C8.setBackground(new java.awt.Color(204, 204, 204));
        C8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C8MouseClicked(evt);
            }
        });
        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });
        jPanel3.add(C8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 40, 40));

        C9.setBackground(new java.awt.Color(204, 204, 204));
        C9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C9MouseClicked(evt);
            }
        });
        C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C9ActionPerformed(evt);
            }
        });
        jPanel3.add(C9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 40, 40));

        C10.setBackground(new java.awt.Color(204, 204, 204));
        C10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C10MouseClicked(evt);
            }
        });
        C10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C10ActionPerformed(evt);
            }
        });
        jPanel3.add(C10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 40, 40));

        D1.setBackground(new java.awt.Color(102, 0, 102));
        D1.setForeground(new java.awt.Color(102, 0, 102));
        D1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D1MouseClicked(evt);
            }
        });
        D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D1ActionPerformed(evt);
            }
        });
        jPanel3.add(D1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 40, 40));

        D2.setBackground(new java.awt.Color(204, 204, 204));
        D2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D2MouseClicked(evt);
            }
        });
        D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D2ActionPerformed(evt);
            }
        });
        jPanel3.add(D2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 40, 40));

        D3.setBackground(new java.awt.Color(204, 204, 204));
        D3.setForeground(new java.awt.Color(204, 204, 204));
        D3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D3MouseClicked(evt);
            }
        });
        D3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D3ActionPerformed(evt);
            }
        });
        jPanel3.add(D3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 40, 40));

        D4.setBackground(new java.awt.Color(204, 204, 204));
        D4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D4MouseClicked(evt);
            }
        });
        D4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D4ActionPerformed(evt);
            }
        });
        jPanel3.add(D4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 40, 40));

        D5.setBackground(new java.awt.Color(204, 204, 204));
        D5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D5MouseClicked(evt);
            }
        });
        D5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D5ActionPerformed(evt);
            }
        });
        jPanel3.add(D5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 40, 40));

        D6.setBackground(new java.awt.Color(204, 204, 204));
        D6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D6MouseClicked(evt);
            }
        });
        D6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D6ActionPerformed(evt);
            }
        });
        jPanel3.add(D6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 40, 40));

        D7.setBackground(new java.awt.Color(204, 204, 204));
        D7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D7MouseClicked(evt);
            }
        });
        D7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D7ActionPerformed(evt);
            }
        });
        jPanel3.add(D7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 40, 40));

        D8.setBackground(new java.awt.Color(204, 204, 204));
        D8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D8MouseClicked(evt);
            }
        });
        D8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D8ActionPerformed(evt);
            }
        });
        jPanel3.add(D8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, 40, 40));

        D9.setBackground(new java.awt.Color(204, 204, 204));
        D9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D9MouseClicked(evt);
            }
        });
        D9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D9ActionPerformed(evt);
            }
        });
        jPanel3.add(D9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 40, 40));

        D10.setBackground(new java.awt.Color(204, 204, 204));
        D10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D10MouseClicked(evt);
            }
        });
        D10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D10ActionPerformed(evt);
            }
        });
        jPanel3.add(D10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 40, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("2");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 30, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("3");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 30, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("4");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 30, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("5");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 30, 50));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("6");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 30, 50));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("7");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 30, 50));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("8");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 30, 50));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("9");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 30, 50));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("10");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, 50, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("1");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 30, 50));

        A1.setBackground(new java.awt.Color(204, 204, 204));
        A1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A1MouseClicked(evt);
            }
        });
        A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A1ActionPerformed(evt);
            }
        });
        jPanel3.add(A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 40, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Untitled.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, -2, -1, 680));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Seat Not Available");

        A12.setBackground(new java.awt.Color(102, 0, 102));
        A12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A12MouseClicked(evt);
            }
        });
        A12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A12ActionPerformed(evt);
            }
        });

        A13.setBackground(new java.awt.Color(204, 204, 204));
        A13.setForeground(new java.awt.Color(255, 0, 0));
        A13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                A13MouseClicked(evt);
            }
        });
        A13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A13ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Your selected seat");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText(" Available Seats");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("You have selected ");

        SE.setBackground(new java.awt.Color(51, 255, 51));
        SE.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout SeatSelectionLayout = new javax.swing.GroupLayout(SeatSelection);
        SeatSelection.setLayout(SeatSelectionLayout);
        SeatSelectionLayout.setHorizontalGroup(
            SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeatSelectionLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SeatSelectionLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SeatSelectionLayout.createSequentialGroup()
                                .addComponent(A11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18))
                            .addGroup(SeatSelectionLayout.createSequentialGroup()
                                .addComponent(A13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addGroup(SeatSelectionLayout.createSequentialGroup()
                                .addComponent(A12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SE)
                                    .addComponent(jLabel19))))
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(SeatSelectionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(SeatSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCansel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SeatSelectionLayout.setVerticalGroup(
            SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeatSelectionLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SeatSelectionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SeatSelectionLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(SE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(97, 97, 97)))
                .addGroup(SeatSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeatSelectionLayout.createSequentialGroup()
                        .addComponent(jButtonCansel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeatSelectionLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );

        TabbedPane.addTab("tab2", SeatSelection);

        Payment.setBackground(new java.awt.Color(255, 255, 255));
        Payment.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(102, 0, 102));
        jPanel4.setAlignmentY(0.0F);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("FINAL STAGE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel21)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Screenshot_2.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Microsoft YaHei", 1, 22)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Please select what type of bags you want to bring ");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Screenshot_3.png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Price Breakdown:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Flight");

        FROMTO.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        FROMTO.setForeground(new java.awt.Color(0, 0, 0));
        FROMTO.setText("FROM&TO");

        INFOS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        INFOS.setForeground(new java.awt.Color(0, 0, 0));
        INFOS.setText("DATE........");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("1 x Basic Ticket");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("200€");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Total to pay:");

        NextButton1.setBackground(new java.awt.Color(0, 102, 102));
        NextButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NextButton1.setForeground(new java.awt.Color(255, 255, 255));
        NextButton1.setText("Back");
        NextButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButton1ActionPerformed(evt);
            }
        });

        NextButton2.setBackground(new java.awt.Color(0, 102, 102));
        NextButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NextButton2.setForeground(new java.awt.Color(255, 255, 255));
        NextButton2.setText("Pay now");
        NextButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButton2ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("or");

        jButton1.setBackground(new java.awt.Color(102, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("1 Small Bag only");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 0, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add for  € 35.99");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FROMTO, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(INFOS, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(NextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NextButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel23)))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addGap(120, 120, 120)))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(FROMTO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(INFOS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NextButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout PaymentLayout = new javax.swing.GroupLayout(Payment);
        Payment.setLayout(PaymentLayout);
        PaymentLayout.setHorizontalGroup(
            PaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaymentLayout.setVerticalGroup(
            PaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabbedPane.addTab("tab3", Payment);

        jPanel.add(TabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 570, 920));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 564, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCanselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCanselActionPerformed
        // TODO add your handling code here:
        TabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_jButtonCanselActionPerformed

    private void A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A1ActionPerformed
                CheackSeats();
        A1.setBackground(COLOR);
        SE.setText("A1 Seat");
        ReturnPosition();
        
        
        
    }//GEN-LAST:event_A1ActionPerformed

    private void A1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A1MouseClicked

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        // TODO add your handling code here:
       
        if(GetSelectedRow()){
            FealArray(FLnumber);
            TabbedPane.setSelectedIndex(1);
            FillSeats();
        }
        
    }//GEN-LAST:event_NextButtonActionPerformed

    private void CanselButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanselButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        BookingPanel booking = new BookingPanel(name1, Us1);
        booking.setLocationRelativeTo(null);
        booking.setVisible(true);
        
    }//GEN-LAST:event_CanselButtonActionPerformed

    private void A2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A2MouseClicked



    }//GEN-LAST:event_A2MouseClicked

    private void A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A2ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A2.setBackground(COLOR);
        SE.setText("A2 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A2ActionPerformed

    private void A3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A3MouseClicked

    private void A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A3ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A3.setBackground(COLOR);
        SE.setText("A3 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A3ActionPerformed

    private void A4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A4MouseClicked

    private void A4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A4ActionPerformed
        // TODO add your handling code here:
                        CheackSeats();
        A4.setBackground(COLOR);
        SE.setText("A4 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A4ActionPerformed

    private void A5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A5MouseClicked

    private void A5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A5ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A5.setBackground(COLOR);
        SE.setText("A5 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A5ActionPerformed

    private void A6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A6MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A6MouseClicked

    private void A6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A6ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A6.setBackground(COLOR);
        SE.setText("A6 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A6ActionPerformed

    private void A7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A7MouseClicked


    }//GEN-LAST:event_A7MouseClicked

    private void A7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A7ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A7.setBackground(COLOR);
        SE.setText("A7 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A7ActionPerformed

    private void A8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A8MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A8MouseClicked

    private void A8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A8ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A8.setBackground(COLOR);
        SE.setText("A8 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A8ActionPerformed

    private void A9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_A9MouseClicked

    private void A9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A9ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A9.setBackground(COLOR);
        SE.setText("A9 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A9ActionPerformed

    private void A10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A10MouseClicked
        // TODO add your handling code here:
 
    }//GEN-LAST:event_A10MouseClicked

    private void A10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A10ActionPerformed
        // TODO add your handling code here:
                CheackSeats();
        A10.setBackground(COLOR);
        SE.setText("A10 Seat");
        ReturnPosition();
    }//GEN-LAST:event_A10ActionPerformed

    private void B1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B1MouseClicked

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B1.setBackground(COLOR);
        SE.setText("B1 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B1ActionPerformed

    private void B2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B2MouseClicked

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B2.setBackground(COLOR);
        SE.setText("B2 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B2ActionPerformed

    private void B3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B3MouseClicked

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        B3.setBackground(COLOR);
        SE.setText("B3 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B3ActionPerformed

    private void B4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B4MouseClicked

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        CheackSeats();
        B4.setBackground(COLOR);
        SE.setText("B4 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B4ActionPerformed

    private void B5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B5MouseClicked

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B5.setBackground(COLOR);
        SE.setText("B5 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B5ActionPerformed

    private void B6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B6MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B6MouseClicked

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B6.setBackground(COLOR);
        SE.setText("B6 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B6ActionPerformed

    private void B7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B7MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B7MouseClicked

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B7.setBackground(COLOR);
        SE.setText("B7 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B7ActionPerformed

    private void B8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B8MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B8MouseClicked

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B8.setBackground(COLOR);
        SE.setText("B8 Seat");
        ReturnPosition();
        
    }//GEN-LAST:event_B8ActionPerformed

    private void B9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B9MouseClicked

    private void B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B9ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        B9.setBackground(COLOR);
        SE.setText("B9 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B9ActionPerformed

    private void B10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B10MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_B10MouseClicked

    private void B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B10ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        B10.setBackground(COLOR);
        SE.setText("B10 Seat");
        ReturnPosition();
    }//GEN-LAST:event_B10ActionPerformed

    private void C1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C1MouseClicked

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C1.setBackground(COLOR);
        SE.setText("C1 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C1ActionPerformed

    private void D1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_D1MouseClicked

    private void D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D1ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        D1.setBackground(COLOR);
        SE.setText("D1 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D1ActionPerformed

    private void C2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C2MouseClicked

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C2.setBackground(COLOR);
        SE.setText("C2 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C2ActionPerformed

    private void C3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C3MouseClicked

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C3.setBackground(COLOR);
        SE.setText("C3 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C3ActionPerformed

    private void C4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C4MouseClicked

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C4.setBackground(COLOR);
        SE.setText("C4 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C4ActionPerformed

    private void C5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C5MouseClicked

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C5.setBackground(COLOR);
        SE.setText("C5 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C5ActionPerformed

    private void C6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C6MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C6MouseClicked

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C6.setBackground(COLOR);
        SE.setText("C6 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C6ActionPerformed

    private void C7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C7MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C7MouseClicked

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C7.setBackground(COLOR);
        SE.setText("C7 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C7ActionPerformed

    private void C8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C8MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C8MouseClicked

    private void C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C8ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C8.setBackground(COLOR);
        SE.setText("C8 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C8ActionPerformed

    private void C9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C9MouseClicked

    private void C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C9ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C9.setBackground(COLOR);
        SE.setText("C9 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C9ActionPerformed

    private void C10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C10MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_C10MouseClicked

    private void C10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C10ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        C10.setBackground(COLOR);
        SE.setText("C10 Seat");
        ReturnPosition();
    }//GEN-LAST:event_C10ActionPerformed

    private void D2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_D2MouseClicked

    private void D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D2ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        D2.setBackground(COLOR);
        SE.setText("D2 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D2ActionPerformed

    private void D3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_D3MouseClicked

    private void D3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D3ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        D3.setBackground(COLOR);
        SE.setText("D3 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D3ActionPerformed

    private void D4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_D4MouseClicked

    private void D4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D4ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        D4.setBackground(COLOR);
        SE.setText("D4 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D4ActionPerformed

    private void D5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_D5MouseClicked

    private void D5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D5ActionPerformed
        // TODO add your handling code here:
                                CheackSeats();
        D5.setBackground(COLOR);
        SE.setText("D5 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D5ActionPerformed

    private void D6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D6MouseClicked
        // TODO add your handling code here:
                        
    }//GEN-LAST:event_D6MouseClicked

    private void D6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D6ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        D6.setBackground(COLOR);
        SE.setText("D6 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D6ActionPerformed

    private void D7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D7MouseClicked
        // TODO add your handling code here:
                        
    }//GEN-LAST:event_D7MouseClicked

    private void D7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D7ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        D7.setBackground(COLOR);
        SE.setText("D7 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D7ActionPerformed

    private void D8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D8MouseClicked
        // TODO add your handling code here:
                        
    }//GEN-LAST:event_D8MouseClicked

    private void D8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D8ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        D8.setBackground(COLOR);
        SE.setText("D8 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D8ActionPerformed

    private void D9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D9MouseClicked
        // TODO add your handling code here:
                        
    }//GEN-LAST:event_D9MouseClicked

    private void D9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D9ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        D9.setBackground(COLOR);
        SE.setText("D9 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D9ActionPerformed

    private void D10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D10MouseClicked
        // TODO add your handling code here:
                        
        
    }//GEN-LAST:event_D10MouseClicked

    private void D10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D10ActionPerformed
        // TODO add your handling code here:
        CheackSeats();
        D10.setBackground(COLOR);
        SE.setText("D10 Seat");
        ReturnPosition();
    }//GEN-LAST:event_D10ActionPerformed

    private void A11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_A11MouseClicked

    private void A11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A11ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_A11ActionPerformed

    private void A12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_A12MouseClicked

    private void A12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A12ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_A12ActionPerformed

    private void A13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_A13MouseClicked

    private void A13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A13ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_A13ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jLabel30.setText("");
        jLabel33.setText("");
        jLabel31.setText("");
        jLabel32.setText("");
        jButton1.setBackground(new Color(102,0,102));
        jButton3.setBackground(new Color(102,0,102));
        
        
        if(SE.getText().equals("")){
            JOptionPane.showMessageDialog(jPanel1, "Please Select a Seat.");
        }else{
            TabbedPane.setSelectedIndex(2);
            
        }
        FROMTO.setText(K1+" to "+K2);
        INFOS.setText(Date.trim()+" • "+"Time:"+Time.trim()+" • "+FLnumber.trim());
        String yourString = Double.toString(COST);
        jLabel30.setText(yourString+"€");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void NextButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButton1ActionPerformed
        // TODO add your handling code here:
        TabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_NextButton1ActionPerformed

    private void NextButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButton2ActionPerformed
        // TODO add your handling code here:
        if((jButton1.getBackground() != COLOR) && (jButton3.getBackground() != COLOR) ){
            JOptionPane.showMessageDialog(jPanel1, "Please Select a Bag Option.");
              
        }else{
            seats[X][Y] = 1;
            try (FileWriter writer = new FileWriter("src\\TXTfiles\\"+FLnumber+".txt")) {
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        writer.write(seats[i][j]+"");
                        if (j < seats[i].length - 1) { // Add a comma only if it's not the last element in the row
                            writer.write(",");
                        }
                    }
                writer.write("\n"); // Move to the next line after each row
            }           
            } catch (IOException e) {
                    e.printStackTrace();
            }
            
            
            FillFlightList(FLnumber,Date,SE.getText(),Time,jLabel33.getText());
            JOptionPane.showMessageDialog(jPanel1, "You Successful booked a Flight From " +K1+" To "+K2+".");
            dispose();
            BookingPanel booking = new BookingPanel(name1, Us1);
            booking.setLocationRelativeTo(null);
            booking.setVisible(true);
            
            
        }
        
    }//GEN-LAST:event_NextButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jButton1.setBackground(COLOR);
        jButton3.setBackground(new Color(102,0,102));
        jLabel31.setText("1 x small hand luggage");
        jLabel32.setText("Included");
        String yourString = Double.toString(COST);
        jLabel33.setText(yourString+"€");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:.
        jButton3.setBackground(COLOR);
        jButton1.setBackground(new Color(102,0,102));
        jLabel31.setText("1 x Priority & 2 cabin bags");
        jLabel32.setText("35€");
  
        
        String yourString = Double.toString(COST+35);
        jLabel33.setText(yourString+"€");
        
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A1;
    private javax.swing.JButton A10;
    private javax.swing.JButton A11;
    private javax.swing.JButton A12;
    private javax.swing.JButton A13;
    private javax.swing.JButton A2;
    private javax.swing.JButton A3;
    private javax.swing.JButton A4;
    private javax.swing.JButton A5;
    private javax.swing.JButton A6;
    private javax.swing.JButton A7;
    private javax.swing.JButton A8;
    private javax.swing.JButton A9;
    private javax.swing.JButton B1;
    private javax.swing.JButton B10;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton B9;
    private javax.swing.JButton C1;
    private javax.swing.JButton C10;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton C8;
    private javax.swing.JButton C9;
    private javax.swing.JButton CanselButton;
    private javax.swing.JButton D1;
    private javax.swing.JButton D10;
    private javax.swing.JButton D2;
    private javax.swing.JButton D3;
    private javax.swing.JButton D4;
    private javax.swing.JButton D5;
    private javax.swing.JButton D6;
    private javax.swing.JButton D7;
    private javax.swing.JButton D8;
    private javax.swing.JButton D9;
    private javax.swing.JLabel FROM;
    private javax.swing.JLabel FROMTO;
    private javax.swing.JPanel FlSelection;
    private javax.swing.JLabel INFOS;
    private javax.swing.JButton NextButton;
    private javax.swing.JButton NextButton1;
    private javax.swing.JButton NextButton2;
    private javax.swing.JPanel Payment;
    private javax.swing.JLabel SE;
    private javax.swing.JPanel SeatSelection;
    private javax.swing.JLabel TO;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCansel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
