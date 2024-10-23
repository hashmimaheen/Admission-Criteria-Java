import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String formNumber;
    String name;
    String fatherName;
    double matricMarks;
    double fscMarks;
    double testMarks;
    double interviewMarks;

    public Student(String formNumber, String name, String fatherName, double matricMarks, double fscMarks) {
        this.formNumber = formNumber;
        this.name = name;
        this.fatherName = fatherName;
        this.matricMarks = matricMarks;
        this.fscMarks = fscMarks;
        this.testMarks = 0;
        this.interviewMarks = 0;
    }

    double calculateScore() {
        return (matricMarks * 0.1) + (fscMarks * 0.6) + (testMarks * 0.3) + (interviewMarks * 0.1);
    }
}
public class Main {
    static List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Form Entry");
            System.out.println("2. Test Score Entry");
            System.out.println("3. Display Test List");
            System.out.println("4. Display Final Merit List");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            System.out.println(choice);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    formEntry();
                    break;
                case 2:
                    testScoreEntry();
                    break;
                case 3:
                    displayTestList();
                    break;
                case 4:
                    displayFinalMeritList();
                    break;
                case 5:
                    System.out.println("if you want to exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    static void formEntry() {
        System.out.print("Enter Form Number: ");
        String formNumber = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Father's Name: ");
        String fatherName = scanner.nextLine();
        System.out.print("Enter Matric Marks(out of 1100): ");
        double matricMarks = scanner.nextDouble();
        System.out.print("Enter FSC Marks(out of 1100): ");
        double fscMarks = scanner.nextDouble();
        scanner.nextLine();

        if (fscMarks < 550) {
            System.out.println("Student is not eligible for admission.");
            return;

        }
        students.add(new Student(formNumber, name, fatherName, matricMarks, fscMarks));
        System.out.println("Form submitted successfully.");
    }

    static void testScoreEntry() {
        System.out.print("Enter Form Number: ");
        String formNumber = scanner.nextLine();
        for (Student student : students) {
            if (student.formNumber.equals(formNumber)) {
                System.out.print("Enter Test Marks:(out of 100) ");
                student.testMarks = scanner.nextDouble();
                System.out.print("Enter Interview Marks:(out of 100) ");
                student.interviewMarks = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Test scores updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void displayTestList() {
        int rollNumber = 1;
        System.out.println("Eligible students for entry test");
        for (Student student : students) {
            if (student.fscMarks >= 550) {
                System.out.println("Roll Number: " + rollNumber + ", Form Number: " + student.formNumber + ", Name: " + student.name);
                rollNumber++;
            }
        }

    }

    static void displayFinalMeritList() {
        students.sort((s1, s2) -> Double.compare(s2.calculateScore(), s1.calculateScore()));
        int rank = 1;
        System.out.println("Final merit list(Top 200)");
        for (Student student : students) {
            if (student.fscMarks >= 550 && student.testMarks != 0) {
                System.out.println("Rank: " + rank + ", Form Number: " + student.formNumber + ", Name: " + student.name + ", Score: " + student.calculateScore());
                rank++;
                if (rank > 200)
                    break;
            }
        }

    }
}
