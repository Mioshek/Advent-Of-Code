fun main(args: Array<String>) {
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day9.txt"
    val beginTime = System.nanoTime()
    val lines = readLines(cpath)
    var debug = false

    if (debug){
        val area = Area(40, 40)
        val gui = RopeFrame(area.area, 40, 40)
    }
    else{
        val area = Area(1000, 1000)
        val partOne = PartOne(area)
        println(partOne.loop(lines))
    }
    val endTime = System.nanoTime()
    println("Elapsed time in microseconds: ${(endTime-beginTime)/1000}")
}

class CheckPoint(val row: Int, val col: Int){
    override fun toString(): String {
        return "$row$col"
    }
}

class Area (width: Int, height: Int) {
    var area = Array(width){ Array<Point?>(height){null} }

    init {
        for (row in 0 until width){
            for (col in 0 until height){
                area[row][col] = Point(row, col, '.', null)
            }
        }
    }
}

class PartOne (var area: Area){
    var partOne = arrayListOf<String>()
//    private val tailPoint9 = Point(500, 500, '9', null)
//    private val tailPoint8 = Point(500, 500, '8', tailPoint9)
//    private val tailPoint7 = Point(500, 500, '7', tailPoint8)
//    private val tailPoint6 = Point(500, 500, '6', tailPoint7)
//    private val tailPoint5 = Point(500, 500, '5', tailPoint6) // if uncommented and tailPoint.next = tailPoint2 then part 2
//    private val tailPoint4 = Point(500, 500, '4', tailPoint5)
//    private val tailPoint3 = Point(500, 500, '3', tailPoint4)
//    private val tailPoint2 = Point(500, 500, '2', tailPoint3)
    private val tailPoint1 = Point(500, 500, '1', null)
    private val head = Point(500, 500, 'H', tailPoint1)
    private var rope = Rope()

    fun loop(lines: List<String>): Int {
        for (line in lines) {
            val args = line.split(" ")
            val range = args[1].toInt()
            when (args[0]) {
                "R" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveRight()
                    area.area = rope.relocatePoints(head, area.area)
                }

                "L" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveLeft()
                    area.area = rope.relocatePoints(head, area.area)
                }

                "U" ->
                    for (i in 0 until range) {
                        area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                        head.moveUp()
                        area.area = rope.relocatePoints(head, area.area)
                    }

                "D" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveDown()
                    area.area = rope.relocatePoints(head, area.area)
                }

            }
        }
        return rope.allPoints.size +1 //fixed
    }
}