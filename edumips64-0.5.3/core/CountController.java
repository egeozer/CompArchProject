package core;

public class CountController {
    private static int takenCount = 0;
    private static int notTakenCount = 0;
    private static int nBitPredictor = 2; //default is 2, set to n-bit >0
    private static boolean predictTaken = false;
    private static int mispredictCount = 0;


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


    public static void incrementMispredictCount(){
        mispredictCount++;
    }

    public static int getMispredictCount(){
        return mispredictCount;
    }

    public static void resetMispredictCount(){
        mispredictCount = 0;
    }

    public static boolean isPredictTaken(){
        return predictTaken;
    }

    public static boolean isMispredictReached(){
        if (mispredictCount == nBitPredictor){
            return true;
        }
        else{
            return false;
        }
    }

    public static void changePrediction(){
        predictTaken = !predictTaken;
    }
}
