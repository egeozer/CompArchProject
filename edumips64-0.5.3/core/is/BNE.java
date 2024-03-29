/*
 * BNE.java
 *
 * may 2006
 * Instruction BNE of the MIPS64 Instruction Set
 * (c) 2006 EduMips64 project - Trubia Massimo, Russo Daniele
 *
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
import core.PredictionCorrector;
import edumips64.core.*;
import edumips64.utils.*;
/** <pre>
 *         Syntax: BNE rt, rs, immediate
 *    Description: if rs != rt then branch
 *                 To compare GPRs then do a PC-relative conditional branch
 *  </pre>
  * @author Trubia Massimo, Russo Daniele
 */

public class BNE extends FlowControl_IType {
    final String OPCODE_VALUE="000101";
    
    public BNE() {
        super.OPCODE_VALUE = OPCODE_VALUE;
        syntax="%R,%R,%B";
	name="BNE";
    }

    public void ID() throws RAWException, IrregularWriteOperationException, IrregularStringOfBitsException,TwosComplementSumException, JumpException {
        if(cpu.getRegister(params.get(RS_FIELD)).getWriteSemaphore()>0 || cpu.getRegister(params.get(RT_FIELD)).getWriteSemaphore()>0)
            throw new RAWException();
        //getting registers rs and rt
        String rs=cpu.getRegister(params.get(RS_FIELD)).getBinString();
        String rt=cpu.getRegister(params.get(RT_FIELD)).getBinString();
         //converting offset into a signed binary value of 64 bits in length
        BitSet64 bs=new BitSet64();
        bs.writeHalf(params.get(OFFSET_FIELD));
        String offset=bs.getBinString();
        boolean condition=!rs.equals(rt);
        PredictionCorrector.correctOffset(condition, cpu, offset, logger);


    }

    
}
