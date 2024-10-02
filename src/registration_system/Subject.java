/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registration_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;

public class Subject extends javax.swing.JFrame {
    
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Functions f = new Functions();
    String strDashLine = "--------------------";
    
    public boolean isFieldValidated(){
        boolean blIsEmpty = true, blFlag = true;
        //subject code
        if(f.isEmpty(txtSubjectCode.getText())){
            blIsEmpty = false;
            lblInvSubjectCode.setVisible(true);
        } else if (!f.isValidCollegeCode(txtSubjectCode.getText())) {
            lblInvSubjectCode.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid Subject Code (letters and spaces only, up to 10 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //description
        if(f.isEmpty(txtDescription.getText())){
            blIsEmpty = false;
            lblInvDescription.setVisible(true);
        } else if (!f.isValidCollegeCode(txtDescription.getText())) {
            lblInvDescription.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid Course Description (letters and spaces only, up to 100 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Units
        if(f.isEmpty(txtUnits.getText())){
            lblInvUnits.setVisible(true);
            blIsEmpty = false;
        } else if (!f.isValidUnits(txtUnits.getText())) {
            lblInvUnits.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid Unit (1 Digit only).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Curriculum
        if(f.isEmpty(txtCurriculum.getText())){
            lblInvCurriculum.setVisible(true);
            blIsEmpty = false;
        } else if (!f.isValidCurriculum(txtCurriculum.getText())) {
            blFlag = false;
            lblInvCurriculum.setVisible(true);
            JOptionPane.showMessageDialog(null, "Please enter a valid Curriculum (year format 0000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //college code
        if(cmbCollege.getSelectedItem().toString().equals(strDashLine)){
            lblInvCollegeCode.setVisible(true);
            blIsEmpty = false;
        }
        if(!blIsEmpty){
            JOptionPane.showMessageDialog(null, "Fields shouldn't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        return blFlag;
    }
    public void setFormEditable(boolean blFlag){
        
        txtDescription.setEditable(blFlag);
        txtUnits.setEditable(blFlag);
        txtCurriculum.setEditable(blFlag);
        cmbCollege.setEnabled(blFlag);
        cmbStatus.setEnabled(blFlag);
        
    }
    
    public void clearForm(){
        txtSubjectCode.setText(null);
        txtDescription.setText(null);
        txtUnits.setText(null);
        txtCurriculum.setText(null);
    }
    public void removeError(){
        lblInvSubjectCode.setVisible(false);
        lblInvDescription.setVisible(false);
        lblInvUnits.setVisible(false);
        lblInvCurriculum.setVisible(false);
        lblInvCollegeCode.setVisible(false);
        lblInvStatus.setVisible(false);
    }
    
    public void setStatus(String status){
        cmbStatus.removeAllItems();
        cmbStatus.addItem("Active");
        cmbStatus.addItem("Inactive");
        if(status.equals("A")){
            cmbStatus.setSelectedItem("Active");
        } else if(status.equals("I")){
            cmbStatus.setSelectedItem("Inactive");
        }
    }
    public void resetButtons(){
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
    }
    public void setCollegeCode(String strCollegeCode){
        cmbCollege.removeAllItems();
        try {
            ps = conn.prepareStatement("Select college_code from oop23.college");
            rs = ps.executeQuery();
            cmbCollege.addItem(strDashLine);
            while(rs.next()) {
                cmbCollege.addItem(rs.getString("college_code"));
            }
            cmbCollege.setSelectedItem(strCollegeCode);
        } 
        catch(Exception e){
            f.displayError(e);
        }
    }
    
    public Subject() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSubjectCode = new javax.swing.JTextField();
        cmbStatus = new javax.swing.JComboBox<>();
        txtDescription = new javax.swing.JTextField();
        txtUnits = new javax.swing.JTextField();
        txtCurriculum = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cmbCollege = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblInvSubjectCode = new javax.swing.JLabel();
        lblInvDescription = new javax.swing.JLabel();
        lblInvUnits = new javax.swing.JLabel();
        lblInvCurriculum = new javax.swing.JLabel();
        lblInvCollegeCode = new javax.swing.JLabel();
        lblInvStatus = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForms = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Subject Code (required)");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Description");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Status");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Units");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Curriculum");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("College code");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("View Subject Information");

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
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

        lblInvSubjectCode.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSubjectCode.setText("*");

        lblInvDescription.setForeground(new java.awt.Color(255, 51, 51));
        lblInvDescription.setText("*");

        lblInvUnits.setForeground(new java.awt.Color(255, 51, 51));
        lblInvUnits.setText("*");

        lblInvCurriculum.setForeground(new java.awt.Color(255, 51, 51));
        lblInvCurriculum.setText("*");

        lblInvCollegeCode.setForeground(new java.awt.Color(255, 51, 51));
        lblInvCollegeCode.setText("*");

        lblInvStatus.setForeground(new java.awt.Color(255, 51, 51));
        lblInvStatus.setText("*");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConfirm))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescription)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtSubjectCode)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblInvSubjectCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblInvDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(lblInvUnits)
                    .addComponent(lblInvCurriculum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblInvCollegeCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblInvStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnConfirm))
                .addGap(66, 66, 66))
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

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        int intRow = tblForms.getSelectedRow();
        String strSubjectCode = "", strCollegeCode = "";
        strSubjectCode = tblForms.getValueAt(intRow, 0).toString();
        resetButtons();
        setFormEditable(false);
        try{
            ps = conn.prepareStatement("Select * FROM oop23.subject WHERE subject_code = '" + strSubjectCode + "';");
            rs = ps.executeQuery();
            if(rs.next()){
                txtSubjectCode.setText(rs.getString("subject_code"));
                txtDescription.setText(rs.getString("description"));
                txtUnits.setText(rs.getString("units"));
                txtCurriculum.setText(rs.getString("curriculum"));
                strCollegeCode = rs.getString("college_code");
                setStatus(rs.getString("status"));
                //new rs
                setCollegeCode(strCollegeCode);
            }
        }
        catch(Exception e) {
            f.displayError(e);
        }
    }//GEN-LAST:event_tblFormsMouseClicked
    public void refreshTable(){
        try {
            ps = conn.prepareStatement("SELECT * FROM oop23.subject");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e) {
            f.displayError(e);
        }
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        clearForm();
        setStatus("A");
        setCollegeCode(strDashLine);
        setFormEditable(true);
        removeError();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        removeError();
        setFormEditable(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnAdd.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        txtSubjectCode.setEditable(true);
        removeError();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
        setFormEditable(false);
        removeError();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        removeError();
        if(btnAdd.isEnabled() && isFieldValidated() ){
            addSubject();
            return;
        }
        if(btnEdit.isEnabled() && isFieldValidated() ){
            editSubject();
            return;
        }
        if(btnDelete.isEnabled()){
            if(f.isEmpty(txtSubjectCode.getText()) || !f.isValidCollegeCode(txtSubjectCode.getText())){
                JOptionPane.showMessageDialog(null, "Please enter a valid Subject Code.", "Error", JOptionPane.ERROR_MESSAGE);
                lblInvSubjectCode.setVisible(true);
                return;
            }
            deleteSubject();
            return;
        }

    }//GEN-LAST:event_btnConfirmActionPerformed
    public void addSubject(){
        String strStatus = f.toStatus(cmbStatus.getSelectedItem().toString());
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to add? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("INSERT INTO oop23.subject VALUES ('"
                    + txtSubjectCode.getText()
                    +"', '"+ txtDescription.getText()
                    +"', '"+ Integer.parseInt(txtUnits.getText())
                    +"', '"+ txtCurriculum.getText()
                    +"', '"+ cmbCollege.getSelectedItem().toString()
                    +"', '"+ strStatus
                    +"')");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Addition is successful");
                refreshTable();
                resetButtons();
                setFormEditable(false);
            }catch(Exception e) {
                f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Addition is aborted");
        }
    }
    
