/** *********************************************************
  * Created by Andrew M. Frey 
  * Date: 4/17/2016
  * Time: 5:51 PM
  * Modified from http://otfried.org/scala/gui.html tutorial
  * *********************************************************/
class Board {
  private var turn = 0
  private var player = 1
  private var gameActive = true
  private val grid = Array(0,0,0,
                           0,0,0,
                           0,0,0)

  def setGameActive(cond: Boolean): Unit = { gameActive = cond }
  def getGameActive: Boolean = gameActive

  def apply(x: Int, y: Int): Int = grid(3 * y + x)

  def currentPlayer: Int = player

  def play(x: Int, y: Int): Unit = {
    if (apply(x, y) == 0) {
      grid(3 * y + x) = player
      player = 3 - player
      turn += 1
    }
  }

  def restart(): Unit = {
    for (i <- 0 until 9)
      grid(i) = 0
    player = 1
    gameActive = true
    turn = 0
  }

  def winner(): Int = {
    for (x <- 0 until 3) {
      val rowProduct = grid(x * 3) * grid(x * 3 + 1) * grid(x * 3 + 2)
      val colProduct = grid(x) * grid(x + 3) * grid(x + 6)
      if (rowProduct == 1 || rowProduct == 8){
        return grid(x * 3)
      } else if (colProduct == 1 || colProduct == 8){
        return grid(x)
      }
    }
    val lDiagonalProduct = grid(0) * grid(4) * grid(8)
    val rDiagonalProduct = grid(2) * grid(4) * grid(6)
    if (lDiagonalProduct == 1 || lDiagonalProduct == 8 ||
        rDiagonalProduct == 1 || rDiagonalProduct == 8) {
      return grid(4)
    }
    if (turn == 9){
      return 0
    }
    -1
  }
}
