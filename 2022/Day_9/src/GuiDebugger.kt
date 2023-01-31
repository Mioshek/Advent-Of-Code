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

class EventListener(private var ropeArea: RopeTextArea, var point: Point) :  KeyListener {

    override fun keyTyped(typed: KeyEvent?) {
    }

    override fun keyPressed(press: KeyEvent?) {
        if (press?.keyChar == 'a'){
            var arr = ropeArea.area
            var head = point
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveLeft()
            arr[head.row][head.col] = head
            var p = head
            while (p.next != null){
                arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
                val newTail = p.moveTail()
                println("row: ${newTail.row}, ${newTail.col}")
                val tailInArr = arr[newTail.next?.row!!][newTail?.next!!.col]
                if (tailInArr?.symbol == '.'){
                    println("equals . in a")
                    arr[newTail.next!!.row][newTail.next!!.col] = newTail.next
                }
                p = newTail.next!!
            }
//            println("nr ${p.next?.row} cr ${p.next?.col}")
//            println("r ${p.row} c ${p.col}")
            ropeArea.updateRopeArea(arr)
        }
        else if (press?.keyChar == 'w'){
            var arr = ropeArea.area
            val head = point
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveUp()
            arr[head.row][head.col] = head
            var p = head
            while (p.next != null){
                arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
                val newTail = p.moveTail()
                println("row: ${newTail.row}, ${newTail.col}")
                val tailInArr = arr[newTail.next?.row!!][newTail?.next!!.col]
                if (tailInArr?.symbol == '.'){
                    println("equals . in w")
                    arr[newTail.next!!.row][newTail.next!!.col] = newTail.next
                }
                p = newTail.next!!
            }
//            println("nr ${p.next?.row} cr ${p.next?.col}")
//            println("r ${p.row} c ${p.col}")
            ropeArea.updateRopeArea(arr)
        }
        else if (press?.keyChar == 's'){
            var arr = ropeArea.area
            var head = point
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveDown()
            arr[head.row][head.col] = head
            var p = head
            while (p.next != null){
                arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
                val newTail = p.moveTail()
                println("row: ${newTail.row}, ${newTail.col}")
                val tailInArr = arr[newTail.next?.row!!][newTail?.next!!.col]
                if (tailInArr?.symbol == '.'){
                    println("equals . in s")
                    arr[newTail.next!!.row][newTail.next!!.col] = newTail.next
                }
                p = newTail.next!!
            }
//            println("nr ${p.next?.row} cr ${p.next?.col}")
//            println("r ${p.row} c ${p.col}")
            ropeArea.updateRopeArea(arr)
        }
        else if (press?.keyChar == 'd'){
            var arr = ropeArea.area
            var head = point
            arr[head.row][head.col] = Point(head.row, head.col, '.', null)
            head.moveRight()
            arr[head.row][head.col] = head
            var p = head
            while (p.next != null){
                arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
                val newTail = p.moveTail()
                println("row: ${newTail.row}, ${newTail.col}")
                val tailInArr = arr[newTail.next?.row!!][newTail?.next!!.col]
                if (tailInArr?.symbol == '.'){
                    println("equals . in d")
                    arr[newTail.next!!.row][newTail.next!!.col] = newTail.next
                }

                p = newTail.next!!
            }
//            println("nr ${p.next?.row} cr ${p.next?.col}")
//            println("r ${p.row} c ${p.col}")
            ropeArea.updateRopeArea(arr)
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
        val tailPoint8 = Point(row, col, 'T', null)
        val tailPoint7 = Point(row, col, 'T', tailPoint8)
        val tailPoint6 = Point(row, col, 'T', tailPoint7)
        val tailPoint5 = Point(row, col, 'T', tailPoint6)
        val tailPoint4 = Point(row, col, 'T', tailPoint5)
        val tailPoint3 = Point(row, col, 'T', tailPoint4)
        val tailPoint2 = Point(row, col, 'T', tailPoint3)
        val tailPoint1 = Point(row, col, 'T', tailPoint2)
        val headPoint = Point(row, col, 'H', tailPoint1)
        this.area[row][col] = headPoint
        this.ropeArea = RopeTextArea(area)
        this.addWindowListener(RopeAdapter())
        this.addKeyListener(EventListener(ropeArea, headPoint))
        this.add(ropeArea)
        this.minimumSize = Dimension(1300, 1300)
        this.isVisible = true
    }
}