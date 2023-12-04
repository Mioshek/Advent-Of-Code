import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Frame
import java.awt.Toolkit
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

class HillClimbingArea(private var area: Array<Array<PathParticle>>, minimumSize: Dimension): JTextPane(){
    private val greenPainter = DefaultHighlightPainter(Color.GREEN)
    private val redPainter = DefaultHighlightPainter(Color.RED)
    private val guiWidth = area[1].size * 2 +1

    init {
        println(minimumSize.width)
        this.foreground = Color.white
        this.background = Color.black
        this.font = Font("consolas", Font.BOLD, minimumSize.width/190)
        this.text = convertToStrArr(this.area)
        this.isFocusable = false
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
        this.highlighter.addHighlight((y*guiWidth)+ x*2, (y*guiWidth)+ x*2+1, greenPainter)
    }

    fun addHighlightPath(y: Int, x: Int){
        this.highlighter.addHighlight((y*guiWidth)+ x*2, (y*guiWidth)+ x*2+1, redPainter)
    }
}

class GuiFrame(area: Array<Array<PathParticle>>, private val resizable: Boolean): Frame() {
    private val dimensions = getCustomDimensions()
    var hillArea = HillClimbingArea(area, dimensions)

    init{
        this.minimumSize = dimensions
        this.isVisible = true
        this.addWindowListener(HillClimbingAdapter())
        this.add(hillArea)
    }

    private fun getCustomDimensions(): Dimension {
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        this.isResizable = resizable
        val ratio = screenSize.width/screenSize.height
        return Dimension(screenSize.width, screenSize.height/ratio)
    }

}