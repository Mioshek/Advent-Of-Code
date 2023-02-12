fun main(){
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day11.txt"
    val lines = readLines(cpath)
    val beginTime = System.nanoTime()
    val d1 = Data(true, lines)
    d1.initializeData()
    val d2 = Data(false, lines)
    d2.initializeData()
    var commonFactor = 1
    for (monkey in d2.monkeys){
        commonFactor *= monkey.test
    }

    partOne(d1)
    partTwo(d2, commonFactor)
    var answer = Array<Int>(2){0}

    getAnswer(d1, answer)
    println(answer[0] * answer[1])
    answer = Array<Int>(2){0}
    getAnswer(d2, answer)
    val endTime = System.nanoTime()
    println("Elapsed time in microseconds: ${(endTime-beginTime)/1000}")
    println()
    println(answer[0]* answer[1])
}

fun getAnswer(d: Data, answerArr: Array<Int>){
    for (monkey in d.monkeys){
        if (monkey.inspectedItems> answerArr[0]){
            answerArr[1] = answerArr[0]
            answerArr[0] = monkey.inspectedItems
        }
        else{
            if (monkey.inspectedItems> answerArr[1]){
                answerArr[1] = monkey.inspectedItems
            }
        }
    }
}

fun partOne(d: Data){
    val roundsPartOne = 19 // starting from 0 sum is 20 rounds
    for (r in 0..roundsPartOne){
        for (monkey in d.monkeys){
            if (monkey.items.size == 0){
                continue
            }
            else{
                monkey.inspect(1)
                monkey.test()
            }
        }
    }
}

fun partTwo(d: Data, commonFactor: Int){
    val roundsPartOne = 9999 // starting from 0 sum is 10_000 rounds
    for (r in 0..roundsPartOne){
        for (monkey in d.monkeys){
            if (monkey.items.size == 0){
                continue
            }
            else{
                monkey.inspect(commonFactor)
                monkey.test()
            }
        }
    }
}

class Data(val maintableWorryLevel: Boolean,val lines: List<String>){
    val linesSize = lines.size -1
    var monkeys = mutableListOf<Monkey>()

    fun initializeData(){
        var items = mutableListOf<Long>()
        var strOperation = ""
        var test = 0
        var trueMonkey = 0
        var falseMonkey = 0
        var currentMonkeyIndex = 0
        for (index in 0.. linesSize){
            when(index%7){
                6 -> {currentMonkeyIndex ++
                    monkeys.add(Monkey(items, strOperation, test, trueMonkey, falseMonkey, monkeys, maintableWorryLevel))
                    items = mutableListOf()
                    strOperation = ""
                    test = 0
                    trueMonkey = 0
                    falseMonkey = 0
                    currentMonkeyIndex = 0
                }
                5 -> falseMonkey = lines[index].split(" ").last().last().digitToInt()
                4 -> trueMonkey = lines[index].split(" ").last().last().digitToInt()
                3 -> test = lines[index].split(" ").last().toInt()
                2 -> strOperation = lines[index]
                1 -> {
                    val line = lines[index].removePrefix("  Starting items: ").split(", ")
                    for (item in line){
                        items.add(item.toLong())
                    }
                }
                0 -> continue
            }
        }
    }
}


class Monkey(
    var items: MutableList<Long>,
    val strOperation: String,
    val test: Int,
    val trueMonkey: Int,
    val falseMonkey: Int,
    val monkeys: MutableList<Monkey>,
    val maintableWorryLevel: Boolean
){
    val operationSign = strOperation.split(" ")[6]
    var inspectedItems = 0

    fun inspect(commonFactor: Int){
        for (index in 0 until items.size){
            inspectedItems ++
            if (operationSign == "+" ){
                if (maintableWorryLevel){
                    items[index] = add(items[index])/3
                }
                else{
                    items[index] = add(items[index]) % commonFactor
//                    println(items[index])
                }

            }
            else{
                if (maintableWorryLevel){
                    items[index] = multiply(items[index])/3
                }
                else{
                    items[index] = multiply(items[index]) % commonFactor
//                    println(items[index])
                }
            }
        }
    }

    private fun add(old: Long): Long {
        return old + strOperation.split(" ").last().toLong()
    }

    private fun multiply(old: Long): Long {
        val toMultiply = strOperation.split(" ").last()
        if (toMultiply == "old"){
            return old * old
        }
        return old * toMultiply.toLong()
    }

    fun test(){
        val tmpSize = items.size
        for (i in 0 until tmpSize){
            val item = items[0]
            items.removeAt(0)
            if ((item % test).toInt() == 0){
                monkeys[trueMonkey].items.add(item)
            }
            else{
                monkeys[falseMonkey].items.add(item)
            }
        }
    }
}