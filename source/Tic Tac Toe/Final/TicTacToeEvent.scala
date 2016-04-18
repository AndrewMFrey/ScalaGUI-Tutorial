import scala.swing.event.Event

/** *********************************************************
  * Created by Andrew M. Frey
  * Date: 4/17/2016
  * Time: 5:51 PM
  * Modified from http://otfried.org/scala/gui.html tutorial
  * *********************************************************/

case class TicTacToeEvent(x: Int, y: Int) extends Event
