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
//    val gui = RopeFrame(area.area, 30, 30)
    val partOne = PartOne(area)
    println(partOne.loop(lines))
}

class CheckPoint(val row: Int, val col: Int){}

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
    var partOne = arrayListOf<CheckPoint>()
    val tail = Point(500, 500, 'T', null)
    val head = Point(500, 500, 'H', tail)

    fun loop(lines: List<String>): Int{
        for (line in lines) {
            val args = line.split(" ")
            val range = args[1].toInt()
            when (args[0]) {
                "R" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveRight()
                    partOne.add(changeTail(area.area, head))
                }

                "L" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveLeft()
                    partOne.add(changeTail(area.area, head))
                }

                "U" ->
                    for (i in 0 until range) {
                        area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                        head.moveUp()
                        partOne.add(changeTail(area.area, head))
                    }

                "D" -> for (i in 0 until range) {
                    area.area[head.row][head.col] = Point(head.row, head.col, '.', null)
                    head.moveDown()
                    partOne.add(changeTail(area.area, head))
                }

            }
        }
        return partOne.distinct().size //somehow returns with copies but when manually deleted then right answer 
    }
}