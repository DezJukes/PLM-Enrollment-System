package registration_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.regex.Pattern;


public class Student extends javax.swing.JFrame {
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String strDashLine = "--------------------";
    Functions f = new Functions();
    TableView tv = new TableView();
    
    /**
     * Creates new form Student
     */
       
    public boolean isFieldValidated(){
        boolean blIsEmpty = true, blFlag = true;
        
        if(cmbGender.getSelectedItem().toString().equals(strDashLine)){
            lblInvalidGender.setVisible(true);
            blIsEmpty = false;
        }
        if(cmbStCourseCode.getSelectedItem().toString().equals(strDashLine)){
            lblInvalidCourse.setVisible(true);
            blIsEmpty = false;
        }
        
        if(f.isEmpty(txtStudentNo.getText())){
            lblInvalidStNum.setVisible(true);
            blIsEmpty = false;
        } else if(!f.isValidStudentNo(txtStudentNo.getText())){
            lblInvalidStNum.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid student number (e.g., 2022-34000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtFirstName.getText())){
            blIsEmpty = false;
            lblInvalidFName.setVisible(true);
        } else if(!f.isValidName(txtFirstName.getText())){
            lblInvalidFName.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid  name (letters and spaces only, up to 50 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(f.isEmpty(txtLastName.getText())){
            blIsEmpty = false;
            lblInvalidLName.setVisible(true);
        } else if(!f.isValidName(txtLastName.getText())){
            lblInvalidLName.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid  name (letters and spaces only, up to 50 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(f.isEmpty(txtEmail.getText())){
            blIsEmpty = false;
            lblInvalidEmail.setVisible(true);
        } else if(!f.isValidEmail(txtEmail.getText())){
            lblInvalidEmail.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtContact.getText())){
            lblInvalidContact.setVisible(true);
            blIsEmpty = false;
        } else if(!f.isValidContactNumber(txtContact.getText())){
            lblInvalidContact.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid contact number (0900000000).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(f.isEmpty(txtAddress.getText())){
            blIsEmpty = false;
            lblInvalidAddress.setVisible(true);
        } else if(!f.isValidAddress(txtAddress.getText())){
            lblInvalidAddress.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid address (letters and spaces only, up to 255 characters).", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(cmbStCourseCode.getSelectedItem().toString().equals(strDashLine)){
            blIsEmpty = false;
            lblInvalidCourse.setVisible(true);
        }
        
        try{
            new SimpleDateFormat("yyyy-MM-dd").format(dtcBirthday.getDate());
        } catch(Exception e){
            lblInvalidBday.setVisible(true);
            blFlag = false;
            JOptionPane.showMessageDialog(null, "Please enter a valid date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(!blIsEmpty){
            JOptionPane.showMessageDialog(null, "Fields shouldn't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
            
        }
        return blFlag;
    }
    
    public void setCmbStatus(String status){
        cmbStStatus.removeAllItems();
        cmbStStatus.addItem("Active");
        cmbStStatus.addItem("Inactive");
        if(status.equals("A")){
            cmbStStatus.setSelectedItem("Active");
        } else if(status.equals("I")){
            cmbStStatus.setSelectedItem("Inactive");
        }
    }
    
    public void setCmbGender(String gender){
        cmbGender.removeAllItems();
        cmbGender.addItem(strDashLine);
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        if(gender.equals("M")){
            cmbGender.setSelectedItem("Male");
        } 
        else if(gender.equals("F")) {
            cmbGender.setSelectedItem("Female");
        }
    }
    
    public void setCourseCode(String strCourseCode){
        cmbStCourseCode.removeAllItems();
        cmbStCourseCode.addItem(strDashLine);
        try {
            ps = conn.prepareStatement("Select course_code from oop23.course");
            rs = ps.executeQuery();
            while(rs.next()) {
                cmbStCourseCode.addItem(rs.getString("course_code"));
            }
            cmbStCourseCode.setSelectedItem(strCourseCode);
        } catch(Exception e){
            f.displayError(e);
        }
    }
       
    public void resetButtons(){
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
    }
    public void refreshTable(){
        try {
            ps = conn.prepareStatement("SELECT * FROM oop23.vwStudent");
            rs = ps.executeQuery();
            tblForms.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e) {
            f.displayError(e);
        }
        lblTitle.requestFocusInWindow();
    }  
    public void searchTable(String strStudentNumber){
        String strGender = "", strCourseCode = "";
        try{
                ps = conn.prepareStatement("Select * FROM oop23.student WHERE student_no = '" + strStudentNumber + "';");
                rs = ps.executeQuery();
                if(rs.next()){
                    strGender = rs.getString(("gender"));
                    strCourseCode = rs.getString("course_code");

                    txtStudentNo.setText(rs.getString("student_no"));
                    txtLastName.setText(rs.getString("lastname"));
                    txtFirstName.setText(rs.getString("firstname"));
                    txtEmail.setText(rs.getString("email"));
                    setCmbGender(strGender);
                    txtContact.setText(rs.getString("cp_num"));
                    txtAddress.setText(rs.getString("address"));
                    dtcBirthday.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("bday")));
                    setCmbStatus(rs.getString("status"));
                    //new rs
                    setCourseCode(strCourseCode);
                }
            }
            catch(Exception e) {
                f.displayError(e);
            }
    }
    public void setFormEditable(boolean blFlag){
        txtLastName.setEditable(blFlag);
        txtFirstName.setEditable(blFlag);
        txtEmail.setEditable(blFlag);
        cmbGender.setEnabled(blFlag);
        cmbStCourseCode.setEnabled(blFlag);
        txtContact.setEditable(blFlag);
        txtAddress.setEditable(blFlag);
        dtcBirthday.setEnabled(blFlag);
        cmbStStatus.setEnabled(blFlag);
    }
    public void clearForms(){
        txtLastName.setText(null);
        txtFirstName.setText(null);
        txtEmail.setText(null);
        txtContact.setText(null);
        txtAddress.setText(null);
        dtcBirthday.setDate(null);
        resetComboBox();
    }
    
    public Student() {
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
        lblInvalidStNum = new javax.swing.JLabel();
        txtStudentNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbStCourseCode = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cmbStStatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblInvalidEmail = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<>();
        dtcBirthday = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblInvalidFName = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblInvalidLName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblInvalidContact = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        lblInvalidCourse = new javax.swing.JLabel();
        lblInvalidStatus = new javax.swing.JLabel();
        lblInvalidGender = new javax.swing.JLabel();
        lblInvalidBday = new javax.swing.JLabel();
        lblInvalidAddress = new javax.swing.JLabel();
        btnViewAll = new javax.swing.JButton();
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
        lblTitle.setText("View Student Information");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Student Number (required)");

        lblInvalidStNum.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidStNum.setText("* invalid format, should be 0000-00000");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Course code");

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

        lblInvalidEmail.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidEmail.setText("* invalid email format");

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

        lblInvalidFName.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidFName.setText("* invalid name");

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

        lblInvalidLName.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidLName.setText("* invalid name");

        lblInvalidContact.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidContact.setText("*invalid format, should be 0900000000");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblInvalidCourse.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidCourse.setText("*");

        lblInvalidStatus.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidStatus.setText("*");

        lblInvalidGender.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidGender.setText("*");

        lblInvalidBday.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidBday.setText("*");

        lblInvalidAddress.setForeground(new java.awt.Color(255, 51, 51));
        lblInvalidAddress.setText("*");

        btnViewAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnViewAll.setText("View All Info");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStudentLayout = new javax.swing.GroupLayout(pnlStudent);
        pnlStudent.setLayout(pnlStudentLayout);
        pnlStudentLayout.setHorizontalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConfirm)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlStudentLayout.createSequentialGroup()
                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addComponent(txtStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlStudentLayout.createSequentialGroup()
                                                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblInvalidGender))
                                                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30)
                                                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentLayout.createSequentialGroup()
                                                        .addComponent(jLabel12)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblInvalidBday)
                                                        .addGap(83, 83, 83))
                                                    .addComponent(dtcBirthday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblInvalidAddress))
                                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblInvalidContact))
                                        .addComponent(txtContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                        .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblInvalidFName))
                                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblInvalidCourse))
                                                    .addComponent(cmbStCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 29, 29)
                                                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblInvalidLName))
                                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                                            .addComponent(jLabel11)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(lblInvalidStatus))
                                                        .addComponent(cmbStStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlStudentLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblInvalidEmail)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnViewAll))
                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInvalidStNum))
                                    .addGroup(pnlStudentLayout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(31, Short.MAX_VALUE))))
        );
        pnlStudentLayout.setVerticalGroup(
            pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblInvalidStNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(lblInvalidCourse)
                    .addComponent(lblInvalidStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(lblInvalidFName)
                    .addComponent(lblInvalidLName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblInvalidEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(lblInvalidAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(lblInvalidGender)
                    .addComponent(lblInvalidBday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtcBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbGender))
                .addGap(15, 15, 15)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblInvalidContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(pnlStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnConfirm))
                .addContainerGap(34, Short.MAX_VALUE))
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
                .addGap(21, 21, 21)
                .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        int intRow = tblForms.getSelectedRow();
        String strGender = "", strCourseCode = "", strStudentNumber ="", strStatus ="";
        strStudentNumber = tblForms.getValueAt(intRow, 0).toString();
        removeError();
        resetButtons();
        try{
                ps = conn.prepareStatement("Select * FROM oop23.student WHERE student_no = '" + strStudentNumber + "';");
                rs = ps.executeQuery();
                if(rs.next()){
                    strGender = rs.getString(("gender"));
                    strCourseCode = rs.getString("course_code");
                    strStatus = rs.getString("status");
                    txtStudentNo.setText(rs.getString("student_no"));
                    txtLastName.setText(rs.getString("lastname"));
                    txtFirstName.setText(rs.getString("firstname"));
                    txtEmail.setText(rs.getString("email"));                    
                    txtContact.setText(rs.getString("cp_num"));
                    txtAddress.setText(rs.getString("address"));
                    dtcBirthday.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("bday")));
                    setCmbGender(strGender);                    
                    setCmbStatus(strStatus);
                    setCourseCode(strCourseCode);
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
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
        txtStudentNo.setEditable(true);
        clearForms();
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
        removeError();
        if(cmbStCourseCode.getItemCount() == 0)
            resetComboBox();
        txtStudentNo.setEditable(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
        setFormEditable(false);
        clearForms();
        removeError();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchTable(txtStudentNo.getText());
        resetButtons();
        removeError();
        setFormEditable(false);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        //Check for empty Fields
        removeError();      
        if(btnAdd.isEnabled() && isFieldValidated()){
            addStudent();
            refreshTable();
            return;
        }

        if(btnEdit.isEnabled() && isFieldValidated()){
            editStudent();
            refreshTable();
            return;
        }
        if(btnDelete.isEnabled()){
            if(f.isEmpty(txtStudentNo.getText()) || !f.isValidStudentNo(txtStudentNo.getText())){
                JOptionPane.showMessageDialog(null, "Please enter a valid student number (0000-00000).", "Error", JOptionPane.ERROR_MESSAGE);
                lblInvalidStNum.setVisible(true);
                return;
            }
            deleteStudent();
            return;
        }
        
        
        
        
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        // TODO add your handling code here:
        tv.setVisible(true);
    }//GEN-LAST:event_btnViewAllActionPerformed
    public void removeError(){
        lblInvalidStNum.setVisible(false);
        lblInvalidLName.setVisible(false);
        lblInvalidFName.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidStNum.setVisible(false);
        lblInvalidCourse.setVisible(false);
        lblInvalidStatus.setVisible(false);
        lblInvalidGender.setVisible(false);
        lblInvalidBday.setVisible(false);
        lblInvalidContact.setVisible(false);
        lblInvalidAddress.setVisible(false);
    }
    public void resetComboBox(){
        cmbGender.removeAllItems();
        cmbStStatus.removeAllItems();
        cmbStCourseCode.removeAllItems();
        
        cmbGender.addItem(strDashLine);
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        
        cmbStCourseCode.addItem(strDashLine);
        try {
            ps = conn.prepareStatement("Select course_code from oop23.course order by course_code;");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbStCourseCode.addItem(rs.getString("course_code"));
            }
        }
        catch(Exception e){
            f.displayError(e);
        }
        cmbStStatus.addItem("Active");
        cmbStStatus.addItem("Inactive");
    }
    public void addStudent() {
        String strGender = "", strStatus = "";
        strStatus = f.toStatus(cmbStStatus.getSelectedItem().toString());
        strGender = f.toGender(cmbGender.getSelectedItem().toString());
        try{
            int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to add? ", "confirm",JOptionPane.YES_NO_OPTION);
            if(intAnswer == 0){
                ps = conn.prepareStatement("INSERT INTO oop23.student VALUES ('"
                        +txtStudentNo.getText()
                        +"','"+txtLastName.getText()
                        +"','"+txtFirstName.getText()
                        +"','"+txtEmail.getText()
                        +"','"+ strGender
                        +"','"+cmbStCourseCode.getSelectedItem().toString()
                        +"','"+txtContact.getText()
                        +"','"+txtAddress.getText()
                        +"','"+new SimpleDateFormat("yyyy-MM-dd").format(dtcBirthday.getDate())
                        +"','"+ strStatus
                        +"')");
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
    public void editStudent() {
        String strGender;
        if (cmbGender.getSelectedItem().toString().equals("Male")) {
            strGender = "M";
        } else {
            strGender = "F";
        }
        
        String strStatus;
        if (cmbStStatus.getSelectedItem().toString().equals("Active")) {
            strStatus = "A";
        } else {
            strStatus = "I";
        }
        try{
            int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to edit? ", "confirm",JOptionPane.YES_NO_OPTION);
            if(intAnswer == 0){
                ps = conn.prepareStatement("UPDATE oop23.student SET"
                    +" lastname = '"+txtLastName.getText()
                    +"', firstname = '"+txtFirstName.getText()
                    +"', email = '"+txtEmail.getText()
                    +"', gender = '"+strGender
                    +"', course_code = '"+cmbStCourseCode.getSelectedItem().toString()
                    +"', cp_num = '"+txtContact.getText()
                    +"', address = '"+txtAddress.getText()
                    +"', bday = '"+new SimpleDateFormat("yyyy-MM-dd").format(dtcBirthday.getDate())
                    +"', status = '"+strStatus
                    +"' WHERE "
                    +"student_no = '"
                    +txtStudentNo.getText()
                    +"'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Edit is successful");
                resetButtons();
                removeError();
                setFormEditable(false);
                
            }
            else {
                JOptionPane.showMessageDialog(null, "Edit is aborted");
            }
            
        }
        catch(Exception e) {
                f.displayError(e);
            }
    }
    public void deleteStudent() {
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("DELETE FROM oop23.student"
                        + " WHERE student_no = '"
                        +txtStudentNo.getText()
                        + "'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deletion is successful");
                resetButtons();
                clearForms();
                removeError();
                refreshTable();
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
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student().setVisible(true);
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
    private javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbStCourseCode;
    private javax.swing.JComboBox<String> cmbStStatus;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInvalidAddress;
    private javax.swing.JLabel lblInvalidBday;
    private javax.swing.JLabel lblInvalidContact;
    private javax.swing.JLabel lblInvalidCourse;
    private javax.swing.JLabel lblInvalidEmail;
    private javax.swing.JLabel lblInvalidFName;
    private javax.swing.JLabel lblInvalidGender;
    private javax.swing.JLabel lblInvalidLName;
    private javax.swing.JLabel lblInvalidStNum;
    private javax.swing.JLabel lblInvalidStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtStudentNo;
    // End of variables declaration//GEN-END:variables
}
