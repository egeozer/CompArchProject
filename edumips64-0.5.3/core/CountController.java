package core;

public class CountController {
    private static int nBitPredictor = 2; //default is 2, set to n-bit >0
    private static boolean predictTaken = true;
    private static int mispredictCount = 0;

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
        return (mispredictCount == nBitPredictor);
    }

    public static void changePrediction(){
        predictTaken = !predictTaken;
        resetMispredictCount();
    }
}
