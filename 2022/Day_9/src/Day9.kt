fun main(args: Array<String>) {
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day8.txt"
    val beginTime = System.nanoTime()
    val lines = readLines(cpath)
    val area = Area(30, 30)
    val gui = RopeFrame(area.area)

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