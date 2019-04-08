package core;

public class CountController {
    private static int nBitPredictor = 2; //default is 2, set to n-bit >0
    private static boolean predictTaken = false;
    private final static boolean DEFAULT_PREDICT_TAKEN = false;
    private static int mispredictCount = 0;
    private static int offsetPC = 0;
    private static int mispredictStalls = 0;
    private static int takenStalls = 0;

    public static void incrementMispredictCount() {
        mispredictCount++;
        mispredictStalls++;
    }
    public static void resetPredictTaken(){
        predictTaken = DEFAULT_PREDICT_TAKEN;
    }

    public static int getMispredictionStalls() {
        return mispredictStalls;
    }
    public static int getTakenStalls() {
        return takenStalls;
    }

    public static int getMispredictCount() {
        return mispredictCount;
    }

    public static void resetMispredictCount() {
        mispredictCount = 0;
    }
    public static void resetMispredictStalls() {
        mispredictStalls = 0;
    }

    public static boolean isPredictTaken() {
        return predictTaken;
    }

    public static boolean isMispredictReached() {
        return (mispredictCount == nBitPredictor);
    }

    public static void changePrediction() {
        predictTaken = !predictTaken;
        resetMispredictCount();
    }

    public static void setOffsetPC(int offset) {
        offsetPC = offset;
    }

    public static int getOffsetPC() {
        return offsetPC;
    }
}
