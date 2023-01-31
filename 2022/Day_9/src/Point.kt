import kotlin.math.abs

class Point(var row: Int, var col: Int, var symbol: Char, var next: Point?){

    init {
        this.symbol = symbol
        this.row = row
        this.col = col
        this.next = next
    }

    fun moveUp(){
        this.row --
    }
    private fun moveUpRight(){
        this.row --
        this.col ++
    }
    fun moveRight(){
        this.col ++
    }
    private fun moveDownRight(){
        this.row ++
        this.col ++
    }
    fun moveDown(){
        this.row ++
    }
    private fun moveDownLeft(){
        this.row ++
        this.col --
    }
    fun moveLeft(){
        this.col --
    }
    private fun moveUpLeft(){
        this.row --
        this.col --
    }

    private fun moveTailDiagonally(){
        if (this.row > this.next?.row!! && this.col > this.next?.col!!){
            this.next?.moveDownRight()
        }
        else if (this.row < this.next?.row!! && this.col > this.next?.col!!){
            this.next?.moveUpRight()
        }
        else if (this.row > this.next?.row!! && this.col < this.next?.col!!){
            this.next?.moveDownLeft()
        }
        else if (this.row < this.next?.row!! && this.col < this.next?.col!!){
            this.next?.moveUpLeft()
        }
    }
    fun moveTail(){

        if (abs(this.row - this.next!!.row) == 1 && abs(this.col - this.next?.col!!) > 1){
            moveTailDiagonally()
        }
        else if (abs(this.row - this.next?.row!!) > 1 && abs(this.col - this.next?.col!!) == 1){
            moveTailDiagonally()
        }
        else if (abs(this.row - this.next?.row!!) > 1){
            if (this.row > next?.row!!){
                this.next?.moveDown()
            }
            else{
                this.next?.moveUp()
            }
        }
        else if (abs(this.col - next?.col!!) > 1) {
            if (this.col > next?.col!!) {
                this.next?.moveRight()
            }
            else {
                this.next?.moveLeft()
            }
        }
    }

}