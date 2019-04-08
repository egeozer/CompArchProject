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

        if (condition) {

            // Condition True but predict Not Taken: Add PC offset @ instruction execution
            if (!CountController.isPredictTaken()) {
                String pc_new = "";
                Register pc = cpu.getPC();
                String pc_old = cpu.getPC().getBinString();

                //subtracting 4 to the pc_old temporary variable using bitset64 safe methods
                BitSet64 bs_temp = new BitSet64();
                bs_temp.writeDoubleWord(-4);
                pc_old = InstructionsUtils.twosComplementSum(pc_old, bs_temp.getBinString());

                //updating program counter
                pc_new = InstructionsUtils.twosComplementSum(pc_old, offset);
                pc.setBits(pc_new, 0);
                throw new JumpException();
            }


        } else {
            // Condition False but predict Taken: Remove PC offset @ instruction execution
            if (CountController.isPredictTaken()) {
                // Change Prediction Miscount
                CountController.incrementMispredictCount();
                logger.info("Increment misprediction to " + CountController.getMispredictCount());

                if (CountController.isMispredictReached()) { //Error catch condition
                    CountController.changePrediction();
                    logger.info("Changing Prediction to " + CountController.isPredictTaken());
                }

                // Change PC offset: CPU changed the offset, need to change it back to + 4
                String pc_new = "";
                Register pc = cpu.getPC();
                String pc_old = cpu.getPC().getBinString();

                //subtracting 4 to the pc_old temporary variable using bitset64 safe methods
                BitSet64 bs_temp = new BitSet64();
                bs_temp.writeDoubleWord(-4 - CountController.getOffsetPC());
                pc_old = InstructionsUtils.twosComplementSum(pc_old, bs_temp.getBinString());

                //updating program counter
                pc_new = InstructionsUtils.twosComplementSum(pc_old, offset);
                pc.setBits(pc_new, 0);

            }
        }



        }
    }


