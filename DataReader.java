package lp.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DataReader {

    private static final String DEFAULT_FILE_NAME = "default.csv";

    public List<String> get() {
        Optional<String> s = getFileName();
        return s.isPresent() ? getF(s.get()) : getR();
    }

    private Optional<String> getFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете име на файл: ");
        String str = scanner.nextLine();
        return (str.equals("")) ? Optional.empty() : Optional.of(str);
    }

    private List<String> getF(String fn) {
        File f = new File(fn);
        BufferedReader reader = null;
        List<String> employee = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(f));
            String emplString;
            while ((emplString = reader.readLine()) != null) {
                employee.add(emplString);
            }
            return employee;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private List<String> getR() {
        Scanner scanner = new Scanner(getClass().getResourceAsStream(DEFAULT_FILE_NAME));
        List<String> employee = new ArrayList<>();
        while (scanner.hasNextLine()) {
            employee.add(scanner.nextLine());
        }
        return employee;
    }

}
