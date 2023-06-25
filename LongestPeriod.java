package lp.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lp.employee.EmployeeData;
import lp.employee.EmployeeMatch;
import lp.employee.EmployeesProject;

public class LongestPeriod {

    public static void main(String[] args) {

        LongestPeriod m = new LongestPeriod();
        m.longestPeriod();
    }

    private void longestPeriod() {
     
        List<String> employee = new DataReader().get();
        List<EmployeeData> emlpList = new ArrayList<>();
        employee.forEach(c -> emlpList.add(stringToEmployee(c)));
        List<EmployeesProject> tList = this.assignmentMatch(emlpList);
        System.out.println("Common projects:");
        System.out.println("-------------------------------------------------------");
        System.out.println("Employee ID #1, Employee ID #2, Project ID, Days worked");
        System.out.println("-------------------------------------------------------");
        tList.forEach(c -> System.out.println(c));
        System.out.println("-------------------------------------------------------");

        Comparator<EmployeesProject> comp = Comparator.comparing(EmployeesProject::getPeriod);
        Optional<EmployeesProject> opEmployees = tList.stream().max(comp);
        String result = (opEmployees.isPresent()) ? (opEmployees.get()).get() : "";
        System.out.println("Longest period (Employee ID #1, Employee ID #2, Days worked : " + result);
    }

    private List<EmployeesProject> assignmentMatch(List<EmployeeData> emlpList) {
        EmployeeData[] emlpArr = emlpList.toArray(new EmployeeData[]{});
        List<EmployeesProject> emplMatchList = new ArrayList<>();
        int cnt = emlpArr.length;
        EmployeeMatch m = new EmployeeMatch();
        for (int i = 0; i < cnt - 1; i++) {
            Optional<EmployeesProject> c = m.compare(emlpArr[i], emlpArr, i);
            if (c.isPresent()) {
                EmployeesProject e = c.get();
                emplMatchList.forEach((EmployeesProject ep) -> overlap(ep, e));
                if (e.getTimes() == 1) {
                    emplMatchList.add(c.get());
                }
            }
        }
        return emplMatchList;
    }

    private void overlap(EmployeesProject e1, EmployeesProject e2) {
        if (e1.compareByProject(e1, e2)) {
            e1.extendP(e2.getPeriod());
            e2.extendT();
        }
    }

    private EmployeeData stringToEmployee(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] arr = s.split(",");
        EmployeeData empl = new EmployeeData(Integer.parseInt(arr[0].trim()), Integer.parseInt(arr[1].trim()),
                LocalDate.parse(arr[2].trim(), formatter),
                (arr[3].trim().equalsIgnoreCase("NULL")) ? LocalDate.now() : LocalDate.parse(arr[3].trim(), formatter));
        return empl;
    }
}
