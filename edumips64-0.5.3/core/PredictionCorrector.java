package core;

import edumips64.core.BitSet64;
import edumips64.core.CPU;
import edumips64.core.IrregularWriteOperationException;
import edumips64.core.Register;
import edumips64.core.is.InstructionsUtils;
import edumips64.core.is.JumpException;
import edumips64.core.is.TwosComplementSumException;
import edumips64.utils.IrregularStringOfBitsException;

import java.util.logging.Logger;

public class PredictionCorrector {

    public static void correctOffset(boolean condition, CPU cpu, String offset, Logger logger) throws JumpException, IrregularWriteOperationException, TwosComplementSumException, IrregularStringOfBitsException {

        // Count number of conditional branch instructions
        PredictionController.incrementBranchCount();

        if (condition) {
            // Condition True but predict Not Taken: Add PC offset @ instruction execution
            if (!PredictionController.isPredictTaken()) {

                // Change Prediction Miscount
                PredictionController.incrementMispredictCount();
                logger.info("Increment misprediction to " + PredictionController.getMispredictCount());

                // Change Prediction if Miscount reached
                if (PredictionController.isMispredictReached()) {
                    PredictionController.changePrediction();
                    logger.info("Changing Prediction to " + PredictionController.isPredictTaken());
                }

                String pc_new = "";
                Register pc = cpu.getPC();
                String pc_old = cpu.getPC().getBinString();

                //subtracting 4 to the pc_old temporary variable using bitset64 safe methods and adding pc offset
                BitSet64 bs_temp = new BitSet64();
                bs_temp.writeDoubleWord(- 4);
                pc_old = InstructionsUtils.twosComplementSum(pc_old, bs_temp.getBinString());

                //updating program counter
                pc_new = InstructionsUtils.twosComplementSum(pc_old, offset);
                pc.setBits(pc_new, 0);

                // Flush the mispredicted instruction
                throw new JumpException();
            }

        } else {
            // Condition False but predict Taken: Remove PC offset @ instruction execution
            if (PredictionController.isPredictTaken()) {
                // Change Prediction Miscount
                PredictionController.incrementMispredictCount();
                logger.info("Increment misprediction to " + PredictionController.getMispredictCount());

                // Change Prediction if Miscount reached
                if (PredictionController.isMispredictReached()) {
                    PredictionController.changePrediction();
                    logger.info("Changing Prediction to " + PredictionController.isPredictTaken());
                }

                // Change PC offset: CPU changed the offset, need to change it back to + 4
                Register pc = cpu.getPC();
                String pc_old = cpu.getPC().getBinString();

                //subtracting 4 to the pc_old temporary variable using bitset64 safe methods
                BitSet64 bs_temp = new BitSet64();
                bs_temp.writeDoubleWord(- 4 - PredictionController.getOffsetPC());
                pc_old = InstructionsUtils.twosComplementSum(pc_old, bs_temp.getBinString());

                //updating program counter by setting it to pc_old
                pc.setBits(pc_old, 0);

                // Flush the mispredicted instruction
                throw new JumpException();
            }
        }

    }
}


