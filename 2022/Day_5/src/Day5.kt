import java.util.Stack

fun main(){
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day5.txt"
    val stackArrPart1 = Array(9){Stack<Char>()}
    val stackArrPart2 = Array(9){Stack<Char>()}
    val lines: List<String> = (readLines(cpath))
    val begin = System.nanoTime()
    
    for (i in 0..7){val chrArr = lines[i].toCharArray()
        for (i in chrArr.indices){
            if (chrArr[i].isUpperCase()){
                stackArrPart1[i/4].add(chrArr[i])
                stackArrPart2[i/4].add(chrArr[i])
            }
        }
    }
    for (line in lines){

        if (line != "" && line.startsWith("move") ){
            val movement = line.split(" ")
            var quantity = movement[1].toInt()
            val from = movement[3].toInt() -1
            val destination = movement[5].toInt() -1
            if (stackArrPart1[from].size < quantity){
                quantity = stackArrPart1[from].size
            }
            if (quantity != 0){
                for (i in 0 until quantity){
                    val toAdd1 = stackArrPart1[from].removeFirst()
                    stackArrPart1[destination].add(0,toAdd1)
                    val toAdd2 = stackArrPart2[from].removeAt(quantity-i-1)
                    stackArrPart2[destination].add(0,toAdd2)

                }
            }
        }
    }
    val end = System.nanoTime()
    println("Elapsed time in microseconds: ${(end-begin)/1000}")
    for (i in 0..8){
        print("${stackArrPart1[i][0]}")
    }
    println()
    for (i in 0..8){
        print("${stackArrPart2[i][0]}")
    }
}