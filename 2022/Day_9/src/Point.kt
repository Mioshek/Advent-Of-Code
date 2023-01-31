import kotlin.math.abs

class Point(var row: Int, var col: Int, var symbol: Char, var next: Point?){

    init {
        this.symbol = symbol
        this.row = row
        this.col = col
        this.next = next
    }

    fun moveUp(): Point? {
        this.row --
        return this
    }
    private fun moveUpRight(): Point? {
        this.row --
        this.col ++
        return this
    }
    fun moveRight(): Point? {
        this.col ++
        return this
    }
    private fun moveDownRight(): Point? {
        this.row ++
        this.col ++
        return this
    }
    fun moveDown(): Point? {
        this.row ++
        return this
    }
    private fun moveDownLeft(): Point? {
        this.row ++
        this.col --
        return this
    }
    fun moveLeft(): Point? {
        this.col --
        return this
    }
    private fun moveUpLeft(): Point? {
        this.row --
        this.col --
        return this
    }

    fun moveTail(): Point{

        if (abs(this.row - this.next!!.row) == 1 && abs(this.col - this.next?.col!!) > 1){
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
            return this
        }
        else if (abs(this.row - this.next?.row!!) > 1 && abs(this.col - this.next?.col!!) == 1){
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
            return this
        }
        else if (abs(this.row - this.next?.row!!) > 1){
            if (this.row > next?.row!!){
                this.next?.moveDown()
            }
            else{
                this.next?.moveUp()
            }
            return this
        }
        else if (abs(this.col - next?.col!!) > 1) {
            if (this.col > next?.col!!) {
                this.next?.moveRight()
            }
            else {
                this.next?.moveLeft()
            }
            return this
        }
        return this
    }

}