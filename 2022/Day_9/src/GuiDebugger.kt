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

class RopeTextArea(var area: Array<Array<Point?>>): JTextArea(){
    init {
        this.area = area
        this.area = area
        this.foreground = Color.white
        this.background = Color.darkGray
        this.rows = 30
        this.columns = 30
        this.font = Font("consolas", Font.BOLD, 35)
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

    val arr = ropeArea.area
    override fun keyTyped(typed: KeyEvent?) {
    }

    fun relocatePoints(){
        arr[head.row][head.col] = head
        var p = head
        while (true){
            val next = p.next ?: break
            arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
            p.moveTail()
//            println("row: ${p.row}, ${p.col}")
            val tailInArr = arr[p.next?.row!!][p?.next!!.col]
            if (tailInArr?.symbol == '.'){
//                println("equals . in a")
                arr[p.next!!.row][p.next!!.col] = p.next
            }
            p = p.next!!
        }
//            println("nr ${p.next?.row} cr ${p.next?.col}")
//            println("r ${p.row} c ${p.col}")
        ropeArea.updateRopeArea(arr)
    }

    override fun keyPressed(press: KeyEvent?) {
        if (press?.keyChar == 'a'){
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveLeft()
            relocatePoints()

        }
        else if (press?.keyChar == 'w'){
            val head = head
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveUp()
            relocatePoints()
        }
        else if (press?.keyChar == 's'){
            var head = head
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveDown()
            relocatePoints()
        }
        else if (press?.keyChar == 'd'){
            var head = head
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveRight()
            relocatePoints()
        }
    }

    override fun keyReleased(rel: KeyEvent?) {
        rel?.consume()
    }
}

class RopeFrame(var area: Array<Array<Point?>>): Frame(){
    var ropeArea: RopeTextArea
    init {
        val row = Random.nextInt(29)
        val col = Random.nextInt(29)
        val tailPoint8 = Point(row, col, '8', null)
        val tailPoint7 = Point(row, col, '7', tailPoint8)
        val tailPoint6 = Point(row, col, '6', tailPoint7)
        val tailPoint5 = Point(row, col, '5', tailPoint6)
        val tailPoint4 = Point(row, col, '4', tailPoint5)
        val tailPoint3 = Point(row, col, '3', tailPoint4)
        val tailPoint2 = Point(row, col, '2', tailPoint3)
        val tailPoint1 = Point(row, col, '1', tailPoint2)
        val headPoint = Point(row, col, '0', tailPoint1)
        this.area[row][col] = headPoint
        this.ropeArea = RopeTextArea(area)
        this.addWindowListener(RopeAdapter())
        this.addKeyListener(EventListener(ropeArea, headPoint))
        this.add(ropeArea)
        this.minimumSize = Dimension(1300, 1300)
        this.isVisible = true
    }
}