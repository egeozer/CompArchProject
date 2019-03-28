package core.is;

import core.CountController;
import edumips64.core.CPU;

import java.util.logging.Logger;

public class NotTakenException extends Exception {

    private static final Logger logger = Logger.getLogger(CPU.class.getName());

    public NotTakenException(){
        //CountController.incrementNotTakenCount();

        // Check what type of prediction since Not Taken
        if(CountController.isPredictTaken()){
            if(CountController.isMispredictReached()){
                CountController.changePrediction();
                logger.info("Changing Prediction to " + CountController.isPredictTaken());
            }
            else{
                CountController.incrementMispredictCount();
                logger.info("Increment misprediction to " + CountController.getMispredictCount());
            }
        }
    }
}
