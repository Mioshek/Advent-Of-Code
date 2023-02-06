import kotlin.reflect.typeOf

fun changeTail(area: Array<Array<Point?>>, head: Point): CheckPoint {
    area[head.row][head.col] = head
    area[head.next!!.row][head.next!!.col] = Point(head.next!!.row, head.next!!.col, '.', null)
    head.moveTail()
    val tailInArr = area[head.next?.row!!][head?.next!!.col]
    if (tailInArr?.symbol == '.'){
        area[head.next!!.row][head.next!!.col] = head.next!!
    }
    return CheckPoint(head.next!!.row, head.next!!.col)
}

fun main(args: Array<String>) {
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day9.txt"
    val beginTime = System.nanoTime()
    val lines = readLines(cpath)
    val area = Area(1000, 1000)
//    val gui = RopeFrame(area.area, 40, 40)
    val partOne = PartOne(area)
    println(partOne.loop(lines))
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
    val tailPoint9 = Point(500, 500, '9', null)
    val tailPoint8 = Point(500, 500, '8', tailPoint9)
    val tailPoint7 = Point(500, 500, '7', tailPoint8)
    val tailPoint6 = Point(500, 500, '6', tailPoint7)
    val tailPoint5 = Point(500, 500, '5', tailPoint6)
    val tailPoint4 = Point(500, 500, '4', tailPoint5)
    val tailPoint3 = Point(500, 500, '3', tailPoint4)
    val tailPoint2 = Point(500, 500, '2', tailPoint3)
    val tailPoint1 = Point(500, 500, '1', tailPoint2)
    val head = Point(500, 500, 'H', tailPoint1)

    fun loop(lines: List<String>): Int {
        for (line in lines) {
            val args = line.split(" ")
            val range = args[1].toInt()
            when (args[0]) {
                "R" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveRight()
                    val strValue = changeTail(area.area, head).toString()
                    if (strValue !in partOne){
                        partOne.add(strValue)
                    }

                }

                "L" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveLeft()
                    val strValue = changeTail(area.area, head).toString()
                    if (strValue !in partOne){
                        partOne.add(strValue)
                    }
                }

                "U" ->
                    for (i in 0 until range) {
                        area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                        head.moveUp()
                        val strValue = changeTail(area.area, head).toString()
                        if (strValue !in partOne){
                            partOne.add(strValue)
                        }
                    }

                "D" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveDown()
                    val strValue = changeTail(area.area, head).toString()
                    if (strValue !in partOne){
                        partOne.add(strValue)
                    }
                }

            }
        }
        return partOne.size //somehow returns with copies but when manually deleted then right answer
    }
}