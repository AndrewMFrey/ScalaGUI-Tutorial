import java.awt.event.ActionEvent
import java.text.SimpleDateFormat
import javax.swing.{Timer, AbstractAction}

import scala.swing._
import java.awt.{Font,Color}

object Time {
  private val form = new SimpleDateFormat("HH:mm:ss")
  def current = form.format(java.util.Calendar.getInstance().getTime)
}

object Timer {
  def apply(interval: Int, repeats: Boolean = true)(op: => Unit) {
    val timeOut = new AbstractAction() {
      def actionPerformed(e: java.awt.event.ActionEvent) = op
    }
    val t = new Timer(interval, timeOut)
    t.setRepeats(repeats)
    t.start()
  }
}

class UI extends MainFrame {
  title = "Clock 1"
  preferredSize = new Dimension(320, 160)
  private var lastTime = Time.current
  private val clock = new Label(lastTime) {
    foreground = new Color(0, 0, 160)
    font = new Font("SansSerif", Font.PLAIN, 64)
  }
  contents = clock
  Timer(200) { tick() }
  Timer(10000, false) { println("Clock has been running for 10 seconds!") }

  def tick(): Unit = {
    // called every 100 ms
    val newTime = Time.current
    if (newTime != lastTime) {
      clock.text = newTime
      lastTime = newTime
    }
  }
}

object ClockOne {
  def main(args: Array[String]): Unit = {
    val ui = new UI
    ui.visible = true
  }
}