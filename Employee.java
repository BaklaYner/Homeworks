/**
 * Java level 1.
 * Homework for lesson 5.
 *
 * @author Bogdanov Anton
 * @version dated May 22, 2018
 */
// Task 1.1
public class Employee {
    private String fullName;
    private String position;
    private String email, phoneNumber;
    private int salary, age;

    // Task 1.2
    public Employee(String fullName, String position, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    // Task 1.3
    public void printInfo() {
        System.out.println(fullName + " - " + position + ".\nE-mail address: " + email + ". Phone number: " + phoneNumber +
                ".\n" + age + " years old. Salary: " + salary + " rubles per month.\n");
    }

    public int getAge() {
        return age;
    }
}