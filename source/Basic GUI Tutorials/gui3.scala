import scala.swing._

class UI extends MainFrame {
  title = "GUI Program #3"
  contents = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Look at me!")
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += Button("Press me, please") {
      println("Thank you")
    }
    contents += Button("Close") {
      sys.exit(0)
    }
    border = Swing.CompoundBorder(Swing.BeveledBorder(Swing.Lowered),
                                  Swing.EmptyBorder(10))
  }
}

object GuiProgramThree {
  def main(args: Array[String]): Unit ={
    val ui = new UI
    ui.visible = true
  }
}