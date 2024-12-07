package airline.project;

import javax.swing.ImageIcon;


public class About extends javax.swing.JDialog {


    
    public About(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ImageIcon icon = new ImageIcon("src\\logoCopy.png");
        setIconImage(icon.getImage());
        jTextPane1.setEditable(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextPane1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTextPane1.setText("Η εφαρμογή αυτή αποτελεί μια ολοκληρωμένη προσομοίωση συστήματος κρατήσεων και διαχείρισης για μια αεροπορική εταιρεία. Παρέχει δύο βασικές λειτουργίες:\n\n    1)   Διαχείριση Πτήσεων: Οι διαχειριστές μπορούν να συνδεθούν με ειδικούς κωδικούς και να προσθέσουν νέες πτήσεις στο σύστημα.\n\n\n    2)  Κρατήσεις για Επιβάτες: Οι χρήστες έχουν τη δυνατότητα να εγγραφούν, να επιλέξουν από τις διαθέσιμες πτήσεις, να κλείσουν εισιτήριο και να διαλέξουν τη θέση τους. Επίσης, μπορούν να προσθέσουν επιλογές όπως χειραποσκευή ή βαλίτσα.\n\nΤο σύστημα συνδυάζει λειτουργικότητα και ευκολία, προσφέροντας μια ολοκληρωμένη εμπειρία για τους διαχειριστές και τους επιβάτες.\n\nH εφαρμογή σχεδιάστηκε και αναπτύχθηκε στα πλαίσια του μαθήματος Αλληλεπίδραση Ανθρώπου-Μηχανής\n\nΓια περισσότερες πληροφορίες ή σχόλια, μπορείτε να επικοινωνήσετε στο:\nEmail: iliasalt@iee.ihu.gr");
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

/*
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                About dialog = new About(new javax.swing.JFrame(), true);
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
