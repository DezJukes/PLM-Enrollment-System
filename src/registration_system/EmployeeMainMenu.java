package registration_system;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class EmployeeMainMenu extends javax.swing.JFrame {
    //ConnectDB
    String strLogin = "";
    Connection conn = ConnectDB.Connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    Student st = new Student();
    Employee em = new Employee();
    Course c = new Course();
    College college = new College();
    Subject subject= new Subject();
    SubjectSchedule ss = new SubjectSchedule();
    Grade g = new Grade();
    Block b = new Block();
    Semester s = new Semester();
    SchoolYear sy = new SchoolYear();
    
    public void displayError(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    
    public void resetButtons(int intIndex){
        if(intIndex != 1)
            btnStudent.setSelected(false);
        if(intIndex != 2)
            btnEmployee.setSelected(false);
        if(intIndex != 3)
            btnCourse.setSelected(false);
        if(intIndex != 4)
            btnCollege.setSelected(false);
        if(intIndex != 5)
            btnSubject.setSelected(false);
        if(intIndex != 6)
            btnSemester.setSelected(false);
        if(intIndex != 7)
            btnSY.setSelected(false);
        if(intIndex != 8)
            btnSchedule.setSelected(false);
        if(intIndex != 9)
            btnBlock.setSelected(false);
        if(intIndex != 10)
            btnGrade.setSelected(false);
        //
        if(intIndex == 1)
            btnStudent.setSelected(true);
        if(intIndex == 2)
            btnEmployee.setSelected(true);
        if(intIndex == 3)
            btnCourse.setSelected(true);
        if(intIndex == 4)
            btnCollege.setSelected(true);
        if(intIndex == 5)
            btnSubject.setSelected(true);
        if(intIndex == 6)
            btnSemester.setSelected(true);
        if(intIndex == 7)
            btnSY.setSelected(true);
        if(intIndex == 8)
            btnSchedule.setSelected(true);
        if(intIndex == 9)
            btnBlock.setSelected(true);
        if(intIndex == 10)
            btnGrade.setSelected(true);
    }
    
    
    
    
    public EmployeeMainMenu() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBg = new javax.swing.JPanel();
        pnlSideBar = new javax.swing.JPanel();
        btnCollege = new javax.swing.JToggleButton();
        btnEmployee = new javax.swing.JToggleButton();
        btnSubject = new javax.swing.JToggleButton();
        btnSemester = new javax.swing.JToggleButton();
        btnSY = new javax.swing.JToggleButton();
        btnSchedule = new javax.swing.JToggleButton();
        btnBlock = new javax.swing.JToggleButton();
        btnCourse = new javax.swing.JToggleButton();
        btnStudent = new javax.swing.JToggleButton();
        btnGrade = new javax.swing.JToggleButton();
        pnlTitleBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblStudentNo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlBg.setBackground(new java.awt.Color(204, 255, 204));
        pnlBg.setPreferredSize(new java.awt.Dimension(1366, 768));
        pnlBg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSideBar.setBackground(new java.awt.Color(204, 204, 204));
        pnlSideBar.setOpaque(false);

        btnCollege.setBackground(new java.awt.Color(0, 0, 0));
        btnCollege.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCollege.setForeground(new java.awt.Color(255, 255, 255));
        btnCollege.setText("College");
        btnCollege.setBorderPainted(false);
        btnCollege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollegeActionPerformed(evt);
            }
        });

        btnEmployee.setBackground(new java.awt.Color(0, 0, 0));
        btnEmployee.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEmployee.setForeground(new java.awt.Color(255, 255, 255));
        btnEmployee.setText("Employee");
        btnEmployee.setBorderPainted(false);
        btnEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeActionPerformed(evt);
            }
        });

        btnSubject.setBackground(new java.awt.Color(0, 0, 0));
        btnSubject.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSubject.setForeground(new java.awt.Color(255, 255, 255));
        btnSubject.setText("Subject");
        btnSubject.setBorderPainted(false);
        btnSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubjectActionPerformed(evt);
            }
        });

        btnSemester.setBackground(new java.awt.Color(0, 0, 0));
        btnSemester.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSemester.setForeground(new java.awt.Color(255, 255, 255));
        btnSemester.setText("Semester");
        btnSemester.setBorderPainted(false);
        btnSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemesterActionPerformed(evt);
            }
        });

        btnSY.setBackground(new java.awt.Color(0, 0, 0));
        btnSY.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSY.setForeground(new java.awt.Color(255, 255, 255));
        btnSY.setText("School Year");
        btnSY.setBorderPainted(false);
        btnSY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSYActionPerformed(evt);
            }
        });

        btnSchedule.setBackground(new java.awt.Color(0, 0, 0));
        btnSchedule.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSchedule.setForeground(new java.awt.Color(255, 255, 255));
        btnSchedule.setText("Schedule");
        btnSchedule.setBorderPainted(false);
        btnSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleActionPerformed(evt);
            }
        });

        btnBlock.setBackground(new java.awt.Color(0, 0, 0));
        btnBlock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBlock.setForeground(new java.awt.Color(255, 255, 255));
        btnBlock.setText("Block");
        btnBlock.setBorderPainted(false);
        btnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockActionPerformed(evt);
            }
        });

        btnCourse.setBackground(new java.awt.Color(0, 0, 0));
        btnCourse.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCourse.setForeground(new java.awt.Color(255, 255, 255));
        btnCourse.setText("Course");
        btnCourse.setBorderPainted(false);
        btnCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseActionPerformed(evt);
            }
        });

        btnStudent.setBackground(new java.awt.Color(0, 0, 0));
        btnStudent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnStudent.setForeground(new java.awt.Color(255, 255, 255));
        btnStudent.setText("Student");
        btnStudent.setBorderPainted(false);
        btnStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentActionPerformed(evt);
            }
        });

        btnGrade.setBackground(new java.awt.Color(0, 0, 0));
        btnGrade.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGrade.setForeground(new java.awt.Color(255, 255, 255));
        btnGrade.setText("Grade");
        btnGrade.setBorderPainted(false);
        btnGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGradeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSideBarLayout = new javax.swing.GroupLayout(pnlSideBar);
        pnlSideBar.setLayout(pnlSideBarLayout);
        pnlSideBarLayout.setHorizontalGroup(
            pnlSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBlock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCollege, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSemester, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSY, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(btnSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSideBarLayout.setVerticalGroup(
            pnlSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideBarLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pnlBg.add(pnlSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 270, 690));

        pnlTitleBar.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pamantasan ng Lungsod ng Maynila - Management System");

        lblStudentNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStudentNo.setForeground(new java.awt.Color(255, 255, 255));
        lblStudentNo.setText("E000");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Logged in as Employee no. :");

        btnLogout.setBackground(new java.awt.Color(204, 0, 0));
        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleBarLayout = new javax.swing.GroupLayout(pnlTitleBar);
        pnlTitleBar.setLayout(pnlTitleBarLayout);
        pnlTitleBarLayout.setHorizontalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleBarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        pnlTitleBarLayout.setVerticalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleBarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStudentNo)
                        .addComponent(jLabel6)
                        .addComponent(btnLogout))
                    .addComponent(jLabel1))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlBg.add(pnlTitleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registration_system/bg_plm.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        pnlBg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-540, 50, 830, 710));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registration_system/bg_plm_2.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        pnlBg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        //strAccount.setText(strLogin);
        lblStudentNo.setText(strLogin);
    }//GEN-LAST:event_formWindowActivated

    private void btnCollegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollegeActionPerformed
        // TODO add your handling code here:
        resetButtons(4);
        college.setVisible(true);
    }//GEN-LAST:event_btnCollegeActionPerformed

    private void btnEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeActionPerformed
        // TODO add your handling code here:
        resetButtons(2);
        em.setVisible(true);
    }//GEN-LAST:event_btnEmployeeActionPerformed

    private void btnSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubjectActionPerformed
        // TODO add your handling code here:
        resetButtons(5);
        subject.setVisible(true);
        
    }//GEN-LAST:event_btnSubjectActionPerformed

    private void btnSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemesterActionPerformed
        // TODO add your handling code here:
        resetButtons(6);
        s.setVisible(true);
    }//GEN-LAST:event_btnSemesterActionPerformed

    private void btnSYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSYActionPerformed
        // TODO add your handling code here:
        resetButtons(7);
        sy.setVisible(true);
    }//GEN-LAST:event_btnSYActionPerformed

    private void btnScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleActionPerformed
        // TODO add your handling code here:
        resetButtons(8);
        ss.setVisible(true);
    }//GEN-LAST:event_btnScheduleActionPerformed

    private void btnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockActionPerformed
        // TODO add your handling code here:
        resetButtons(9);
        b.setVisible(true);
    }//GEN-LAST:event_btnBlockActionPerformed

    private void btnCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCourseActionPerformed
        // TODO add your handling code here:
        resetButtons(3);
        c.setVisible(true);
    }//GEN-LAST:event_btnCourseActionPerformed

    private void btnStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentActionPerformed
        // TODO add your handling code here:
        resetButtons(1);
        st.setVisible(true);
        
    }//GEN-LAST:event_btnStudentActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGradeActionPerformed
        // TODO add your handling code here:
        g.setVisible(true);
        resetButtons(10);
    }//GEN-LAST:event_btnGradeActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        lblStudentNo.setText(strLogin);
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(EmployeeMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBlock;
    private javax.swing.JToggleButton btnCollege;
    private javax.swing.JToggleButton btnCourse;
    private javax.swing.JToggleButton btnEmployee;
    private javax.swing.JToggleButton btnGrade;
    private javax.swing.JButton btnLogout;
    private javax.swing.JToggleButton btnSY;
    private javax.swing.JToggleButton btnSchedule;
    private javax.swing.JToggleButton btnSemester;
    private javax.swing.JToggleButton btnStudent;
    private javax.swing.JToggleButton btnSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblStudentNo;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPanel pnlSideBar;
    private javax.swing.JPanel pnlTitleBar;
    // End of variables declaration//GEN-END:variables
}
