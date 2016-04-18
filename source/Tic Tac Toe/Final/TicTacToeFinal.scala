/** *********************************************************
  * Created by Andrew M. Frey
  * Date: 4/17/2016
  * Time: 5:51 PM
  * Modified from http://otfried.org/scala/gui.html tutorial
  * *********************************************************/

object TicTacToeFinal {
  def main(args: Array[String]): Unit = {
    val board = new Board
    val ui = new UI(board)
    ui.visible = true
  }
}
