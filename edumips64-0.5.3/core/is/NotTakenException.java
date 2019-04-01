package core.is;

import core.CountController;
import edumips64.core.CPU;

import java.util.logging.Logger;

public class NotTakenException extends Exception {

    private static final Logger logger = Logger.getLogger(CPU.class.getName());

    public NotTakenException(){
        // Check what type of prediction since Not Taken
        if(CountController.isPredictTaken()){

            CountController.incrementMispredictCount();
            logger.info("Increment misprediction to " + CountController.getMispredictCount());

            if(CountController.isMispredictReached()){ //Error catch condition
                CountController.changePrediction();
                logger.info("Changing Prediction to " + CountController.isPredictTaken());
            }

        }
    }
}
