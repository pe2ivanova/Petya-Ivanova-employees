package lp.employee;

import java.time.LocalDate;

public class EmployeeData {

    private final int empID;
    private final int projectID;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public EmployeeData(int pEmplId, int pProjectID, LocalDate pDateFrom, LocalDate pDateTo) {
        empID = pEmplId;
        projectID = pProjectID;
        dateFrom = pDateFrom;
        dateTo = pDateTo;
    }
    
    public int getEmpID() {
        return empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return "EmployeeData{" + "empID=" + empID + ", projectID=" + projectID + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + '}';
    }

}
