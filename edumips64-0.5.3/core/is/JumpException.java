/*
 * JumpException.java
 *
 *  20th may 2006
 * Exception for the MIPS64 Instruction Set
 * (c) 2006 EduMips64 project - Trubia Massimo, Russo Daniele
 *
 * This file is part of the EduMIPS64 project, and is released under the GNU
 * General Public License.
 * This file is part of the EduMIPS64 project, and is released under the GNU
 * General Public License.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edumips64.core.is;

import core.CountController;
import edumips64.core.CPU;

import java.util.logging.Logger;

/**
 * @author Trubia Massimo, Russo Daniele
 */
public class JumpException extends Exception {

    private static final Logger logger = Logger.getLogger(CPU.class.getName());

    public JumpException() {
        // Check what type of prediction since Taken
        if (!CountController.isPredictTaken()) {
            CountController.incrementMispredictCount();
            logger.info("Increment misprediction to " + CountController.getMispredictCount());

            if (CountController.isMispredictReached()) {
                CountController.changePrediction();
                logger.info("Changing Prediction to " + CountController.isPredictTaken());
            }

        }
    }
}

