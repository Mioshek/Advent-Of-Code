interface Visibility{
    fun checkHorizontally(): Boolean
    fun checkVertically(): Boolean
}

class Descending(private val row: Int, private val col: Int, private val grid: Array<IntArray>): Visibility{
    private val startValue: Int = grid[row][col]

    override fun checkHorizontally(): Boolean {
        for (x in col-1 downTo 0){
            if (grid[row][x] >= startValue){
                return false
            }
        }
        return true
    }

    override fun checkVertically(): Boolean {
        for (y in row-1 downTo 0){
            if (grid[y][col] >= startValue){
                return false
            }
        }
        return true
    }
}


class Ascending(private val row: Int, private val col: Int, private val grid: Array<IntArray>): Visibility{
    private val startValue: Int = grid[row][col]
    private val width = grid[0].size
    private val height = grid.size

    override fun checkHorizontally(): Boolean {
        for (x in col+1 until width){
            if (grid[row][x] >= startValue){
                return false
            }
        }
        return true
    }

    override fun checkVertically(): Boolean {
        for (y in row+1 until height){
            if (grid[y][col] >= startValue){
                return false
            }
        }
        return true
    }

}

fun main() {
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day8.txt"
    val beginTime = System.nanoTime()
    val lines = readLines(cpath)
    val height = lines.size
    val width = lines[0].length
    val forest = Array(height) { IntArray(width) }
    var partOne = 0

    for (row in 0 until height){
        for (col in 0 until width){
            forest[row][col] = lines[row][col].digitToInt()
        }
    }

    for (row in 0 until height){
        for (col in 0 until width){
            if (col == 0 || col == width-1 || row == 0 || row == height-1) {
                partOne += 1
            }
            else{
                val asc = Ascending(row,col,forest)
                val desc = Descending(row,col,forest)
                if (asc.checkVertically() || asc.checkHorizontally() || desc.checkVertically() || desc.checkHorizontally()){
                    partOne +=1
                }
            }
        }
    }
    val endTime = System.nanoTime()
    println("Elapsed time in microseconds: ${(endTime-beginTime)/1000}")
    println("$partOne p1")
}