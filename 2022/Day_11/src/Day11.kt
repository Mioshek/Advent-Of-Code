fun main(){
    val d = Data()
    d.initializeData()
    val rounds = 19 // starting from 0 sum is 20 rounds
    for (r in 0..rounds){
        for (monkey in d.monkeys){
            if (monkey.items.size == 0){
                continue
            }
            else{
                monkey.inspect()
                monkey.test()
            }
        }
    }

    for (monkey in d.monkeys){
        println(monkey.inspectedItems)
    }
}

class Data{
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day11.txt"
    val lines = readLines(cpath)
    val beginTime = System.nanoTime()
    val linesSize = lines.size -1
    var monkeys = mutableListOf<Monkey>()

    fun initializeData(){
        var items = mutableListOf<Int>()
        var strOperation = ""
        var test = 0
        var trueMonkey = 0
        var falseMonkey = 0
        var currentMonkeyIndex = 0
        for (index in 0.. linesSize){
            when(index%7){
                6 -> {currentMonkeyIndex ++
                    monkeys.add(Monkey(items, strOperation, test, trueMonkey, falseMonkey, monkeys))
                    items = mutableListOf<Int>()
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
                        items.add(item.toInt())
                    }
                }
                0 -> continue
            }
        }
    }
}


class Monkey(
    var items: MutableList<Int>,
    val strOperation: String,
    val test: Int,
    val trueMonkey: Int,
    val falseMonkey: Int,
    val monkeys: MutableList<Monkey>
){
    val operationSign = strOperation.split(" ")[6]
    var inspectedItems = 0

    fun inspect(){
        for (index in 0 until items.size){
            inspectedItems ++
            if (operationSign == "+" ){
                items[index] = add(items[index])/3
            }
            else{
                items[index] = multiply(items[index])/3
            }
        }
    }

    private fun add(old: Int): Int {
        return old + strOperation.split(" ").last().toInt()
    }

    private fun multiply(old: Int): Int {
        val toMultiply = strOperation.split(" ").last()
        if (toMultiply == "old"){
            return old * old
        }
        return old * toMultiply.toInt()
    }

    fun test(){
        val tmpSize = items.size
        for (i in 0 until tmpSize){
            val item = items[0]
            items.removeAt(0)
            if (item % test == 0){
                monkeys[trueMonkey].items.add(item)
            }
            else{
                monkeys[falseMonkey].items.add(item)
            }
        }
    }
}