import scala.swing._

class UI extends MainFrame {
  title = "Flow Panel"
  contents = new FlowPanel {
    contents += new Label("A Label")
    contents += Swing.RigidBox(new Dimension(30, 0))
    contents += new Button("A Button")
    contents += new Button("Another Button")
    contents += Button("Close") { sys.exit(0) }
  }
}

object PanelOne {
  def main(args: Array[String]): Unit = {
    val ui = new UI
    ui.visible = true
  }
}