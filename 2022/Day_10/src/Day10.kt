
fun main(){
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day10.txt"
    val beginTime = System.nanoTime()
    val lines = readLines(cpath)
    var register = 1
    var partOne = 0
    var tick = 0
    val multiplayableCycles = arrayOf(20, 60, 100, 140, 180, 220)

    for(line in lines){
        val splitLine = line.split(" ")
        if (splitLine[0] == "noop"){
            tick ++
            if (tick in multiplayableCycles) {
                partOne += register * tick
            }

            continue
        }
        else{
            tick += 2
            if (tick -1 in multiplayableCycles){
                partOne += register * (tick-1)
                register += splitLine[1].toInt()
            }
            else if (tick in multiplayableCycles){
                partOne += register * tick
                register += splitLine[1].toInt()
            }
            else{
                register += splitLine[1].toInt()
            }
        }
    }
    println(partOne)
}