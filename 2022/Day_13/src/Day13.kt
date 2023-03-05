import org.jetbrains.annotations.TestOnly
import java.util.Objects
import kotlin.math.exp

fun main(){
    val inputFile =  System.getProperty("user.dir") + "/2022/inputs/Day13.txt"
    val lines = readLines(inputFile)
    var answerOne = 0
    var firstPacket = ""
    var secondPacket = ""
    var currentArray: List<Objects>
    var lineIndex = 0


    comparePackets("[5,0]", "[5,[[0],6,[7,8,7,5],[4,8,7,7],10],[0,9,[4,9,9,6,3],[6],4],[[8,9],3,[]]],[[]],[0,[[10,10,5,8,5],2,7,0,[3]],[2,[4,6,5,1,6],[7,10,10,4],7]]", false);
    comparePackets("[[5,[[0],6,[7,8,7,5],[4,8,7,7],10],[0,9,[4,9,9,6,3],[6],4],[[8,9],3,[]]],[[]],[0,[[10,10,5,8,5],2,7,0,[3]],[2,[4,6,5,1,6],[7,10,10,4],7]]]", "[[5,0],[[[6,3,5],[3],[8,1,5],5,9],[6],[2,0],2,[10]],[]]", false);
    comparePackets("[[6,[6,[1,8],[8,10,0,8,5]],[[7],7]],[[],0,[[8,9,8],1,3,[]]]]","[[[[6,2,6,4,5],3,[0],[9]],4],[],[5,1,[[]],[3,[10,1,10,5,10]]]]", true);
    comparePackets("[[],[5,3]]","[[2,[]]]", true);
    comparePackets("[[],[[[0,3,2,6],7],8,5,5],[1],[[6,6,10,10],9,[[9,1],[6,0,2,10],[0,7,1,1,2]],[0,[],[8,6,5,6],[9,8,6],8]],[0,[],[[],[7,10,10]]]]", "[[2,[[9,2,7],2,[4,8,5],4],0,[[7,4,0,9],3]]]", true);
    comparePackets("[[]]", "[[[]]]", true);
    comparePackets("[3,6]", "[[2],[7]]", false);
    comparePackets("[[[]]]", "[[]]", false);
    comparePackets("[[7],8]", "[[],9]", false);
    comparePackets("[7,7,7,7]", "[7,7,7]", false);
    comparePackets("[]", "[3]", true);
    comparePackets("[1,1,3,1,1]", "[1,1,5,1,1]", true);
    comparePackets("[[1],[2,3,4]]", "[[1],4]", true);
    comparePackets("[9]", "[[8,7,6]]", false);
    comparePackets("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]", false);
    comparePackets("[1,2,3]", "[1,2,4]", true);
    comparePackets("[1,2,3]", "[1,2,2]", false);
    comparePackets("[1,831,12]", "[2,3,4]", true);


    for (line in lines){
        if (lineIndex % 3 == 0){
            firstPacket = line
        }
        else if (lineIndex % 3 == 1){
            secondPacket = line
        }
        else{
            if (isOrdered(firstPacket, secondPacket)){
                answerOne += lineIndex/3 + 1
//                println(lineIndex/3 + 1)
            }
        }
        lineIndex ++
    }
//    println(answerOne)
}

fun isNotNumber(chr:Char): Boolean {
    if (chr == '[' || chr == ']' || chr == ','){
        return true
    }
    return false
}

fun isWithin(len1: Int, index1: Int, len2: Int, index2: Int): Boolean {
    if(index1 < len1 && index2 < len2){
        return true
    }
    return false
}

fun isOrdered(firstPacket: String, secondPacket: String): Boolean {
    var firstPacketLen = firstPacket.length
    var firstPacketOpenedBrackets = 0
    var secondPacketOpenedBrackets = 0
    var secondPacketLength = secondPacket.length
    var fpi = 0 //FirstPacketIndex
    var spi = 0 //SecondPacketIndex
    while (fpi < firstPacketLen && spi < secondPacketLength){
        if (isNotNumber(firstPacket[fpi])){fpi++}
        if (isNotNumber(secondPacket[spi])){spi++}
        else if (isWithin(firstPacketLen, fpi, secondPacketLength, spi) && firstPacket[fpi] > secondPacket[spi]){
            return false
        }
        else if(isWithin(firstPacketLen, fpi, secondPacketLength, spi) && firstPacket[fpi] == secondPacket[spi]){
            fpi++
            spi++
        }
        else if (isWithin(firstPacketLen, fpi, secondPacketLength, spi) && firstPacket[fpi] < secondPacket[spi]){
            return true
        }
        else{
            fpi++
            spi++
        }
    }
    if (spi == secondPacketLength){return false}
    return true
}

fun comparePackets(firstPacket: String, secondPacket: String, expected: Boolean) {
    if (isOrdered(firstPacket, secondPacket) == expected){
        println("$firstPacket  &  $secondPacket, Passed")
        return
    }
    println("$firstPacket  &  $secondPacket, Did not pass")
}
