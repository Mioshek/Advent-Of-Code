import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(){
    val inputFile = System.getProperty("user.dir") + "/2022/inputs/Day12.txt"
    val lines = readLines(inputFile)
    val height = lines.size
    val width = lines[0].length
    val field = Field(height, width)
    generateField(lines, field)
    val a = Astar(field)
    val gui = GuiFrame(field.field)

    suspend fun threads() = coroutineScope {
        launch {
            var part1 = a.findPath(gui)
            var answer = 0
            gui.hillArea.highlighter.removeAllHighlights()
            while (part1?.origin != null){
                delay(10L)
                gui.hillArea.addHighlightPath(part1.y, part1.x)
                answer++
                part1 = part1.origin
            }
            println(answer)
        }
    }

    threads()
}

fun generateField(lines: List<String>, field: Field){
    val width = field.width
    val height = field.height

    for (y in 0 until height){
        for (x in 0 until  width){
            field.field[y][x] = PathParticle(y, x, lines[y][x], null)
            if (lines[y][x] == 'S'){
                field.startParticle = PathParticle(y,x,'a', null)
            }
            if (lines[y][x] == 'E'){
                field.field[y][x] = PathParticle(y, x, 'z', null)
                field.endParticle = PathParticle(y,x,'z', null)
                PathParticle.setGoal(field.endParticle!!)
            }
        }
    }
}