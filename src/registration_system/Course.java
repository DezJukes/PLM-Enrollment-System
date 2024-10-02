package registration_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class Course extends javax.swing.JFrame {
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Functions f = new Functions();
    String strDashLine = "--------------------";
    boolean blIsOpen = false;
    /**
     * Creates new form Course
     */
    public boolean isFieldValidated(){
        boolean blFlag = true, blFieldsEmpty = false, blInvalidDate = false;
        if(cmbCollegeCode.getSelectedItem().equals(strDashLine)){
            lblInvCollege.setVisible(true);
            JOptionPane.showMessageDialog(null, "Please select a College", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        if(f.isEmpty(txtCourseCode.getText())){
            lblInvCourse.setVisible(true);
            blFieldsEmpty = true;
            blFlag = false;
        } else if (!f.isValidCollegeCode(txtCourseCode.getText())){
            JOptionPane.showMessageDialog(null, "Please enter a valid Course Code (letters and spaces only, up to 10 characters).", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        if(f.isEmpty(txtDescription.getText())){
            lblInvDescription.setVisible(true);
            blFieldsEmpty = true;
            blFlag = false;
        }
        else if(!f.isValidDescription(txtDescription.getText())){
            lblInvDescription.setVisible(true);
            JOptionPane.showMessageDialog(null, "Please enter a valid Course Description (letters and spaces only, up to 100 characters).", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        if(cmbCollegeCode.getSelectedItem().toString().equals(strDashLine)){
            lblInvCollegeCode.setVisible(true);
            blFieldsEmpty = true;
        }
        try{
            new SimpleDateFormat("yyyy-MM-dd").format(dtcDateOpened.getDate());
        } catch(Exception e){
            blFlag = false;
            blInvalidDate = true;
            lblInvDateOpen.setVisible(true);
        }
        try{
            new SimpleDateFormat("yyyy-MM-dd").format(dtcDateClosed.getDate());
        } catch(Exception e){
            blFlag = false;
            blInvalidDate = true;
            lblInvDateClose.setVisible(true);
        }
        if(blInvalidDate){
            JOptionPane.showMessageDialog(null, "Invalid Date Format", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        if(blFieldsEmpty){
            JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            blFlag = false;
        }
        //if()
            
        
        return blFlag;
    }
    
    
    public void clearForms(){
        txtCourseCode.setText(null);
        txtDescription.setText(null);
        dtcDateOpened.setDate(null);
        dtcDateClosed.setDate(null);
    }
    public void setFormEditable(boolean blFlag){
        txtCourseCode.setEditable(blFlag);
        txtDescription.setEditable(blFlag);
        cmbCollegeCode.setEnabled(blFlag);
        dtcDateOpened.setEnabled(blFlag);
        dtcDateClosed.setEnabled(blFlag);
        cmbStatus.setEnabled(blFlag);
    }    
    
    public void removeError(){
        lblInvCollege.setVisible(false);
        lblInvCourse.setVisible(false);
        lblInvDescription.setVisible(false);
        lblInvCollegeCode.setVisible(false);
        lblInvDateOpen.setVisible(false);
        lblInvDateClose.setVisible(false);
        lblInvStatus.setVisible(false);
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
        cmbStatus.addItem("Active");
        cmbStatus.addItem("Inactive");
        if(status.equals("A")){
            cmbStatus.setSelectedItem("Active");
        } else if(status.equals("I")){
            cmbStatus.setSelectedItem("Inactive");
        } 
    }
    
    public void refreshTable(){
        String strCollegeCode = "";
        if(cmbCollege.getSelectedIndex() == 0){
            try {
                ps = conn.prepareStatement("SELECT * FROM oop23.vwCourse order by college_code");
                rs = ps.executeQuery();
                tblForms.setModel(DbUtils.resultSetToTableModel(rs));

            }
            catch(Exception e) {
                f.displayError(e);
            }
        } else {
            strCollegeCode = cmbCollegeCode.getSelectedItem().toString();
            try {
                ps = conn.prepareStatement("SELECT * FROM oop23.vwCourse WHERE college_code = '" + strCollegeCode + "'order by college_code");
                rs = ps.executeQuery();
                tblForms.setModel(DbUtils.resultSetToTableModel(rs));

            }
            catch(Exception e) {
                f.displayError(e);
            }
        }
    }
    
    public void setCmbCollegeCode(String strCollegeCode){
        cmbCollegeCode.removeAllItems();
        cmbCollegeCode.addItem(strDashLine);
        try {
            ps = conn.prepareStatement("Select * from oop23.college;");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbCollegeCode.addItem(rs.getString("college_code"));
            }
            cmbCollegeCode.setSelectedItem(strCollegeCode);
        } catch (Exception e){
            f.displayError(e);
        }
    }
    
    public Course() {
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
        jLabel1 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        dtcDateOpened = new com.toedter.calendar.JDateChooser();
        dtcDateClosed = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblInvCollege = new javax.swing.JLabel();
        lblInvCourse = new javax.swing.JLabel();
        lblInvDescription = new javax.swing.JLabel();
        lblInvCollegeCode = new javax.swing.JLabel();
        lblInvDateOpen = new javax.swing.JLabel();
        lblInvDateClose = new javax.swing.JLabel();
        lblInvStatus = new javax.swing.JLabel();
        cmbCollege = new javax.swing.JComboBox<>();
        cmbCollegeCode = new javax.swing.JComboBox<>();
        txtCourseCode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForms = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Select College");

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("View Course Information");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Course code");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Description");

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(1);
        txtDescription.setAutoscrolls(false);
        jScrollPane2.setViewportView(txtDescription);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("College code");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Date opened");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Date closed");

        dtcDateOpened.setEnabled(false);

        dtcDateClosed.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Status");

        cmbStatus.setEnabled(false);

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

        lblInvCollege.setForeground(new java.awt.Color(255, 51, 51));
        lblInvCollege.setText("*");

        lblInvCourse.setForeground(new java.awt.Color(255, 51, 51));
        lblInvCourse.setText("*Invalid");

        lblInvDescription.setForeground(new java.awt.Color(255, 51, 51));
        lblInvDescription.setText("*Invalid");

        lblInvCollegeCode.setForeground(new java.awt.Color(255, 51, 51));
        lblInvCollegeCode.setText("*");

        lblInvDateOpen.setForeground(new java.awt.Color(255, 51, 51));
        lblInvDateOpen.setText("*Invalid");

        lblInvDateClose.setForeground(new java.awt.Color(255, 51, 51));
        lblInvDateClose.setText("*Invalid");

        lblInvStatus.setForeground(new java.awt.Color(255, 51, 51));
        lblInvStatus.setText("*");

        cmbCollege.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));
        cmbCollege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCollegeActionPerformed(evt);
            }
        });

        cmbCollegeCode.setEnabled(false);

        txtCourseCode.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCourseCode)
                    .addComponent(cmbCollegeCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCollege, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInvDateOpen))
                            .addComponent(dtcDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(lblInvDateClose))
                            .addComponent(dtcDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInvCollege))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInvStatus))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInvCourse))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(lblInvDescription))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInvCollegeCode))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnConfirm)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblInvCollege))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblInvCourse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblInvDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblInvCollegeCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(lblInvDateOpen)
                    .addComponent(lblInvDateClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtcDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblInvStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnConfirm))
                .addContainerGap(28, Short.MAX_VALUE))
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

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormsMouseClicked
        // TODO add your handling code here:
        int intRow = tblForms.getSelectedRow();
        String strCourseCode = "", strCollegeCode = "";
        strCourseCode = tblForms.getValueAt(intRow, 0).toString();
        resetButtons();
        setFormEditable(false);
        try{
            cmbCollegeCode.removeAllItems();
            ps = conn.prepareStatement("Select * FROM oop23.course WHERE course_code = '" + strCourseCode + "';");
            rs = ps.executeQuery();
            if(rs.next()){
                strCollegeCode = rs.getString("college_code");
                txtCourseCode.setText(rs.getString("course_code"));
                txtDescription.setText(rs.getString("description"));
                cmbCollegeCode.addItem(strCollegeCode);
                dtcDateOpened.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_opened")));
                dtcDateClosed.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_closed")));
                setStatus(rs.getString("status"));
                
                //new rs
                setCmbCollegeCode(strCollegeCode);
            }
        }
        catch(Exception e) {
            f.displayError(e);
        }
    }//GEN-LAST:event_tblFormsMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnCancel.setEnabled(true);
        btnConfirm.setEnabled(true);
        cmbCollegeCode.setEnabled(true);
        setFormEditable(true);
        txtCourseCode.setEditable(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            ps = conn.prepareStatement("Select * from oop23.college;");
            rs = ps.executeQuery();
            while(rs.next()){
                cmbCollegeCode.addItem(rs.getString("college_code"));
            }
        } catch (Exception e){
            f.displayError(e);
        }
        removeError();
        cmbCollege.removeAllItems();
        try{
            ps = conn.prepareStatement("Select description from oop23.college");
            rs = ps.executeQuery();
            cmbCollege.addItem("View All Colleges");
            while(rs.next())
                cmbCollege.addItem(rs.getString("description"));
        }
        catch(Exception e){
            f.displayError(e);
        }
        
        if(cmbCollege.getSelectedItem().equals("View All Colleges")){
            refreshTable();
        }
        blIsOpen = true;
    }//GEN-LAST:event_formWindowOpened

    private void cmbCollegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCollegeActionPerformed
        // TODO add your handling code here:
        String strCollegeCode = ""; 
        removeError();
        clearForms();
        resetButtons();
        setFormEditable(false);
        if(blIsOpen){
            if(cmbCollege.getSelectedIndex() == 0){
                refreshTable();
                setCmbCollegeCode(strDashLine);
                return;
            } 
            
            try{
                ps = conn.prepareStatement("Select * from oop23.college WHERE description ='" + cmbCollege.getSelectedItem()+ "';");
                rs = ps.executeQuery();
                if(rs.next()){
                    strCollegeCode = rs.getString("college_code");
                } 
                
                ps = conn.prepareStatement("Select * from oop23.vwCourse WHERE college_code ='" + strCollegeCode+ "';");
                rs = ps.executeQuery();
                tblForms.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e){
                f.displayError(e);
            }
        }
    }//GEN-LAST:event_cmbCollegeActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        resetButtons();
        removeError();
        setFormEditable(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnCancel.setEnabled(true);
        btnConfirm.setEnabled(true);
        cmbCollegeCode.setEnabled(true);
        clearForms();
        setFormEditable(true);
        setStatus("A");        
        setCmbCollegeCode(strDashLine);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        btnEdit.setEnabled(false);
        btnAdd.setEnabled(false);
        btnCancel.setEnabled(true);
        btnConfirm.setEnabled(true);
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        removeError();
        if(btnAdd.isEnabled() && isFieldValidated()){
            addCourse();
            
            return;
        }
        if(btnEdit.isEnabled() && isFieldValidated()){
            editCourse();
            return;
        }
        if(btnDelete.isEnabled()){
            deleteCourse();
            return;
        }
        
    }//GEN-LAST:event_btnConfirmActionPerformed

    public void addCourse(){
        String strStatus = f.toStatus(cmbStatus.getSelectedItem().toString());
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to add? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("INSERT INTO oop23.course VALUES ('"
                    + txtCourseCode.getText()
                    +"', '"+ txtDescription.getText()
                    +"', '"+ cmbCollegeCode.getSelectedItem().toString()    
                    +"', '"+ new SimpleDateFormat("yyyy-MM-dd").format(dtcDateOpened.getDate())
                    +"', '"+ new SimpleDateFormat("yyyy-MM-dd").format(dtcDateClosed.getDate())
                    +"', '"+ strStatus
                    +"')");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Addition is successful");
                resetButtons();
                setFormEditable(false);
                refreshTable();
            }catch(Exception e) {
                    f.displayError(e);
            }   
        }
        else {
            JOptionPane.showMessageDialog(null, "Addition is aborted");
        }
        
    }
    public void editCourse(){
        String strStatus = f.toStatus(cmbStatus.getSelectedItem().toString());
        
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to edit? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("UPDATE oop23.course SET description = '"
                    +txtDescription.getText()
                    +"', date_opened = '"+new SimpleDateFormat("yyyy-MM-dd").format(dtcDateOpened.getDate())
                    +"', date_closed = '"+new SimpleDateFormat("yyyy-MM-dd").format(dtcDateClosed.getDate())
                    +"', status = '"+ strStatus
                    +"', college_code = '"+ cmbCollegeCode.getSelectedItem().toString()
                    +"'"
                    +" WHERE course_code = '"+ txtCourseCode.getText()
                    +"'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "Edit is successful");
                resetButtons();
                setFormEditable(false);
                refreshTable();
            }catch(Exception e) {
                    f.displayError(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Edit is aborted");
        }
        
    }  
    public void deleteCourse(){
        int intAnswer = JOptionPane.showConfirmDialog(null, "Do you really want to delete? ", "confirm",JOptionPane.YES_NO_OPTION);
        if(intAnswer == 0){
            try{
                ps = conn.prepareStatement("DELETE FROM oop23.course"
                    +" WHERE course_code = '"
                    + txtCourseCode.getText()
                    +"'");   
                ps.execute();
                JOptionPane.showMessageDialog(null, "Deletion is successful");
                resetButtons();
                setFormEditable(false);
                clearForms();
                refreshTable();
            }catch(Exception e) {
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
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Course().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cmbCollege;
    private javax.swing.JComboBox<String> cmbCollegeCode;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser dtcDateClosed;
    private com.toedter.calendar.JDateChooser dtcDateOpened;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInvCollege;
    private javax.swing.JLabel lblInvCollegeCode;
    private javax.swing.JLabel lblInvCourse;
    private javax.swing.JLabel lblInvDateClose;
    private javax.swing.JLabel lblInvDateOpen;
    private javax.swing.JLabel lblInvDescription;
    private javax.swing.JLabel lblInvStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblForms;
    private javax.swing.JTextField txtCourseCode;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables
}
