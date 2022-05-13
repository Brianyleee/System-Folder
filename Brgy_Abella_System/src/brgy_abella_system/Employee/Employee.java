
package brgy_abella_system.Employee;

import java.time.LocalDate;
import javafx.scene.control.Button;

public class Employee {
    private String id,firstName,middleName,lastName,Position,Status,DOB,Resigned,Hired;
    private int Access;

    public Employee(String id, String firstName, String middleName, String lastName, String Position) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.Position = Position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getResigned() {
        return Resigned;
    }

    public void setResigned(String Resigned) {
        this.Resigned = Resigned;
    }

    public String getHired() {
        return Hired;
    }

    public void setHired(String Hired) {
        this.Hired = Hired;
    }

 

    public int getAccess() {
        return Access;
    }

    public void setAccess(int Access) {
        this.Access = Access;
    }
    
    
}
