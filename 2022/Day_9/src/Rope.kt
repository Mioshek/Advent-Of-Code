class Rope {

    companion object {
        fun relocatePoints(head: Point, arr: Array<Array<Point?>>): Array<Array<Point?>> {
            arr[head.row][head.col] = head
            var p = head
            var lastPointVisited = Array<Int>(2) { 0 }
            while (true) {
                if (p.next == null) {
                    arr[lastPointVisited[0]][lastPointVisited[1]] =
                        Point(lastPointVisited[0], lastPointVisited[1], '#', null)
                }
                val next = p.next ?: break
                arr[p.next!!.row][p.next!!.col] = Point(p.next!!.row, p.next!!.col, '.', null)
                lastPointVisited = arrayOf(p.next!!.row, p.next!!.col)
                p.moveTail()
//            println("row: ${p.row}, ${p.col}")
                val tailInArr = arr[p.next?.row!!][p?.next!!.col]
                if (tailInArr?.symbol == '.') {
                    arr[p.next!!.row][p.next!!.col] = p.next
                }
                p = p.next!!
            }
//            println("nr ${p.next?.row} cr ${p.next?.col}")
//            println("r ${p.row} c ${p.col}")
            return arr
        }
    }
}

