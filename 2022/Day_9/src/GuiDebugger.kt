import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Frame
import java.awt.event.*
import javax.swing.JTextArea
import kotlin.random.Random
import kotlin.system.exitProcess

class RopeAdapter: WindowAdapter(){
    override fun windowClosing(e: WindowEvent?) {
        super.windowClosing(e)
        exitProcess(0)
    }
}

class RopeTextArea(var area: Array<Array<Point?>>,rows: Int, cols: Int): JTextArea(){
    init {
        this.area = area
        this.foreground = Color.white
        this.background = Color.darkGray
        this.rows = rows
        this.columns = cols
        this.font = Font("consolas", Font.BOLD, 30)
        this.text = convertToString(this.area)
        this.isFocusable = false
    }

    fun updateRopeArea(textArr: Array<Array<Point?>>){
        this.text = convertToString(textArr)
    }

    fun convertToString(arr: Array<Array<Point?>>): String {
        var str = ""
        for (row in arr){
            for (col in row){
                str += col?.symbol
                str += " "
            }
            str += "\n"
        }
        return str
    }
}

class EventListener(private var ropeArea: RopeTextArea, val head: Point,) :  KeyListener {
    var rope = Rope()
    val arr = ropeArea.area

    override fun keyTyped(typed: KeyEvent?) {}

    fun update(){
        val nArr = rope.relocatePoints(head, arr)
        ropeArea.updateRopeArea(nArr)
    }

    override fun keyPressed(press: KeyEvent?) {
        if (press?.keyChar == 'a'){
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveLeft()
            update()

        }
        else if (press?.keyChar == 'w'){
            val head = head
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveUp()
            update()
        }
        else if (press?.keyChar == 's'){
            var head = head
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveDown()
            update()
        }
        else if (press?.keyChar == 'd'){
            var head = head
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveRight()
            update()
        }
    }

    override fun keyReleased(rel: KeyEvent?) {
        rel?.consume()
    }
}

class RopeFrame(var area: Array<Array<Point?>>, rows: Int, cols: Int): Frame(){
    var ropeArea: RopeTextArea
    init {
        val row = Random.nextInt(39)
        val col = Random.nextInt(39)

        val tailPoint9 = Point(row, col, '9', null)
        val tailPoint8 = Point(row, col, '8', tailPoint9)
        val tailPoint7 = Point(row, col, '7', tailPoint8)
        val tailPoint6 = Point(row, col, '6', tailPoint7)
        val tailPoint5 = Point(row, col, '5', tailPoint6)
        val tailPoint4 = Point(row, col, '4', tailPoint5)
        val tailPoint3 = Point(row, col, '3', tailPoint4) // Only for debugging
        val tailPoint2 = Point(row, col, '2', tailPoint3)
        val tailPoint1 = Point(row, col, '1', tailPoint2)
        val headPointH = Point(row, col, 'H', tailPoint1)
        this.area[row][col] = headPointH
        this.ropeArea = RopeTextArea(area, rows, cols )
        this.addWindowListener(RopeAdapter())
        this.addKeyListener(EventListener(ropeArea, headPointH))
        this.add(ropeArea)
        this.minimumSize = Dimension(1300, 1300)
        this.isVisible = true
    }
}