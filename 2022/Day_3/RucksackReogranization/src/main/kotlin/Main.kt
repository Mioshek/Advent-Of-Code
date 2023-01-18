import java.io.File

fun readLines(fileName: String) = File(fileName).useLines { it.toList() }


fun main() {
    val eachLetterValArray: CharArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    var strLen: Int
    var endVal: Int = 0
    var leftCompart: String
    var rightCompart: String

    val lines: List<String> =
        (readLines("/home/mioshek/Programming_Stuff/Programming/Rust/Advent_of_code/2022/inputs/Day3.txt"))
    for (line in lines) {
        strLen = line.length
        leftCompart = line.substring(0, strLen / 2)
        rightCompart = line.substring(strLen / 2, strLen)
        println(line)
        println(leftCompart)
        println(rightCompart)

        for (chr in leftCompart){
            if (chr in rightCompart){
                println(chr)
                endVal += eachLetterValArray.indexOf(chr.toChar()) + 1
                println(endVal)
                break
            }
        }
    }
    println(endVal)

}