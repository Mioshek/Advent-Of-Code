import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(){
    val guiDebugger = true
    val showProgress = true
    val inputFile = System.getProperty("user.dir") + "/2022/inputs/Day12.txt"
    val lines = readLines(inputFile)
    val height = lines.size
    val width = lines[0].length
    val field = Field(height, width)
    generateField(lines, field)
    var gui: GuiFrame?
    var a: Astar
    if (guiDebugger){
        gui = GuiFrame(field.field)
        a = Astar(field, gui)
    }
    else{
        gui = null
        a = Astar(field)
    }

    suspend fun threads() = coroutineScope {
        launch {
            part1(a, showProgress, gui)
            //part 2
            println(part2(field, guiDebugger, showProgress, gui, a))
        }
    }
    threads()
}

suspend fun part1(a: Astar, showProgress: Boolean, gui: GuiFrame?){
    var part1 = a.findPath()
    var answer1 = 0
    if (showProgress){
        if (gui != null) {
            gui.hillArea.highlighter?.removeAllHighlights()
        }
    }
    while (part1?.origin != null){
        if (showProgress){
            delay(1L)
            gui?.hillArea?.addHighlightPath(part1.y, part1.x)
        }
        answer1++
        part1 = part1.origin
    }
    println(answer1)
}

suspend fun part2(field: Field, guiDebugger: Boolean, showProgress: Boolean, gui: GuiFrame?, a: Astar): Int {
    var partTwo = 1000
    var astar = a
    var answer2 = 0
    for (row in field.field){
        for (particle in row){
            if (particle.symbol == 'a'){
                if (guiDebugger){
                    if (!showProgress){
                        gui?.hillArea?.addHighlightPath(particle.y, particle.x)
                    }
                    else{
                        gui?.hillArea?.highlighter?.removeAllHighlights()
                    }
                }
                answer2 = 0
                field.startParticle = PathParticle(particle.y, particle.x,'a', null)
                astar = Astar(field)
                var part2 = astar.findPath()
                if (part2?.origin == null){
                    continue
                }
                while (part2?.origin != null){
                    if (showProgress){
                        delay(1L)
                        gui?.hillArea?.addHighlightPath(part2?.y!!, part2?.x!!)
                    }
                    answer2++
                    part2 = part2?.origin
                }
                if (answer2 < partTwo){
                    partTwo = answer2
                }
            }
        }
    }
    return partTwo
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