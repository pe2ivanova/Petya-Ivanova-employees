package lp.employee;

import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeesProject {

    private final int empID_1;
    private final int empID_2;
    private final int projectID;
    private long period;
    private int times;

    public EmployeesProject(int id1, int id2, int prId, long pperiod) {
        this.times = 1;
        this.empID_1 = id1;
        this.empID_2 = id2;
        this.projectID = prId;
        this.period = pperiod;
    }

    public long getPeriod() {
        return period;
    }

    public long getTimes() {
        return times;
    }

    public void extendP(long p) {
        period = period + p;
    }

    public void extendT() {
        times = times + 1;
    }

    public boolean compareByProject(EmployeesProject a, EmployeesProject b) {
        return (a.empID_1 == b.empID_1 && a.empID_2 == b.empID_2 && a.projectID == b.projectID);
    }

    public String get() {
        return String.valueOf(empID_1).concat(", ").concat(String.valueOf(empID_2))
                .concat(", ").concat(String.valueOf(period));
    }

    

    @Override
    public String toString() {
        return Stream.of(String.valueOf(empID_1),String.valueOf(empID_2),String.valueOf(projectID), String.valueOf(period))
                .collect(Collectors.joining(", "));
    }
}
