/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registration_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ALMENDRA
 */

public class Grade extends javax.swing.JFrame {
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String strDashLine = "--------------------";
    Functions f = new Functions();
    
    
    public boolean isFieldValidated(){
        boolean blIsEmpty = true, blFlag = true;
        //SY
        if(f.isEmpty(txtSY.getText())){
            blIsEmpty = false;
            lvlInvSY.setVisible(true);
        } else if(!f.isValidSY(txtSY.getText())){
            blFlag = false;
            lvlInvSY.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid School Year(should be 0000-0000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Semester
        if(f.isEmpty(txtSemester.getText())){
            blIsEmpty = false;
            lblInvSem.setVisible(true);
        } else if(!f.isValidSem(txtSemester.getText())){
            blFlag = false;
            lblInvSem.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid Semester(should be 1 or 2).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Student number
        if(f.isEmpty(txtStudentNo.getText())){
            blIsEmpty = false;
            lblInvSt.setVisible(true);
        } else if(!f.isValidStudentNo(txtStudentNo.getText())){
            blFlag = false;
            lblInvSt.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid Student No(should be 0000-00000.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //subject_code
        if(cmbSubjectCode.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvSbj.setVisible(true);
        }
        //Block
        if(f.isEmpty(txtBlock.getText())){
            blIsEmpty = false;
            lblInvBlock.setVisible(true);
        } else if(!f.isValidBlock(txtBlock.getText())){
            blFlag = false;
            lblInvBlock.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid Block code (e.g. CS12). ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //grade
        if(f.isEmpty(txtGrade.getText())){
            blIsEmpty = false;
            lblInvGrade.setVisible(true);
        } else if(!f.isValidGrade(txtGrade.getText())){
            blFlag = false;
            lblInvGrade.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid grade (should be 0.00). ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(!blIsEmpty){
            JOptionPane.showMessageDialog(null, "Fields shouldn't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        
        return blFlag;
    }
    
    public void removeError(){
        lvlInvSY.setVisible(false);
        lblInvSem.setVisible(false);
        lblInvSt.setVisible(false);
        lblInvSbj.setVisible(false);
        lblInvBlock.setVisible(false);
        lblInvGrade.setVisible(false);
    }
    
    public void searchTable(){
        try{
            ps = conn.prepareStatement("Select * from oop23.grade WHERE student_no ='" + txtSearchStudent.getText() +"';");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    
    public void setCmbSubject(String strSbj){
        cmbSubjectCode.removeAllItems();
        cmbSubjectCode.addItem(strDashLine);
        try{
            ps = conn.prepareStatement("Select * from oop23.subject;");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbSubjectCode.addItem(rs.getString("subject_code"));
            }
            cmbSubjectCode.setSelectedItem(strSbj);
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    public void setFieldsEditable(boolean blFlag){
        txtSY.setEditable(blFlag);
        txtSemester.setEditable(blFlag);
        txtStudentNo.setEditable(blFlag);
        cmbSubjectCode.setEnabled(blFlag);
        txtBlock.setEditable(blFlag);
        txtGrade.setEditable(blFlag);
    }
    
    public void refreshTable(){
        try{
            ps = conn.prepareStatement("Select * from oop23.grade");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    public void clearForms(){
        txtSearchStudent.setText(null);
        txtSY.setText(null);
        txtSemester.setText(null);
        txtStudentNo.setText(null);
        txtBlock.setText(null);
        txtGrade.setText(null);
        setCmbSubject(strDashLine);
    }
    /**
     * Creates new form Grade
     */
    public Grade() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSY = new javax.swing.JTextField();
        txtSemester = new javax.swing.JTextField();
        txtStudentNo = new javax.swing.JTextField();
        txtSearchStudent = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBlock = new javax.swing.JTextField();
        txtGrade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cmbSubjectCode = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lvlInvSY = new javax.swing.JLabel();
        lblInvSem = new javax.swing.JLabel();
        lblInvSt = new javax.swing.JLabel();
        lblInvSbj = new javax.swing.JLabel();
        lblInvBlock = new javax.swing.JLabel();
        lblInvGrade = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForms = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("View Grades");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("School Year");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Semester");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Student no");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Subject Code");

        txtSY.setText("jTextField1");

        txtSemester.setText("jTextField2");

        txtStudentNo.setText("jTextField3");

        txtSearchStudent.setText("jTextField4");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Student no");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Block no");

        txtBlock.setText("jTextField6");

        txtGrade.setText("jTextField7");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Grade");

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cmbSubjectCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lvlInvSY.setForeground(new java.awt.Color(255, 51, 51));
        lvlInvSY.setText("*");

        lblInvSem.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSem.setText("*");

        lblInvSt.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSt.setText("*");

        lblInvSbj.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSbj.setText("*");

        lblInvBlock.setForeground(new java.awt.Color(255, 51, 51));
        lblInvBlock.setText("*");

        lblInvGrade.setForeground(new java.awt.Color(255, 51, 51));
        lblInvGrade.setText("*");

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSY, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                    .addComponent(txtSemester)
                                    .addComponent(txtStudentNo)
                                    .addComponent(txtBlock)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lvlInvSY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvSem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvSt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvSbj, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblInvGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(btnAdd)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnEdit)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDelete)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnCancel)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnConfirm)))
                            .addComponent(cmbSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnReset)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnSearch))
                                .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lvlInvSY))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblInvSem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblInvSt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblInvSbj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblInvBlock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblInvGrade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(btnCancel)
                    .addComponent(btnConfirm))
                .addContainerGap(22, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 51, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        txtStudentNo.setEditable(true);
        clearForms();
        removeError();
        setFieldsEditable(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        setFieldsEditable(false);
        clearForms();
        removeError();
        resetButtons();
        
        refreshTable();
        
    }//GEN-LAST:event_formWindowOpened

    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        int intRow = tblForms.getSelectedRow();
        String strSY ="", strSemester="", strStudentNo="", strSubjectCode="", strBlock="", strGrade ="";
        //reset field and buttons
        //removeError();
        setFieldsEditable(false);
        resetButtons();
        
        //get values for txtfields
        strSY = tblForms.getValueAt(intRow, 0).toString();
        strSemester = tblForms.getValueAt(intRow, 1).toString();
        strStudentNo = tblForms.getValueAt(intRow, 2).toString();
        strSubjectCode = tblForms.getValueAt(intRow, 3).toString();
        strBlock = tblForms.getValueAt(intRow, 4).toString(); 
        strGrade = tblForms.getValueAt(intRow, 5).toString();
        
        txtSY.setText(strSY);
        txtSemester.setText(strSemester);
        txtStudentNo.setText(strStudentNo);
        setCmbSubject(strSubjectCode);
        txtBlock.setText(strBlock);
        txtGrade.setText(strGrade);
    }//GEN-LAST:event_tblFormsMouseClicked
    public void resetButtons(){
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
    }
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        resetButtons();
        removeError();
        setFieldsEditable(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        removeError();
        txtBlock.setEditable(true);
        txtGrade.setEditable(true);
        txtSearchStudent.setText(txtStudentNo.getText());
        searchTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        removeError();      
        if(btnAdd.isEnabled() && isFieldValidated()){
            addGrade();
            refreshTable();
            return;
        }

        if(btnEdit.isEnabled() && isFieldValidated()){
            editGrade();
            return;
        }
        if(btnDelete.isEnabled()){
            if(f.isEmpty(txtStudentNo.getText()) || !f.isValidStudentNo(txtStudentNo.getText())){
                JOptionPane.showMessageDialog(null, "Please enter a valid student number (0000-00000).", "Error", JOptionPane.ERROR_MESSAGE);
                lblInvSt.setVisible(true);
                return;
            }
            deleteGrade();
            return;
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnAdd.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        removeError();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        clearForms();
        refreshTable();
    }//GEN-LAST:event_btnResetActionPerformed
    public void addGrade(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to Add? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("Insert into oop23.grade values ('"
                        + txtSY.getText() 
                        + "', '" + txtSemester.getText()
                        + "', '" + txtStudentNo.getText()
                        + "', '" + cmbSubjectCode.getSelectedItem().toString()
                        + "', '" + txtBlock.getText()
                        + "', '" + txtGrade.getText()
                        + "');"
                        
                ); 
                
                ps.execute();
                JOptionPane.showMessageDialog(null, "Addition is successful");
                refreshTable();
                resetButtons();
                setFieldsEditable(false);
            }
            catch(Exception e){
                f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Addition is aborted");
        }
    }
    
    public void editGrade(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to Edit? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("UPDATE oop23.grade set block_no ='" 
                        + txtBlock.getText() 
                        + "', grade =" 
                        + txtGrade.getText() 
                        + " WHERE sy ='" + txtSY.getText()
                        + "' and semester ='" + txtSemester.getText()
                        + "' and subject_code ='" + cmbSubjectCode.getSelectedItem().toString()
                        + "';"
                );
                ps.execute();
                JOptionPane.showMessageDialog(null, "Edit is successful");
                searchTable();
                resetButtons();
                setFieldsEditable(false);
            }
            catch(Exception e){
                f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Edit is aborted");
        }
    }
    
    public void deleteGrade(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to Delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("Delete from oop23.grade"
                        + " WHERE sy ='" + txtSY.getText()
                        + "' and semester ='" + txtSemester.getText()
                        + "' and student_no ='" + txtStudentNo.getText()
                        + "' and subject_code ='" + cmbSubjectCode.getSelectedItem().toString()
                        + "';"
                );
                ps.execute();
                JOptionPane.showMessageDialog(null, "Delition is successful");
                refreshTable();
                resetButtons();
                setFieldsEditable(false);
            }
            catch(Exception e){
                f.displayError(e);
            }
        }
        else {
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
            java.util.logging.Logger.getLogger(Grade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Grade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbSubjectCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInvBlock;
    private javax.swing.JLabel lblInvGrade;
    private javax.swing.JLabel lblInvSbj;
    private javax.swing.JLabel lblInvSem;
    private javax.swing.JLabel lblInvSt;
    private javax.swing.JLabel lvlInvSY;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtBlock;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtSY;
    private javax.swing.JTextField txtSearchStudent;
    private javax.swing.JTextField txtSemester;
    private javax.swing.JTextField txtStudentNo;
    // End of variables declaration//GEN-END:variables
}
