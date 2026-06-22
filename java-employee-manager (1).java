import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Main test = new Main();
        if(test.calculate(test.generateList()) == 5582086.0) {
            System.out.println("Everything goes fine");
        } else {
            System.out.println("Something went wrong");
        }
    }
    private List generateList() {
        List l = new ArrayList();
        for (int i = 0; i < 10; i++) {
            l.add(new Manager(50000, 10, "HR"));
            l.add(new Employee(30000, 5));
        }
        return l;
    }
    /////////////////////////////////////////////////    // Exercise starts here    /////////////////////////////////////////////////
    class Employee {
        private int salary;
        private int tenure; // years in the company
        public Employee(int salary, int tenure) {
            this.salary = salary;
            this.tenure = tenure;
        }
        public int getSalary() {
            return salary;
        }
        public int getTenure() {
            return tenure;
        }
    }
    class Manager {
        private int salary;
        private int tenure; // years in the company
        private String department;
        public Manager(int salary, int tenure, String department) {
            this.salary = salary;
            this.tenure = tenure;
            this.department = department;
        }
        public int getSalary() {
            return salary;
        }
        public int getTenure() {
            return tenure;
        }
        public String getDepartment() {
            return department;
        }
    }
    /**     
     * This method calculates the provision for next year
     * @param l List with current Employees
     **/
    public double calculate(List l) {
        double sum = 0; // total to provision
        double departmentFactor = 1;
        if (l != null) {
            // this part calculate the amount due the salaries, etc... variable expenses
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i) instanceof Manager) {
                    Manager manager = (Manager)l.get(i);
                    if (manager.getDepartment() == "HR") {
                        departmentFactor = 1.2;
                    } else if (manager.getDepartment() == "Finance") {
                        departmentFactor = 1.5;
                    } else {
                        departmentFactor = 1;
                    }
                    if (manager.getTenure() < 3) {
                        sum = sum + (manager.getSalary() * departmentFactor);
                    } else if (manager.getTenure() < 10) {
                        sum = sum + (manager.getSalary() * 1.2 * departmentFactor);
                    } else {
                        sum = sum + (manager.getSalary() * 1.5 * departmentFactor);
                    }
                } else {
                    Employee e = (Employee)l.get(i);
                    if (e.getTenure() < 3) {
                        sum = sum + e.getSalary();
                    } else if (e.getTenure() < 10) {
                        sum = sum + (e.getSalary() * 1.2);
                    } else {
                        sum = sum + (e.getSalary() * 1.5);
                    }
                }
            }
            System.out.println("Total before other expenses: " + sum);
            // this part calculate the fix expenses independently of the type of employee
            sum += l.size() * 100;
            System.out.println("Total before rent: " + sum);
            sum += 4320086; // rent
        }
        System.out.println("The total is: " + sum);
        return sum;
    }
}
