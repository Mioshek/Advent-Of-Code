class Rope {
    var lastPointVisited = Array<Int>(2) { 0 }
    var allPoints = ArrayList<String>()


    fun relocatePoints(head: Point, arr: Array<Array<Point?>>): Array<Array<Point?>> {
        arr[head.row][head.col] = head
        var p = head
        while (true) {
            if (p.next == null) {
                arr[lastPointVisited[0]][lastPointVisited[1]] =
                    Point(lastPointVisited[0], lastPointVisited[1], '#', null)
            }
            val next = p.next ?: break
            arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
            lastPointVisited = arrayOf(p.next!!.row, p.next!!.col)
            p.moveTail()
            val tailInArr = arr[p.next?.row!!][p?.next!!.col]
            if (tailInArr?.symbol == '.') {
                arr[p.next!!.row][p.next!!.col] = p.next
            }
            p = p.next!!
        }

        val strValue = "${lastPointVisited[0]}${lastPointVisited[1]}"
        if (strValue !in allPoints){
            allPoints.add(strValue)
        }
        return arr
    }

}