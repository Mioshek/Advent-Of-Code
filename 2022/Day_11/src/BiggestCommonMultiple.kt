class BiggestCommonMultiple {
    var bcm = 1

    companion object{
        fun findBiggest(numbers: MutableList<Int>): Int {
            println(numbers)
            var multiplicators = mutableListOf<Int>()
            val biggestPotentialMultiple = numbers.min()/2
            if (biggestPotentialMultiple < 2 || numbers.size < 1){
                return 1
            }

            for (i in 1..biggestPotentialMultiple){
                multiplicators.add(i)
            }
            multiplicators.add(numbers.min())
            for (num in numbers){
                for (i in 0..biggestPotentialMultiple){
                    if ((num % multiplicators[i.toInt()]).toInt() != 0){
                        multiplicators[i.toInt()] = 1
                    }
                }
            }
            return multiplicators.max().toInt()
        }
    }

}