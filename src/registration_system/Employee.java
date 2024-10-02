/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registration_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ALMENDRA
 */
public class Employee extends javax.swing.JFrame {
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String strDashLine = "--------------------";
    Functions f = new Functions();
    
    public boolean isFieldValidated(){
        boolean blFlag = true, blIsEmpty = true;
        
        if(f.isEmpty(txtEmployeeId.getText())){
            lblInvEmpNo.setVisible(true);
            blIsEmpty = false;
            
        }
        else if(!f.isValidEId(txtEmployeeId.getText())){
            lblInvEmpNo.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid Employee ID (should be E000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtFirstName.getText())){
            lblInvFname.setVisible(true);
            blIsEmpty = false;
        }
        else if(!f.isValidName(txtFirstName.getText())){
            lblInvFname.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid first name (letters and spaces only, up to 50 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtLastName.getText()) ){
            lblInvLName.setVisible(true);
            blIsEmpty = false;
        }
        else if( !f.isValidName(txtLastName.getText()) ){
            lblInvLName.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid last name (letters and spaces only, up to 50 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(f.isEmpty(txtEmail.getText()) ){
            lblInvEmail.setVisible(true);
            blIsEmpty = false;
        }
        else if(!f.isValidEmail(txtEmail.getText()) ){
            lblInvEmail.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid email.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtAddress.getText()) ){
            lblInvAddress.setVisible(true);
            blIsEmpty = false;
        }
        else if(!f.isValidAddress(txtAddress.getText()) ){
            lblInvAddress.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid address (letters and spaces only, up to 255 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtContact.getText()) ){
            lblInvContact.setVisible(true);
            blIsEmpty = false;
        }
        else if(!f.isValidContactNumber(txtContact.getText()) ){
            lblInvContact.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid contact number (0900000000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(cmbGender.getSelectedItem().equals(strDashLine)){
            lblInvGender.setVisible(true);
            blIsEmpty = false;
        }
        
        
        try{
            new SimpleDateFormat("yyyy-MM-dd").format(dtcBirthday.getDate());
        } catch(Exception e){
            lblInvBday.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //f.isEmpty();
        if(!blIsEmpty){
            JOptionPane.showMessageDialog(null, "Fields shouldn't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        return blFlag;
    }    
    
    public void setFormEditable(boolean blFlag){
        txtEmployeeId.setEditable(blFlag);
        txtFirstName.setEditable(blFlag);
        txtLastName.setEditable(blFlag);
        txtEmail.setEditable(blFlag);
        txtAddress.setEditable(blFlag);
        cmbGender.setEnabled(blFlag);
        cmbStatus.setEnabled(blFlag);
        dtcBirthday.setEnabled(blFlag);
        txtContact.setEditable(blFlag);
    }
    public void removeError(){
        lblInvEmpNo.setVisible(false);
        lblInvLName.setVisible(false);
        lblInvFname.setVisible(false);
        lblInvEmail.setVisible(false);
        lblInvAddress.setVisible(false);
        lblInvBday.setVisible(false);
        lblInvContact.setVisible(false);
        lblInvGender.setVisible(false);
        lblInvStatus.setVisible(false);
        
    }
    public void displayError(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    
    public void resetButtons(){
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
    }
    
    public void setStatus(String status){
        cmbStatus.removeAllItems();            
        System.out.println(status);
        if(status.equals("A")){
            cmbStatus.addItem("Active");
            cmbStatus.addItem("Inactive");
        } else if(status.equals("I")){
            cmbStatus.addItem("Inactive");
            cmbStatus.addItem("Active");
        } 
    }
    
    public void setGender(String gender){
        cmbGender.removeAllItems();
        if(gender.equals("M")){
            cmbGender.addItem("Male");
            cmbGender.addItem("Female");

        } 
        else if(gender.equals("F")) {
            cmbGender.addItem("Female");
            cmbGender.addItem("Male");
        }
        else{
            cmbGender.addItem(strDashLine);
            cmbGender.addItem("Male");
            cmbGender.addItem("Female");
        }
    }
    public void clearForm(){
        txtEmployeeId.setText(null);
        txtFirstName.setText(null);
        txtLastName.setText(null);
        txtEmail.setText(null);
        txtAddress.setText(null);
        setStatus("A");
        setGender("dash");
        dtcBirthday.setDate(null);
        txtContact.setText(null);
        
    }
    
    public void refreshTable(){
        try {
            ps = conn.prepareStatement("SELECT * FROM oop23.vwEmployee");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e) {
            displayError(e);
        }
    }
    
    /**
     * Creates new form Employee
     */
    public Employee() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblForms = new javax.swing.JTable();
        pnlStudent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblInvEmpNo = new javax.swing.JLabel();
        txtEmployeeId = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblInvEmail = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<>();
        dtcBirthday = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblInvFname = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblInvLName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblInvContact = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        lblInvStatus = new javax.swing.JLabel();
        lblInvGender = new javax.swing.JLabel();
        lblInvBday = new javax.swing.JLabel();
        lblInvAddress = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        lblTitle.setText("View Employee Information");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Employee Id (required)");

        lblInvEmpNo.setForeground(new java.awt.Color(255, 51, 51));
        lblInvEmpNo.setText("* invalid format, should be E000");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Status");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("First Name");

        txtFirstName.setEditable(false);

        txtLastName.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Email");

        txtEmail.setEditable(false);

        lblInvEmail.setForeground(new java.awt.Color(255, 51, 51));
        lblInvEmail.setText("* invalid email format");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Address");

        txtAddress.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Gender");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Birthday");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Contact Number");

        txtContact.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Last name");

        lblInvFname.setForeground(new java.awt.Color(255, 51, 51));
        lblInvFname.setText("* invalid name");

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

        lblInvLName.setForeground(new java.awt.Color(255, 51, 51));
        lblInvLName.setText("* invalid name");

        lblInvContact.setForeground(new java.awt.Color(255, 51, 51));
        lblInvContact.setText("*invalid format, should be 0900000000");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblInvStatus.setForeground(new java.awt.Color(255, 51, 51));
        lblInvStatus.setText("*");

        lblInvGender.setForeground(new java.awt.Color(255, 51, 51));
        lblInvGender.setText("*");

        lblInvBday.setForeground(new java.awt.Color(255, 51, 51));
        lblInvBday.setText("*");

        lblInvAddress.setForeground(new java.awt.Color(255, 51, 51));
        lblInvAddress.setText("*");

        javax.swing.GroupLayout pnlStudentLayout = new javax.swing.GroupLayout(pnlStudent);
        pnlStudent.setLayout(pnlStudentLayout);
        pnlStudentLayout.setHorizontalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentLayout.createSequentialGroup()
                        .addComponent(txtEmployeeId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInvEmpNo))
                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlStudentLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblInvGender))
                                        .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentLayout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblInvBday)
                                            .addGap(83, 83, 83))
                                        .addComponent(dtcBirthday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblInvAddress))
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblInvContact))
                                .addComponent(txtContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlStudentLayout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblInvFname)))
                                    .addGap(29, 29, 29)
                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlStudentLayout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblInvLName))
                                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblInvEmail)))
                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblInvStatus))
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnConfirm)))
                        .addGap(25, 25, 25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlStudentLayout.setVerticalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel15))
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInvEmpNo)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmployeeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(lblInvFname)
                            .addComponent(lblInvLName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblInvEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblInvAddress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(lblInvGender)
                            .addComponent(lblInvBday))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtcBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblInvContact))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblInvStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnConfirm))
                .addContainerGap(48, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void searchTable(){
        String strEmployeeId = "";
        strEmployeeId = txtEmployeeId.getText();
        resetButtons();
        try{
            ps = conn.prepareStatement("Select * FROM oop23.employee WHERE employee_id = '" + strEmployeeId + "';");
            rs = ps.executeQuery();
            if(rs.next()){
                txtLastName.setText(rs.getString("lastname"));
                txtFirstName.setText(rs.getString("firstname"));
                txtEmail.setText(rs.getString("email"));
                setGender(rs.getString(("gender")));
                txtContact.setText(rs.getString("cp_num"));
                txtAddress.setText(rs.getString("address"));
                dtcBirthday.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("bday")));
                setStatus(rs.getString("status"));
            }
        }
        catch(Exception e) {
            displayError(e);
        }
    }
    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        int intRow = tblForms.getSelectedRow();
        String strEmployeeId = "";
        strEmployeeId = tblForms.getValueAt(intRow, 0).toString();
        setFormEditable(false);
        resetButtons();
        try{
                ps = conn.prepareStatement("Select * FROM oop23.employee WHERE employee_id = '" + strEmployeeId + "';");
                rs = ps.executeQuery();
                if(rs.next()){
                    txtEmployeeId.setText(rs.getString("Employee_id"));
                    txtLastName.setText(rs.getString("lastname"));
                    txtFirstName.setText(rs.getString("firstname"));
                    txtEmail.setText(rs.getString("email"));
                    setGender(rs.getString(("gender")));
                    txtContact.setText(rs.getString("cp_num"));
                    txtAddress.setText(rs.getString("address"));
                    dtcBirthday.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("bday")));
                    setStatus(rs.getString("status"));
                }
            }
            catch(Exception e) {
                displayError(e);
            }
    }//GEN-LAST:event_tblFormsMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        txtEmployeeId.setEditable(true);
        clearForm();
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
        txtEmployeeId.setEditable(true);
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
            addEmployee();
            return;
        }
        if(btnEdit.isEnabled() && isFieldValidated() ){
            editEmployee();
            return;
        }
        if(btnDelete.isEnabled()){
            if(f.isEmpty(txtEmployeeId.getText()) || !f.isValidEId(txtEmployeeId.getText())){
                JOptionPane.showMessageDialog(null, "Please enter a valid Employee Id (E000).", "Error", JOptionPane.ERROR_MESSAGE);
                lblInvEmpNo.setVisible(true);
                return;
            }
            deleteEmployee();
            return;
        }    
        
    }//GEN-LAST:event_btnConfirmActionPerformed
    public void addEmployee(){
        String strGender = "", strStatus = "";
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to add? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("INSERT INTO oop23.employee VALUES ('"
                + txtEmployeeId.getText()
                + "', '" + txtLastName.getText()        
                + "', '" + txtFirstName.getText()
                + "', '" + txtEmail.getText()
                + "', '" + f.toGender(cmbGender.getSelectedItem().toString())
                + "', '" + txtContact.getText()
                + "', '" + txtAddress.getText()
                + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(dtcBirthday.getDate())
                + "', '" + f.toStatus(cmbStatus.getSelectedItem().toString())
                + "')"        
                );
                ps.execute();
                JOptionPane.showMessageDialog(null, "Addition is successful.");
                resetButtons();
                removeError();
                refreshTable();
            } catch (Exception e){
                f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Addition is aborted");
        }
    }
    
    public void editEmployee(){
        String strGender = "", strStatus = "";
        strGender = f.toGender(cmbGender.getSelectedItem().toString() );
        strStatus = f.toStatus(cmbStatus.getSelectedItem().toString() );
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to edit? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("UPDATE oop23.employee SET"
                        +" lastname = '"+txtLastName.getText()
                        +"', firstname = '"+txtFirstName.getText()
                        +"', email = '"+txtEmail.getText()
                        +"', gender = '"+strGender
                        +"', cp_num = '"+txtContact.getText()
                        +"', address = '"+txtAddress.getText()
                        +"', bday = '"+new SimpleDateFormat("yyyy-MM-dd").format(dtcBirthday.getDate())
                        +"', status = '"+strStatus
                        +"' WHERE "
                        +"employee_id = '"
                        +txtEmployeeId.getText()
                        +"'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Edit is successful");
                resetButtons();
                removeError();
                refreshTable();
            } catch (Exception e){
                f.displayError(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Edit is aborted");
        }
    }
    
    public void deleteEmployee(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                try{
                ps = conn.prepareStatement("DELETE FROM oop23.employee"
                        + " WHERE employee_id = '"
                        +txtEmployeeId.getText()
                        + "'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deletion is successful");
                resetButtons();
                clearForm();
                removeError();
                refreshTable();
            }
            catch(Exception e) {
                    displayError(e);
                }
            } catch (Exception e){
                f.displayError(e);
            }
        } else {
                JOptionPane.showMessageDialog(null, "Deletion is aborted");
            }
    }
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        removeError();
        refreshTable();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
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
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser dtcBirthday;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInvAddress;
    private javax.swing.JLabel lblInvBday;
    private javax.swing.JLabel lblInvContact;
    private javax.swing.JLabel lblInvEmail;
    private javax.swing.JLabel lblInvEmpNo;
    private javax.swing.JLabel lblInvFname;
    private javax.swing.JLabel lblInvGender;
    private javax.swing.JLabel lblInvLName;
    private javax.swing.JLabel lblInvStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmployeeId;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
