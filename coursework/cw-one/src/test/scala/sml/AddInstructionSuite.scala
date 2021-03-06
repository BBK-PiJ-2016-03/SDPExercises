package sml

import org.scalatest.{BeforeAndAfter, FunSpec}
import sml.library.LabelsFactory

/**
  * Created by aworton on 19/02/17.
  */
class AddInstructionSuite extends FunSpec with BeforeAndAfter{

  private var instruction: AddInstruction = _
  private var loadR1Instruction: LinInstruction = _
  private var loadR2Instruction: LinInstruction = _
  private var machine: Machine = _
  private var labels: Labels = _
  private final val label0 = "f0"
  private final val label1 = "f1"
  private final val label2 = "f2"
  private final val increase = 10
  private final val register0 = 0
  private final val register1 = 1
  private final val register2 = 2

  before{
    labels = LabelsFactory.populateLabels(label0, label1, label2)
    loadR2Instruction = LinInstruction(label1, register2, increase)
  }

  describe("AddInstruction"){

    it("should take two values and return the sum of them"){
      val start = -1000
      val end = 1000

      (start to end by 2).foreach(valueR2 => {
        loadR1Instruction = LinInstruction(label0, register1, valueR2)
        instruction = AddInstruction(label2, register0, register1, register2)
        machine = Machine(labels, Vector(loadR1Instruction, loadR2Instruction, instruction))
        machine.execute()
        assert(machine.regs(register0) == (valueR2 + increase))
      })

    }

    it("should return its content when printed"){
      instruction = AddInstruction(label2, register0, register1, register2)
      assert(s"$label2: add $register1 + $register2 to $register0\n" == instruction.toString())
    }
  }

}
