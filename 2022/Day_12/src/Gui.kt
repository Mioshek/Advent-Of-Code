import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Frame
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JTextPane
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter
import kotlin.system.exitProcess

class HillClimbingAdapter: WindowAdapter(){
    override fun windowClosing(e: WindowEvent?) {
        super.windowClosing(e)
        exitProcess(0)
    }
}

class HillClimbingArea(private var area: Array<Array<PathParticle>>): JTextPane(){
    private val greenPainter = DefaultHighlightPainter(Color.GREEN)
    private val redPainter = DefaultHighlightPainter(Color.RED)

    init {
        this.foreground = Color.white
        this.background = Color.black
        this.font = Font("consolas", Font.BOLD, 13)
        this.text = convertToStrArr(this.area)
//        this.text = "<font color=\"#9900FF\"> Welcome to freeCodeCamp. </font>"
//        this.contentType = "text/html"
        this.isFocusable = false
//
    }

    private fun convertToStrArr(arr: Array<Array<PathParticle>>): String {
        var str = ""
        for (row in arr){
            for (col in row){
                str += col.symbol
                str += " "
            }
            str += "\n"
        }
        return str
    }

    fun addHighlight(y: Int, x: Int){
        this.highlighter.addHighlight((y*343)+ x*2, (y*343)+ x*2+1, greenPainter)
    }

    fun addHighlightPath(y: Int, x: Int){
        this.highlighter.addHighlight((y*343)+ x*2, (y*343)+ x*2+1, redPainter)
    }
}

class GuiFrame(area: Array<Array<PathParticle>>): Frame() {
    var hillArea: HillClimbingArea = HillClimbingArea(area)

    init{
        this.addWindowListener(HillClimbingAdapter())
        this.add(hillArea)
        this.minimumSize = Dimension(800, 2000)
        this.isVisible = true
    }
}