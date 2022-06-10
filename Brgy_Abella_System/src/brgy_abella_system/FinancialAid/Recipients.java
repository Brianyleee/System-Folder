
package brgy_abella_system.FinancialAid;

public class Recipients {
    private String Matri,Prevgrade,Fname,Mname,Lname,Recipient_Id,PlaceOB,Address,Contact_Number,DOB,Sex,Zone_Org,School,EstAnnualIncome,
            AvgMonthlyIncome,EnrollmentFeePaid,Sem_Estimate,Course,SchoolOrg,Fname_F,Mname_F,Lname_F,ContactNumber_F,Occupation_F,
            Occupation_M,Contactnumber_M,Fname_M,Mname_M,Lname_M,FullNameReci,DayApplied,CollegeLevel,Status;
    private int Age,Age_F,Age_M,Zone;

    public Recipients(String Recipient_Id,String FullNameReci,String DayApplied,String DOB,String CollegeLevel){
        this.Recipient_Id = Recipient_Id;
        this.FullNameReci = FullNameReci;
        this.DayApplied = DayApplied;
        this.DOB = DOB;
        this.CollegeLevel = CollegeLevel;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    
    public String getDayApplied() {
        return DayApplied;
    }

    public void setDayApplied(String DayApplied) {
        this.DayApplied = DayApplied;
    }

    public String getCollegeLevel() {
        return CollegeLevel;
    }

    public void setCollegeLevel(String CollegeLevel) {
        this.CollegeLevel = CollegeLevel;
    }
   
    public void setFullName (){
        FullNameReci = Lname + ", "+ Fname+" "+ Lname.charAt(0)+".";
    }

    public String getFullNameReci() {
        return FullNameReci;
    }
    
    
    public String getMatri() {
        return Matri;
    }

    public void setMatri(String Matri) {
        this.Matri = Matri;
    }

    public String getPrevgrade() {
        return Prevgrade;
    }

    public void setPrevgrade(String Prevgrade) {
        this.Prevgrade = Prevgrade;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String Mname) {
        this.Mname = Mname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getRecipient_Id() {
        return Recipient_Id;
    }

    public void setRecipient_Id(String Recipient_Id) {
        this.Recipient_Id = Recipient_Id;
    }

    public String getPlaceOB() {
        return PlaceOB;
    }

    public void setPlaceOB(String PlaceOB) {
        this.PlaceOB = PlaceOB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(String Contact_Number) {
        this.Contact_Number = Contact_Number;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getZone_Org() {
        return Zone_Org;
    }

    public void setZone_Org(String Zone_Org) {
        this.Zone_Org = Zone_Org;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String School) {
        this.School = School;
    }

    public String getEstAnnualIncome() {
        return EstAnnualIncome;
    }

    public void setEstAnnualIncome(String EstAnnualIncome) {
        this.EstAnnualIncome = EstAnnualIncome;
    }

    public String getAvgMonthlyIncome() {
        return AvgMonthlyIncome;
    }

    public void setAvgMonthlyIncome(String AvgMonthlyIncome) {
        this.AvgMonthlyIncome = AvgMonthlyIncome;
    }

    public String getEnrollmentFeePaid() {
        return EnrollmentFeePaid;
    }

    public void setEnrollmentFeePaid(String EnrollmentFeePaid) {
        this.EnrollmentFeePaid = EnrollmentFeePaid;
    }

    public String getSem_Estimate() {
        return Sem_Estimate;
    }

    public void setSem_Estimate(String Sem_Estimate) {
        this.Sem_Estimate = Sem_Estimate;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getSchoolOrg() {
        return SchoolOrg;
    }

    public void setSchoolOrg(String SchoolOrg) {
        this.SchoolOrg = SchoolOrg;
    }

    public String getFname_F() {
        return Fname_F;
    }

    public void setFname_F(String Fname_F) {
        this.Fname_F = Fname_F;
    }

    public String getMname_F() {
        return Mname_F;
    }

    public void setMname_F(String Mname_F) {
        this.Mname_F = Mname_F;
    }

    public String getLname_F() {
        return Lname_F;
    }

    public void setLname_F(String Lname_F) {
        this.Lname_F = Lname_F;
    }

    public String getContactNumber_F() {
        return ContactNumber_F;
    }

    public void setContactNumber_F(String ContactNumber_F) {
        this.ContactNumber_F = ContactNumber_F;
    }

    public String getOccupation_F() {
        return Occupation_F;
    }

    public void setOccupation_F(String Occupation_F) {
        this.Occupation_F = Occupation_F;
    }

    public String getOccupation_M() {
        return Occupation_M;
    }

    public void setOccupation_M(String Occupation_M) {
        this.Occupation_M = Occupation_M;
    }

    public String getContactnumber_M() {
        return Contactnumber_M;
    }

    public void setContactnumber_M(String Contactnumber_M) {
        this.Contactnumber_M = Contactnumber_M;
    }

    public String getFname_M() {
        return Fname_M;
    }

    public void setFname_M(String Fname_M) {
        this.Fname_M = Fname_M;
    }

    public String getMname_M() {
        return Mname_M;
    }

    public void setMname_M(String Mname_M) {
        this.Mname_M = Mname_M;
    }

    public String getLname_M() {
        return Lname_M;
    }

    public void setLname_M(String Lname_M) {
        this.Lname_M = Lname_M;
    }

    public int getZone() {
        return Zone;
    }

    public void setZone(int Zone) {
        this.Zone = Zone;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getAge_F() {
        return Age_F;
    }

    public void setAge_F(int Age_F) {
        this.Age_F = Age_F;
    }

    public int getAge_M() {
        return Age_M;
    }

    public void setAge_M(int Age_M) {
        this.Age_M = Age_M;
    }
    
    
    
    
    
    
}
