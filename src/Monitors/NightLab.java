package Monitors;

public class NightLab {

    public static void main(String[] args) {
        TABrain brain = new TABrain(3);
        int studentCount = 20;
        Thread[] students = new Thread[studentCount];
        TA ta = new TA(brain, "Jerry");

        for (int i = 0; i < studentCount; i++) {
            students[i] = new Student(brain, "student_"+i);
        }

        for (int i = 0; i < studentCount; i++) {
            students[i].start();
        }
        ta.start();
    }

}
