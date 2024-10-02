package registration_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.regex.Pattern;


public class Semester extends javax.swing.JFrame {
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String strDashLine = "--------------------";
    Functions f = new Functions();
    /**
     * Creates new form Student
     */
       
    public boolean isFieldValidated(){
        return txtSemester.getText().matches("^[1-3]{1}");
    }
    
    public void resetButtons(){
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
    }
    public void refreshTable(){
        try {
            ps = conn.prepareStatement("SELECT * FROM oop23.semester");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e) {
            f.displayError(e);
        }
        lblTitle.requestFocusInWindow();
    }  
    public void searchTable(String strSemester){
        try{
                ps = conn.prepareStatement("Select * FROM oop23.semester WHERE semester = '" + strSemester + "';");
                rs = ps.executeQuery();
                if(rs.next()){
                    txtSemester.setText(rs.getString("semester"));
                }
            }
            catch(Exception e) {
                f.displayError(e);
            }
    }
    public void setFormEditable(boolean blFlag){
        txtSemester.setEditable(blFlag);
    }
    public void clearForms(){
        txtSemester.setText(null);
    }
    
    public Semester() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblForms = new javax.swing.JTable();
        pnlStudent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblInvalidSemester = new javax.swing.JLabel();
        txtSemester = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblForms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblForms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblForms);

        pnlStudent.setBackground(new java.awt.Color(204, 255, 255));
        pnlStudent.setForeground(new java.awt.Color(255, 51, 51));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("View Semester Information");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Semester (Required)");

        lblInvalidSemester.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidSemester.setText(" invalid format, should be [1-2] ");

        txtSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSemesterActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConfirm.setText("Confirm");
        btnConfirm.setEnabled(false);
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStudentLayout = new javax.swing.GroupLayout(pnlStudent);
        pnlStudent.setLayout(pnlStudentLayout);
        pnlStudentLayout.setHorizontalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSemester, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnDelete)
                            .addGap(18, 18, 18)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnConfirm)))
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblInvalidSemester)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        pnlStudentLayout.setVerticalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(33, 33, 33)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInvalidSemester)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(txtSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnAdd)
                    .addComponent(btnConfirm))
                .addGap(85, 85, 85)
                .addComponent(jLabel15))
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        int intRow = tblForms.getSelectedRow();
        String strSemester;
        strSemester = tblForms.getValueAt(intRow, 0).toString();
        removeError();
        resetButtons();
        try{
                ps = conn.prepareStatement("Select * FROM oop23.semester WHERE semester = '" + strSemester + "';");
                rs = ps.executeQuery();
                if(rs.next()){
                    txtSemester.setText(rs.getString("semester"));
                }
            }
            catch(Exception e) {
                f.displayError(e);
            }
        
        
    }//GEN-LAST:event_tblFormsMouseClicked
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        removeError();
        refreshTable();
        setFormEditable(false);
    }//GEN-LAST:event_formWindowOpened

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        txtSemester.setEditable(true);
        clearForms();
        setFormEditable(true);
        removeError();       
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        btnAdd.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        removeError();
        txtSemester.setEditable(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
        setFormEditable(false);
        clearForms();
        removeError();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        //Check for empty Fields
        removeError();      
        if(btnAdd.isEnabled()){
            if(isFieldValidated()) {
                addSemester();
                refreshTable();
            }
            else {
            JOptionPane.showMessageDialog(this, "Please enter a Valid Semester Between (1-3).", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        if(btnDelete.isEnabled()){
            if(!isFieldValidated()){
                JOptionPane.showMessageDialog(null, "Please enter a Valid Semester Between (1-3).", "Error", JOptionPane.ERROR_MESSAGE);
                lblInvalidSemester.setVisible(true);
                return;
            }
            deleteSemester();
            refreshTable();
        }
        
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemesterActionPerformed
    public void removeError(){
        lblInvalidSemester.setVisible(false);
    }
    public void addSemester() {
        try{
            int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to add? ", "confirm",JOptionPane.YES_NO_OPTION);
            if(intAnswer == 0){
                ps = conn.prepareStatement("INSERT INTO oop23.semester VALUES (?)");
                ps.setString(1, txtSemester.getText());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Addition is successful");
                resetButtons();
                removeError();
                setFormEditable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Addition is aborted");
            }
        }   
        catch(Exception e) {
                f.displayError(e);
            }
    }

    public void deleteSemester() {
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("DELETE FROM oop23.semester"
                        + " WHERE semester = '"
                        +txtSemester.getText()
                        + "'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deletion is successful");
                resetButtons();
                clearForms();
                removeError();
                setFormEditable(false);
            }
            catch(Exception e) {
                    f.displayError(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Deletion is aborted");
            }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Semester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Semester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Semester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Semester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Semester().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInvalidSemester;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtSemester;
    // End of variables declaration//GEN-END:variables
}
