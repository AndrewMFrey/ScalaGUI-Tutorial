import java.awt.geom._
import java.awt.{BasicStroke, Color, Graphics2D}

import scala.swing._
import scala.swing.event._

/** *********************************************************
  * Created by Andrew M. Frey
  * Date: 4/17/2016
  * Time: 5:51 PM
  * Modified from http://otfried.org/scala/gui.html tutorial
  * *********************************************************/
class Canvas(val board: Board) extends Component {
  preferredSize = new Dimension(320, 320)

  focusable = true
  listenTo(mouse.clicks)
  listenTo(keys)
  reactions += {
    case MouseClicked(_, p, _, _, _) => mouseClick(p.x, p.y)
    case KeyTyped(_, c, _, _) =>
      if ('1' <= c && c <= '9' && board.getGameActive) {
        val idx = c - '1'
        publish(TicTacToeEvent(idx % 3, idx / 3))
      }
  }

  // returns squareSide, x0, y0, wid
  private def squareGeometry: (Int, Int, Int, Int) = {
    val d = size
    val squareSide = d.height min d.width
    val x0 = (d.width - squareSide) / 2
    val y0 = (d.height - squareSide) / 2
    (squareSide, x0, y0, squareSide / 3)
  }

  private def mouseClick(x: Int, y: Int): Unit = {
    val (squareSide, x0, y0, wid) = squareGeometry
    if (x0 <= x && x < x0 + squareSide &&
        y0 <= y && y < y0 + squareSide &&
        board.getGameActive) {
      val col = (x - x0) / wid
      val row = (y - y0) / wid
      publish(TicTacToeEvent(col, row))
    }
  }

  override def paintComponent(g : Graphics2D): Unit = {
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
    g.setColor(Color.WHITE)
    val d = size
    g.fillRect(0, 0, d.width, d.height)
    val (squareSide, x0, y0, wid) = squareGeometry
    g.setColor(Color.BLACK)
    // vertical lines
    for (x <- 1 to 2)
      g.draw(new Line2D.Double(x0 + x * wid, y0, x0 + x * wid, y0 + squareSide))
    // horizontal lines
    for (y <- 1 to 2)
      g.draw(new Line2D.Double(x0, y0 + y * wid, x0 + squareSide, y0 + y * wid))
    g.setStroke(new BasicStroke(3f))
    for (x <- 0 until 3) {
      for (y <- 0 until 3) {
        board(x, y) match {
          case 1 =>
            g.setColor(Color.RED)
            g.draw(new Ellipse2D.Double(x0 + x * wid + 10, y0 + y * wid + 10, wid - 20, wid - 20))
          case 2 =>
            g.setColor(new Color(0, 160, 0))
            val x1 = x0 + x * wid + 10
            val y1 = y0 + y * wid + 10
            g.draw(new Line2D.Double(x1, y1, x1 + wid - 20, y1 + wid - 20))
            g.draw(new Line2D.Double(x1, y1 + wid - 20, x1 + wid - 20, y1))
          case _ => // draw nothing
        }
      }
    }
  }
}