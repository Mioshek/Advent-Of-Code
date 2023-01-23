fun main(){val cpath = System.getProperty("user.dir") + "/2022/inputs/Day6.txt"
    var result1 = 0
    var result2 = 0
    var end = false
    val input: CharArray = readLines(cpath)[0].toCharArray()
    val beginTime = System.nanoTime()
    for (i in 3 until input.size){
        val nextFourElements = arrayOf(input[i-3],input[i-2],input[i-1],input[i])
        val nextFourteenElements = Array(14){'0'}
        if (i>=13){
            for (j in 0..13)
                nextFourteenElements[j] = input[i-j]
        }


        if (nextFourElements.toSet().size == 4 && !end){
            result1 = i +1
            end = true
        }
        if (nextFourteenElements.toSet().size == 14 && end){
            println("true")
            result2 = i +1
            break
        }
    }
    val endTime = System.nanoTime()
    println("Elapsed time in microseconds: ${(endTime-beginTime)/1000}")
    println(result1)
    println(result2)
}
