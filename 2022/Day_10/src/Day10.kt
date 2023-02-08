
fun main(){
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day10.txt"
    val beginTime = System.nanoTime()
    val lines = readLines(cpath)
    val p1 = Part1()
    val p2 = Part2()

    for(line in lines){
        val splitLine = line.split(" ")
//        if p2.currentRow
        if (splitLine[0] == "noop"){
            p2.changePixel()
            p2.iterateCol()
            p2.tick ++
        }
        else{
            p2.changePixel()
            p2.iterateCol()
            p2.tick ++
            p2.changePixel()
            p2.iterateCol()
            p2.tick ++
            p2.changeUsablePixels(splitLine[1].toInt())
        }
        p1.getSignalStrength(splitLine)

    }
    val endTime = System.nanoTime()
    println("Elapsed time in microseconds: ${(endTime-beginTime)/1000}")
    println(p1.answer)
    for (row in p2.answer){
        for (char in row){
            print(char)
        }
        println()
    }
}

class Part1{
    var register = 1
    var answer = 0
    var tick = 0
    val multiplayableCycles = arrayOf(20, 60, 100, 140, 180, 220)

    fun getSignalStrength(splitLine: List<String>){
        if (splitLine[0] == "noop"){
            tick ++
            if (tick in multiplayableCycles) {
                answer += register * tick
            }
        }
        else{
            tick += 2
            if (tick -1 in multiplayableCycles){
                answer += register * (tick-1)
                register += splitLine[1].toInt()
            }
            else if (tick in multiplayableCycles){
                answer += register * tick
                register += splitLine[1].toInt()
            }
            else{
                register += splitLine[1].toInt()
            }
        }
    }
}


class Part2{
    var tick = 0
    var register = 1
    var currentRow = 0
    var currentCol = 0
    var answer = Array(6){Array(40){'.'} }
    var usablePixels = arrayOf(0,1,2)

    fun changePixel(){
        if (currentCol in usablePixels){
            answer[currentRow][currentCol] = '#'
        }
    }

    fun changeUsablePixels(middlePxAdjustCount: Int){
        register += middlePxAdjustCount
        usablePixels = arrayOf(register-1, register, register+1)
    }

    fun iterateCol(){
        if (currentCol == 0){
            currentCol ++
        }
        else if (currentCol%39 == 0){
            currentRow ++
            currentCol = 0
        }
        else{
            currentCol ++
        }
    }
}