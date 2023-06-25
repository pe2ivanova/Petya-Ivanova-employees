package lp.employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class EmployeeMatch {
    
    public Optional<EmployeesProject> compare(EmployeeData a, EmployeeData[] arr, int index) {
        long maxDays = 0;
        Optional<EmployeesProject> maxEmpl =Optional.empty();
        int cnt = arr.length;
        for (int j = index + 1; j < cnt; j++) {
            Optional<EmployeesProject> c = combine(a, arr[j]);
            if (c.isPresent() ) {
                long cPeriod =  c.get().getPeriod();
                if (cPeriod > maxDays) {
                    maxDays = cPeriod;
                    maxEmpl = c;
                }
            }
        }
        return maxEmpl;
    }

    private long getCommonPeriod(LocalDate aFrom, LocalDate aTo, LocalDate bFrom, LocalDate bTo) {
        if (aTo.isBefore(bFrom) || bTo.isBefore(aFrom)) {
            return 0;
        }
        LocalDate dFrom = aFrom.isAfter(bFrom) ? aFrom : bFrom;
        LocalDate dTo = aTo.isBefore(bTo) ? aTo : bTo;
        return ChronoUnit.DAYS.between(dFrom, dTo) + 1;
    }

    public long getCommonPeriodEmpl(EmployeeData a, EmployeeData b) {
        return getCommonPeriod(a.getDateFrom(), a.getDateTo(), b.getDateFrom(), b.getDateTo());
    }

    public Optional<EmployeesProject> combine(EmployeeData a, EmployeeData b) {
        if ((a.getEmpID() != b.getEmpID()) && (a.getProjectID() == b.getProjectID())) {
            return Optional.of(new EmployeesProject(a.getEmpID(), b.getEmpID(), a.getProjectID(), getCommonPeriodEmpl(a, b)));  
        }
        return Optional.empty();
    }
}
