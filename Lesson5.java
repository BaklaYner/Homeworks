/**
 * Java level 1.
 * Homework for lesson 5.
 *
 * @author Bogdanov Anton
 * @version dated May 23, 2018
 */
public class Lesson5 {
    public static void main(String[] args) {
        // Task 1.4
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Ivanov Ivan", "builder", "IvanovI@mail.ru",
                "8(911)111-11-11", 40000, 23);
        employees[1] = new Employee("Petrov Petr", "bricklayer", "PetrovP@gmail.com",
                "8(921)222-22-22", 50000, 30);
        employees[2] = new Employee("Sidorov Sidor", "foreman", "SidorovS@list.ru",
                "8(911)333-33-33", 60000, 43);
        employees[3] = new Employee("Fedorov Fedor", "architect", "FedorovF@gmail.com",
                "8(921)444-44-44", 150000, 48);
        employees[4] = new Employee("Miller Aleksey Borisovich", "CEO \"Газпром\"",
                "MillerAB@gazprom.ru", "8(812)777-77-77", 90959562, 56);

        // Task 1.5
        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                employee.printInfo();
            }
        }
    }
}