    public void editSubject(){
        String strStatus = f.toStatus(cmbStatus.getSelectedItem().toString());
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to edit? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("UPDATE oop23.subject SET description ='"
                    + txtDescription.getText()
                    +"', units ='"+ Integer.parseInt(txtUnits.getText())
                    +"', curriculum ='"+ txtCurriculum.getText()
                    +"', college_code = '"+ cmbCollege.getSelectedItem().toString()
                    +"', status = '"+ strStatus
                    +"' WHERE subject_code = '"
                    + txtSubjectCode.getText()
                    + "';"
                );
                ps.execute();
                JOptionPane.showMessageDialog(null, "Edit is successful");
                refreshTable();
                resetButtons();
                setFormEditable(false);
            }catch(Exception e) {
                f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Edit is aborted");
        }
    }
    
    public void deleteSubject(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("DELETE FROM oop23.subject WHERE subject_code ='"
                + txtSubjectCode.getText()
                +"';");
                
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deletion is successful");
                refreshTable();
                resetButtons();
                setFormEditable(false);
                clearForm();
            }catch(Exception e) {
                f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Deletion is aborted");
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        refreshTable();
        removeError();
        setFormEditable(false);
        clearForm();
        setStatus("A");
        try {
            ps = conn.prepareStatement("Select college_code from oop23.college");
            rs = ps.executeQuery();
            while(rs.next()) {
                cmbCollege.addItem(rs.getString("college_code").toString());
            }
        } 
        catch(Exception e){
            f.displayError(e);
        }
    }//GEN-LAST:event_formWindowOpened
    public void searchTable(String strSubject){
        String strCollegeCode = "";
        try{
            ps = conn.prepareStatement("Select * FROM oop23.subject WHERE subject_code = '" + strSubject + "';");
            rs = ps.executeQuery();
            if(rs.next()){
                txtSubjectCode.setText(rs.getString("subject_code"));
                txtDescription.setText(rs.getString("description"));
                txtUnits.setText(rs.getString("units"));
                txtCurriculum.setText(rs.getString("curriculum"));
                strCollegeCode = rs.getString("college_code");
                setStatus(rs.getString("status"));
                //new rs
                setCollegeCode(strCollegeCode);
            }
        }
        catch(Exception e) {
            f.displayError(e);
        }
    }
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchTable(txtSubjectCode.getText());
        resetButtons();
        removeError();
        setFormEditable(false);
    }//GEN-LAST:event_btnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Subject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbCollege;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInvCollegeCode;
    private javax.swing.JLabel lblInvCurriculum;
    private javax.swing.JLabel lblInvDescription;
    private javax.swing.JLabel lblInvStatus;
    private javax.swing.JLabel lblInvSubjectCode;
    private javax.swing.JLabel lblInvUnits;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtCurriculum;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtSubjectCode;
    private javax.swing.JTextField txtUnits;
    // End of variables declaration//GEN-END:variables
}
