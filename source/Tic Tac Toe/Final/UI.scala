import java.awt.{Color, Dimension}

import scala.swing._

/** *********************************************************
  * Created by Andrew M. Frey
  * Date: 4/17/2016
  * Time: 5:51 PM
  * Modified from http://otfried.org/scala/gui.html tutorial
  * *********************************************************/
class UI(val board: Board) extends MainFrame {
  title = "Tic Tac Toe"

  val canvas = new Canvas(board)
  val newGameButton = Button("New Game") { newGame() }
  val turnLabel = new Label("Player 1's turn") { foreground = Color.BLUE }
  val quitButton = Button("Quit") { sys.exit(0) }
  val buttonLine = new BoxPanel(Orientation.Horizontal) {
    contents += newGameButton
    contents += Swing.HGlue
    contents += turnLabel
    contents += Swing.HGlue
    contents += quitButton
  }

  // make sure that resizing only changes the TicTacToeDisplay
  restrictHeight(buttonLine)

  contents = new BoxPanel(Orientation.Vertical) {
    contents += canvas
    contents += Swing.VStrut(10)
    contents += buttonLine
    border = Swing.EmptyBorder(10)
  }

  listenTo(canvas)
  reactions += {
    case TicTacToeEvent(x, y) =>
      board.play(x, y)
      updateLabelAndBoard()
      val result = board.winner()
      result match {
        case 1 =>
          Dialog.showMessage(contents.head, "Player 1 wins!")
          board.setGameActive(false)
        case 2 =>
          Dialog.showMessage(contents.head, "Player 2 wins!")
          board.setGameActive(false)
        case 0 =>
          Dialog.showMessage(contents.head, "It's a tie!")
          board.setGameActive(false)
        case _ => //game is still on-going
      }
  }

  private def restrictHeight(s: Component): Unit = {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }

  def updateLabelAndBoard(): Unit = {
    turnLabel.text = "Player %d's turn".format(board.currentPlayer)
    canvas.repaint()
  }

  def newGame(): Unit = {
    board.restart()
    updateLabelAndBoard()
  }
}