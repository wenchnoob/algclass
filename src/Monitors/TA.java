package Monitors;

public class TA extends Thread {

    private TABrain brain;
    private final String TAName;

    public TA(TABrain brain, String TAName) {
        this.brain = brain;
        this.TAName = TAName;
    }

    public TABrain getBrain() {
        return this.brain;
    }
    public String getTAName() {
        return this.TAName;
    }

    public void giveAdvice() {
        System.out.println(TAName + " offers: Have you thought about majoring in global business?");
    }

    public void run() {
        while (brain.getNumOfStudentsHelping() > 0) {
            giveAdvice();
            brain.releaseAStudent(this);
        }
    }
}
