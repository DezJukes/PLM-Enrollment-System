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
public class SubjectSchedule extends javax.swing.JFrame {
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Functions f = new Functions();
    String strDashLine = "--------------------";
    /**
     * Creates new form SubjectSchedule
     */
    public boolean isFieldValidated(){
        boolean blFlag = true, blIsEmpty = true;
        //SY
        if(cmbSY.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvSY.setVisible(true);
        }
        //Semester
        if(cmbSemester.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvSemester.setVisible(true);
        }
        //College Code
        if(cmbCollegeCode.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvCollegeCode.setVisible(true);
        }
        //Block number
        if(cmbBlock.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvBlock.setVisible(true);
        }
        //Subject Code
        if(cmbSubjectCode.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvSubject.setVisible(true);
        }
        //Day
        if(cmbDay.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvDay.setVisible(true);
        }
        //Time
        if(f.isEmpty(txtTime.getText())){
            blIsEmpty = false;
            lblInvTime.setVisible(true);
        } else if(!f.isValidTime(txtTime.getText())){
            blFlag = false;
            lblInvTime.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid time format(should be 0:00-0:00).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Room
        if(f.isEmpty(txtRoom.getText())){
            blIsEmpty = false;
            lblInvRoom.setVisible(true);
        }
        //type
        if(cmbType.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvType.setVisible(true);
        }
        
        //Sequence
        if(f.isEmpty(txtSequence.getText())){
            blIsEmpty = false;
            lblInvSqn.setVisible(true);
        }
        else if(!f.isValidSequenceNo(txtSequence.getText())){
            blFlag = false;
            lblInvSqn.setVisible(true);
            JOptionPane.showMessageDialog(null, "Invalid sequence number (should be 00).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Faculty id
        if(f.isEmpty(txtFacultyId.getText())){
            lblInvFacultyId.setVisible(true);
            blIsEmpty = false;
            
        }
        else if(!f.isValidEId(txtFacultyId.getText())){
            lblInvFacultyId.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid Employee ID (should be E000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        if(!blIsEmpty){
            JOptionPane.showMessageDialog(null, "Fields shouldn't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        
        return blFlag;
    }
    
    public void clearForms(){
        setCmbSY(strDashLine);
        setCmbSemester(strDashLine);
        setCmbCollegeCode(strDashLine);
        setCmbBlock(strDashLine);
        setCmbSubjectCode(strDashLine);
        setCmbDay(strDashLine);
        txtTime.setText(null);
        txtRoom.setText(null);
        setCmbType(strDashLine);
        txtSequence.setText(null);
        txtFacultyId.setText(null);
    }
    
    public void resetButtons(){
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
    }
    public void removeError(){
        lblInvSY.setVisible(false);
        lblInvSemester.setVisible(false);
        lblInvCollegeCode.setVisible(false);
        lblInvBlock.setVisible(false);
        lblInvSubject.setVisible(false);
        lblInvDay.setVisible(false);
        lblInvTime.setVisible(false);
        lblInvRoom.setVisible(false);
        lblInvType.setVisible(false);
        lblInvSqn.setVisible(false);
        lblInvFacultyId.setVisible(false); 
        
    }
    public void setCmbSY(String strSY){
        cmbSY.removeAllItems();
        cmbSY.addItem(strDashLine);
        try{
            ps = conn.prepareStatement("Select * FROM oop23.sy");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbSY.addItem(rs.getString("sy"));
            }
            cmbSY.setSelectedItem(strSY);
        }
        catch(Exception e){
            f.displayError(e);
        }
        
    }
    
    public void setCmbSemester(String strSemester){
        cmbSemester.removeAllItems();
        cmbSemester.addItem(strDashLine);
        try{
            ps = conn.prepareStatement("Select * FROM oop23.semester");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbSemester.addItem(rs.getString("semester"));
            }
            cmbSemester.setSelectedItem(strSemester);
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    
    public void setCmbCollegeCode(String strCollegeCode){
        cmbCollegeCode.removeAllItems();
        cmbCollegeCode.addItem(strDashLine);
        try{
            ps = conn.prepareStatement("Select * FROM oop23.college WHERE status ='A'");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbCollegeCode.addItem(rs.getString("college_code"));
            }
            cmbCollegeCode.setSelectedItem(strCollegeCode);
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    
    public void setCmbBlock(String strBlock){
        cmbBlock.removeAllItems();
        cmbBlock.addItem(strDashLine);
        try{
            ps = conn.prepareStatement("Select * FROM oop23.block");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbBlock.addItem(rs.getString("block_no"));
            }
            cmbBlock.setSelectedItem(strBlock);
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    
    public void setCmbSubjectCode(String strSubjectCode){
        cmbSubjectCode.removeAllItems();
        cmbSubjectCode.addItem(strDashLine);
        try{
            ps = conn.prepareStatement("Select * FROM oop23.subject");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbSubjectCode.addItem(rs.getString("subject_code"));
            }
            cmbSubjectCode.setSelectedItem(strSubjectCode);
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    
    public void setCmbDay(String strDay){
        cmbDay.removeAllItems();
        cmbDay.addItem(strDashLine);
        cmbDay.addItem("Sunday");
        cmbDay.addItem("Monday");
        cmbDay.addItem("Tuesday");
        cmbDay.addItem("Wednesday");
        cmbDay.addItem("Thursday");
        cmbDay.addItem("Friday");
        cmbDay.addItem("Saturday");
        if (strDay.equals("Su")) {
            cmbDay.setSelectedItem("Sunday");
        } else if (strDay.equals("M")) {
            cmbDay.setSelectedItem("Monday");
        } else if (strDay.equals("T")) {
            cmbDay.setSelectedItem("Tuesday");
        } else if (strDay.equals("W")) {
            cmbDay.setSelectedItem("Wednesday");
        } else if (strDay.equals("Th")) {
            cmbDay.setSelectedItem("Thursday");
        } else if (strDay.equals("F")) {
            cmbDay.setSelectedItem("Friday");
        } else if (strDay.equals("S")) {
            cmbDay.setSelectedItem("Saturday");
        } 
    }
    
    public void setCmbType(String strType){
        cmbType.removeAllItems();
        cmbType.addItem(strDashLine);
        cmbType.addItem("Face to Face");
        cmbType.addItem("Online");
        if(strType.equals("FTF")){
            cmbType.setSelectedItem("Face to Face");
        } else if(strType.equals("OL")){
            cmbType.setSelectedItem("Online");
        }
    }
    
    public SubjectSchedule() {
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

        pnlStudent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbDay = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRoom = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSequence = new javax.swing.JTextField();
        txtFacultyId = new javax.swing.JTextField();
        cmbSY = new javax.swing.JComboBox<>();
        cmbSemester = new javax.swing.JComboBox<>();
        cmbCollegeCode = new javax.swing.JComboBox<>();
        cmbBlock = new javax.swing.JComboBox<>();
        cmbSubjectCode = new javax.swing.JComboBox<>();
        lblInvSY = new javax.swing.JLabel();
        lblInvSemester = new javax.swing.JLabel();
        lblInvCollegeCode = new javax.swing.JLabel();
        lblInvBlock = new javax.swing.JLabel();
        lblInvSubject = new javax.swing.JLabel();
        lblInvDay = new javax.swing.JLabel();
        lblInvFacultyId = new javax.swing.JLabel();
        lblInvSqn = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        lblInvTime = new javax.swing.JLabel();
        lblInvRoom = new javax.swing.JLabel();
        lblInvType = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForms = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlStudent.setBackground(new java.awt.Color(204, 255, 255));
        pnlStudent.setForeground(new java.awt.Color(255, 51, 51));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("View Subject Schedule");

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("School Year");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Semester");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("College Code");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Block Number");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Subject Code");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Day");

        cmbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Time");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Room");

        txtRoom.setText("jTextField3");

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Type");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Sequence Number");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Faculty Id");

        txtSequence.setText("jTextField4");

        txtFacultyId.setText("jTextField5");

        cmbSY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbCollegeCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbBlock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSubjectCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblInvSY.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSY.setText("*");

        lblInvSemester.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSemester.setText("*");

        lblInvCollegeCode.setForeground(new java.awt.Color(255, 51, 51));
        lblInvCollegeCode.setText("*");

        lblInvBlock.setForeground(new java.awt.Color(255, 51, 51));
        lblInvBlock.setText("*");

        lblInvSubject.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSubject.setText("*");

        lblInvDay.setForeground(new java.awt.Color(255, 51, 51));
        lblInvDay.setText("*");

        lblInvFacultyId.setForeground(new java.awt.Color(255, 51, 51));
        lblInvFacultyId.setText("*");

        lblInvSqn.setForeground(new java.awt.Color(255, 51, 51));
        lblInvSqn.setText("*");

        txtTime.setText("jTextField1");

        lblInvTime.setForeground(new java.awt.Color(255, 51, 51));
        lblInvTime.setText("*");

        lblInvRoom.setForeground(new java.awt.Color(255, 51, 51));
        lblInvRoom.setText("*");

        lblInvType.setForeground(new java.awt.Color(255, 51, 51));
        lblInvType.setText("*");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStudentLayout = new javax.swing.GroupLayout(pnlStudent);
        pnlStudent.setLayout(pnlStudentLayout);
        pnlStudentLayout.setHorizontalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblInvSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lblInvBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblInvCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lblInvSY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlStudentLayout.createSequentialGroup()
                                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cmbSubjectCode, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtSequence, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtRoom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                                                .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(23, 23, 23))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentLayout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblInvDay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(97, 97, 97)))
                                                        .addGroup(pnlStudentLayout.createSequentialGroup()
                                                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblInvRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                                                    .addComponent(jLabel11)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblInvSqn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(25, 25, 25)))
                                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cmbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtFacultyId)
                                                        .addComponent(txtTime)
                                                        .addGroup(pnlStudentLayout.createSequentialGroup()
                                                            .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(lblInvType, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(lblInvTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(pnlStudentLayout.createSequentialGroup()
                                                                    .addComponent(jLabel12)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblInvFacultyId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(0, 46, Short.MAX_VALUE))))
                                                .addComponent(cmbBlock, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblInvSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(93, 93, 93))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cmbCollegeCode, javax.swing.GroupLayout.Alignment.LEADING, 0, 330, Short.MAX_VALUE)
                                            .addComponent(cmbSemester, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbSY, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                            .addGap(59, 59, 59)
                                            .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlStudentLayout.setVerticalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15))
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblInvSY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblInvSemester))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblInvCollegeCode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblInvBlock))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(btnReset))))
                .addGap(26, 26, 26)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblInvSubject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(lblInvDay)
                    .addComponent(lblInvTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(lblInvRoom)
                    .addComponent(lblInvType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(lblInvFacultyId)
                    .addComponent(lblInvSqn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSequence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFacultyId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnConfirm))
                .addContainerGap())
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
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addComponent(pnlStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
        clearForms();
        setFieldsEditable(true);
        removeError();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        removeError();
        setFieldsEditable(true);
        
        cmbSY.setEnabled(false);
        cmbSemester.setEnabled(false);
        cmbCollegeCode.setEnabled(false);
        cmbBlock.setEnabled(false);
        cmbSubjectCode.setEnabled(false);
        txtSequence.setEditable(false);
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnAdd.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        removeError();
        setFieldsEditable(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
        setFieldsEditable(false);
        cmbSY.setEnabled(true);
        cmbSemester.setEnabled(true);
        cmbCollegeCode.setEnabled(true);
        cmbBlock.setEnabled(true);
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
        if(btnDelete.isEnabled() && isFieldValidated() ){
            deleteSubject();
            return;
        }

    }//GEN-LAST:event_btnConfirmActionPerformed
    public void addSubject(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to add? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("INSERT INTO oop23.subject_schedule VALUES"
                + "('" + cmbSY.getSelectedItem().toString()
                + "', '" + cmbSemester.getSelectedItem().toString()
                + "', '" + cmbCollegeCode.getSelectedItem().toString()        
                + "', '" + cmbBlock.getSelectedItem().toString()           
                + "', '" + cmbSubjectCode.getSelectedItem().toString()        
                + "', '" + f.toDay(cmbDay.getSelectedItem().toString())
                + "', '" + txtTime.getText()      
                + "', '" + txtRoom.getText()
                + "', '" + f.toType(cmbType.getSelectedItem().toString())        
                + "', '" + txtSequence.getText()
                + "', '" + txtFacultyId.getText()        
                + "');"        
                );
                ps.execute();
            }
            catch(Exception e){
                f.displayError(e);
            }
            refreshTable();
            resetButtons();
            JOptionPane.showMessageDialog(null, "Addition is successful");
            setFieldsEditable(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Addition is aborted");
        }
        
    }
    
    public void editSubject(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to Edit? ", "confirm",JOptionPane.YES_NO_OPTION);
        String strSY = "", strSemester = "", strCollegeCode = "", strBlock ="", strSubjectCode ="", strSequence="";
        strSY = cmbSY.getSelectedItem().toString();
        strSemester = cmbSemester.getSelectedItem().toString();
        strCollegeCode = cmbCollegeCode.getSelectedItem().toString();
        strBlock = cmbBlock.getSelectedItem().toString();
        strSubjectCode = cmbSubjectCode.getSelectedItem().toString();
        strSequence = txtSequence.getText();
        
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("UPDATE oop23.subject_schedule SET "
                + "day ='" + f.toDay(cmbDay.getSelectedItem().toString())
                + "', time ='" + txtTime.getText()
                + "', room ='" + txtRoom.getText()        
                + "', type ='" + f.toType(cmbType.getSelectedItem().toString()) 
                + "', faculty_id = '" + txtFacultyId.getText()        
                + "' WHERE sy ='" 
                + strSY + "' and Semester = '" 
                + strSemester + "' and college_code = '"
                + strCollegeCode + "' and block_no ='"
                + strBlock + "' and subject_code ='"
                + cmbSubjectCode.getSelectedItem().toString() + "' and sequence_no ='"
                + txtSequence.getText() +"';");
                
                ps.execute();
            }
            catch(Exception e){
                f.displayError(e);
            }
            refreshTable();
            resetButtons();
            JOptionPane.showMessageDialog(null, "Edit is successful");
            setFieldsEditable(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Edit is aborted");
        }
    }
    
    public void deleteSubject(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to Delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        String strSY = "", strSemester = "", strCollegeCode = "", strBlock ="", strSubjectCode ="", strSequence="";
        strSY = cmbSY.getSelectedItem().toString();
        strSemester = cmbSemester.getSelectedItem().toString();
        strCollegeCode = cmbCollegeCode.getSelectedItem().toString();
        strBlock = cmbBlock.getSelectedItem().toString();
        strSubjectCode = cmbSubjectCode.getSelectedItem().toString();
        strSequence = txtSequence.getText();
        
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("DELETE FROM oop23.subject_schedule "      
                + "WHERE sy = '" 
                + strSY + "' and Semester = '" 
                + strSemester + "' and college_code = '"
                + strCollegeCode + "' and block_no ='"
                + strBlock + "' and subject_code ='"
                + cmbSubjectCode.getSelectedItem().toString() + "' and sequence_no ='"
                + txtSequence.getText() +"';");
                
                ps.execute();
            }
            catch(Exception e){
                f.displayError(e);
            }
            refreshTable();
            resetButtons();
            JOptionPane.showMessageDialog(null, "Deletion is successful");
            setFieldsEditable(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Deletion is aborted");
        }
    }
    
    public void refreshTable(){
        try{
            ps = conn.prepareStatement("SELECT * FROM oop23.subject_schedule ");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            f.displayError(e);
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        refreshTable();
        removeError();
        clearForms();
        setFieldsEditable(false);
        cmbSY.setEnabled(true);
        cmbSemester.setEnabled(true);
        cmbCollegeCode.setEnabled(true);
        cmbBlock.setEnabled(true);
    }//GEN-LAST:event_formWindowOpened
    public void setFieldsEditable(boolean blFlag){
        cmbSY.setEnabled(blFlag);
        cmbSemester.setEnabled(blFlag);
        cmbCollegeCode.setEnabled(blFlag);
        cmbBlock.setEnabled(blFlag);
        cmbSubjectCode.setEnabled(blFlag);
        cmbDay.setEnabled(blFlag);
        txtTime.setEditable(blFlag);
        txtRoom.setEditable(blFlag);
        cmbType.setEnabled(blFlag);
        txtSequence.setEditable(blFlag);
        txtFacultyId.setEditable(blFlag);        
    }
    
    
    
    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        boolean isEditing = tblForms.isEditing();
        int intRow = tblForms.getSelectedRow();
        String strSY = "", strSemester = "", strCollegeCode = "", strBlock ="", strSequence ="", strSubjectCode ="", strDay="", strTime ="", strRoom="", strType="", strFacultyId="";
        
        //reset field and buttons
        removeError();
        resetButtons();
        setFieldsEditable(false);
        cmbSY.setEnabled(true);
        cmbSemester.setEnabled(true);
        cmbCollegeCode.setEnabled(true);
        cmbBlock.setEnabled(true);
        
        //get values for txtfields
        strSY = tblForms.getValueAt(intRow, 0).toString();
        strSemester = tblForms.getValueAt(intRow, 1).toString();
        strCollegeCode = tblForms.getValueAt(intRow, 2).toString();
        strBlock = tblForms.getValueAt(intRow, 3).toString();
        strSubjectCode = tblForms.getValueAt(intRow, 4).toString(); 
        strDay = tblForms.getValueAt(intRow, 5).toString();
        strTime = tblForms.getValueAt(intRow, 6).toString();
        strRoom = tblForms.getValueAt(intRow, 7).toString();
        strType = tblForms.getValueAt(intRow, 8).toString();        
        strSequence = tblForms.getValueAt(intRow, 9).toString();
        strFacultyId = tblForms.getValueAt(intRow, 10).toString();
        
        setCmbSY(strSY);
        setCmbSemester(strSemester);
        setCmbCollegeCode(strCollegeCode);
        setCmbBlock(strBlock);
        setCmbSubjectCode(strSubjectCode);
        setCmbDay(strDay);
        txtTime.setText(strTime);
        txtRoom.setText(strRoom);
        setCmbType(strType);
        txtSequence.setText(strSequence);
        txtFacultyId.setText(strFacultyId);
        
        
    }//GEN-LAST:event_tblFormsMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String strSY = "", strSemester = "", strCollegeCode = "", strBlock ="";
        strSY = cmbSY.getSelectedItem().toString();
        strSemester = cmbSemester.getSelectedItem().toString();
        strCollegeCode = cmbCollegeCode.getSelectedItem().toString();
        strBlock = cmbBlock.getSelectedItem().toString();
        
        try{
            ps = conn.prepareStatement("SELECT * FROM oop23.subject_schedule WHERE sy ='" 
            + strSY + "' and Semester = '" 
            + strSemester + "' and college_code = '"
            + strCollegeCode + "' and block_no ='"
            + strBlock + "';");
            
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            f.displayError(e);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        clearForms();
        refreshTable();
        resetButtons();
        setFieldsEditable(false);
    }//GEN-LAST:event_btnResetActionPerformed
    
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
            java.util.logging.Logger.getLogger(SubjectSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubjectSchedule().setVisible(true);
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
    private javax.swing.JComboBox<String> cmbBlock;
    private javax.swing.JComboBox<String> cmbCollegeCode;
    private javax.swing.JComboBox<String> cmbDay;
    private javax.swing.JComboBox<String> cmbSY;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JComboBox<String> cmbSubjectCode;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInvBlock;
    private javax.swing.JLabel lblInvCollegeCode;
    private javax.swing.JLabel lblInvDay;
    private javax.swing.JLabel lblInvFacultyId;
    private javax.swing.JLabel lblInvRoom;
    private javax.swing.JLabel lblInvSY;
    private javax.swing.JLabel lblInvSemester;
    private javax.swing.JLabel lblInvSqn;
    private javax.swing.JLabel lblInvSubject;
    private javax.swing.JLabel lblInvTime;
    private javax.swing.JLabel lblInvType;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtFacultyId;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JTextField txtSequence;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
