package Monitors;

public class Student extends Thread {

    private final String studentName;
    private final TABrain brain;

    public Student(TABrain brain, String name) {
        this.brain = brain;
        this.studentName = name;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void getAdvice() {
        System.out.println(studentName + " asks for help!");
    }

    public void run() {
        brain.takeAStudent(this);
        getAdvice();
    }
}
