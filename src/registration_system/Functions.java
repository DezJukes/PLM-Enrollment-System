package registration_system;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Functions {
    public boolean isValidCollegeCode(String strName) {
        return strName.matches("^[a-zA-Z0-9\\s]{1,10}$");
    }
    public boolean isValidGrade(String strGrd){
        return strGrd.matches("^\\d+\\.\\d{2}$");
    }
    public boolean isValidSY(String str){
        return str.matches("^\\d{4}-\\d{4}$");
    }
    public boolean isValidSem(String str){
        return str.matches("^[1-2]$");
    }
    public boolean isValidBlock(String str){
        return str.matches("^[A-Z]{2}\\d{2}$");
    }
    
    
    public boolean isValidDescription(String strName) {
        return strName.matches("^[a-zA-Z0-9\\s]{1,100}$");
    }
    public boolean isValidUnits(String strName) {
        return strName.matches("^[0-9]{1}$");
    }
    public boolean isValidCurriculum (String strName) {
        return strName.matches("^[0-9]{4}$");
    }
    public boolean isValidTime(String strTime){
        return strTime.matches("^(?:[0-9]|1\\d|2[0-3]):[0-5]\\d-(?:[0-9]|1\\d|2[0-3]):[0-5]\\d$");
    }
    public boolean isValidSequenceNo(String strSqn){
        return strSqn.matches("^[0-9]{1,2}$");
    }
    public boolean isValidEmail(String strEmail) {
        return strEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public boolean isValidContactNumber(String strContactNumber) {
        return strContactNumber.matches("^0\\d{10}$");
    } 
    public boolean isValidStudentNo(String strStudentNo) {
        return strStudentNo.matches("^\\d{4}-\\d{5}$");
    }
    public boolean isValidName(String strName) {
        return strName.matches("^[A-Za-z ]{1,50}$");
    }
    public void displayError(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    public boolean isEmpty(String input) {
        return input == null || Pattern.matches("\\s*", input);
    }
    public boolean isValidAddress(String strAddress){
        return strAddress == null || Pattern.matches("^[a-zA-Z0-9, ]{1,255}$", strAddress);
    }
    public boolean isValidEId(String strEId){
        return strEId == null || Pattern.matches("^E\\d{3}$", strEId);
    }
    
    public String toGender(String strGender){
        if(strGender.equals("Male")){
            return "M";
        } else if (strGender.equals("Female")){
            return "F";
        }
        return "Invalid";
    }
    public String toStatus(String strStatus){
        if(strStatus.equals("Active")){
            return "A";
        } else if (strStatus.equals("Inactive")){
            return "I";
        }
        return "Invalid";
    }
    
    public String toDay(String strDay){
    if(strDay.equals("Sunday")){
        return "Su";
    } else if (strDay.equals("Monday")){
        return "M";
    } else if (strDay.equals("Tuesday")){
        return "T";
    } else if (strDay.equals("Wednesday")){
        return "W";
    } else if (strDay.equals("Thursday")){
        return "Th";
    } else if (strDay.equals("Friday")){
        return "F";
    } else if (strDay.equals("Saturday")){
        return "S";
    }
    return "Invalid";
    }
    public String toType(String strType){
        if(strType.equals("Face to Face")){
            return "FTF";
        } else if(strType.equals("Online")){
            return "OL";
        }
        return "Invalid";
    }
    
}
