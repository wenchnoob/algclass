package Monitors;

public class TABrain {

    private int capacity, numOfStudentsHelping = 0;

    public TABrain(int capacity) {
        this.capacity = capacity;
    }

    public synchronized int getNumOfStudentsHelping() {
        return numOfStudentsHelping;
    }

    public synchronized void takeAStudent(Student student) {
        // if (numOfStudentsHelping < capacity)
        while ( numOfStudentsHelping >= capacity ) {
            try {
                System.out.println(student.getStudentName() + " is waiting for a spot with the TA.");
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        numOfStudentsHelping++;
        System.out.printf("%s is with the TA.%n", student.getStudentName());
    }

    public synchronized void releaseAStudent(TA t) {
        numOfStudentsHelping--;
        System.out.printf("%s is done helping a student.%n", t.getTAName());
        notify();
    }



}
