package core;

public class CountController {
    private static int takenCount = 0;
    private static int notTakenCount = 0;
    private int nBitPredictor = 2; //default is 2, set to n-bit >0


    public static void incrementTakenCount() {
        takenCount++;
    }

    public static void incrementNotTakenCount() {
        notTakenCount--;
    }

    public static int getTakenCount() {
        return takenCount;
    }

    public static int getNotTakenCount() {
        return notTakenCount;
    }

    public boolean isTakenThresholdReached() {
        return (takenCount == nBitPredictor);
    }

    public boolean isNotTakenThresholdReached() {
        return (notTakenCount == nBitPredictor);
    }
}
