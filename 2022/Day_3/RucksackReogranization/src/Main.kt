import java.io.File

fun readLines(fileName: String) = File(fileName).useLines { it.toList() }

fun partOne(line: String, alphabetArr: CharArray): Int {
    val strLen: Int = line.length
    val leftCompart = line.substring(0, strLen / 2)
    val rightCompart = line.substring(strLen / 2, strLen)

    for (chr in leftCompart) {
        if (chr in rightCompart) {
            return alphabetArr.indexOf(chr) + 1
        }
    }
    return 0
}

fun partTwo(linesArr: Array<String>, alphabetArr: CharArray): Int{
    val line1 = linesArr[0]
    val line2 = linesArr[1]
    val line3 = linesArr[2]
    for (chr in line1){
        if (chr in line2 && chr in line3) {
            return alphabetArr.indexOf(chr) +1
        }
    }

    return 0
}

fun main() {
    val alphabetArr: CharArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    var endVal1 = 0
    var endVal2 = 0
    var index = 0
    var threeLinesArr = Array<String>(3){""}

    val lines: List<String> =
        (readLines("/home/mioshek/Programming_Stuff/Programming/Advent_of_code/2022/inputs/Day3.txt"))
    val begin = System.nanoTime()
    for (line in lines) {
        endVal1 += partOne(line, alphabetArr)

        if (index % 3 ==2){
            threeLinesArr[index%3] = line
            endVal2 += partTwo(threeLinesArr, alphabetArr)
        }
        else{
            threeLinesArr[index%3] = line
        }
        index+=1
    }
    val end = System.nanoTime()
    println("Elapsed time in micro: ${(end-begin)/1000}")
    println(endVal1)
    println(endVal2)

}