fun solution(line: String): Int{
    val rawInput = line.split(",")
    var loopVal = 0
    val firstElf = rawInput[0].split("-").map { it.toInt() }.toTypedArray()
    val secondElf = rawInput[1].split("-").map { it.toInt() }.toTypedArray()
    if (firstElf[0] >= secondElf[0] && firstElf[1] <= secondElf[1]){
        return 2
    }
    else if (firstElf[0] <= secondElf[0] && firstElf[1] >= secondElf[1]){
        return 2
    }
    //Part two explicitly
    if (firstElf[0] >= secondElf[0] && firstElf[0] <= secondElf[1]){
        loopVal ++
    }
    else if (firstElf[1] >= secondElf[0] && firstElf[1] <= secondElf[1]){
        loopVal ++
    }
    else if (firstElf[0] <= secondElf[0] && firstElf[0] >= secondElf[1]){
        loopVal ++
    }

    return loopVal
}

fun main() {
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day4.txt"
    var endVal1 = 0
    var endVal2 = 0
    val lines: List<String> = (readLines(cpath))
    val begin = System.nanoTime()

    for (line in lines){
        val result = solution(line)
        if (result ==2 ) {
            endVal1 ++
            endVal2 ++
        }
        else if (result == 1){
            endVal2 ++
        }
    }
    println(endVal1)
    println(endVal2)

    val end = System.nanoTime()
    println("Elapsed time in microseconds: ${(end-begin)/1000}")
}