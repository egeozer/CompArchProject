package core;

import edumips64.utils.Config;

public class PredictionController {
    private static int nBitPredictor = (Integer)Config.get("n_predictor"); //default is 2, set to n-bit >0

    private final static boolean DEFAULT_PREDICT_TAKEN = false;
    private static boolean predictTaken = false;
    private static int mispredictCount = 0;
    private static int offsetPC = 0;
    private static int mispredictStalls = 0;
    private static int takenStalls = 0;
    private static int branchCount = 0;

    /* -------------- Prediction Handling ------------------ */
    public static void incrementMispredictCount() {
        mispredictCount++;
        mispredictStalls++;
    }

    public static void resetPredictTaken(){
        predictTaken = DEFAULT_PREDICT_TAKEN;
    }

    public static boolean isMispredictReached() {
        return (mispredictCount == nBitPredictor);
    }

    public static void changePrediction() {
        predictTaken = !predictTaken;
        resetMispredictCount();
    }

    public static boolean isPredictTaken() {
        return predictTaken;
    }

    /* -------------- Stalls  Handling ------------------ */
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

    /* -------------- Offset Handling ------------------ */
    public static void setOffsetPC(int offset) {
        offsetPC = offset;
    }

    public static int getOffsetPC() {
        return offsetPC;
    }

    /* -------------- nBit prediction ------------------ */
    public static int getnBitPredictor() {
        return nBitPredictor;
    }
    public static void updateNBitPredictor() {
        nBitPredictor = (Integer) Config.get("n_predictor");
    }

    /* -------------- Conditional Branch Instructions Counter ------------------ */
    public static void incrementBranchCount() {
        branchCount++;
    }

    public static int getBranchCount() {
        return branchCount;
    }

    public static void resetBranchCount(){
        branchCount = 0;
    }
}
