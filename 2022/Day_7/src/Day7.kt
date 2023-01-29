import com.day7.commands.activateConsole
import java.lang.Exception

class Directory(var parent: Directory?, var name: String){
    var totalSize = 0
    var dirSize = 0
    var directories: MutableList<Directory> = mutableListOf()
    var files:HashMap<String, Int> = HashMap<String,Int>()
    var visited: Boolean = false

    override fun toString(): String {
        return this.name
    }

    fun addFile(fileName:String, size:Int) {
        this.files[fileName] = size
    }

    fun addDir(dir: Directory) {
            this.directories.add(dir)
    }

    fun visit(){ this.visited = true }

    fun getChildDirs(): MutableList<Directory> {
        return this.directories
    }

    fun calculateValue(fileSize: Int){
        dirSize += fileSize
        totalSize += fileSize
//        println(files.values)
        var tempParent: Directory? = parent
        while (tempParent != null){
            tempParent?.totalSize = tempParent?.totalSize?.plus(fileSize)!!
            tempParent = tempParent?.parent
        }
    }
}


fun getLastElement(currentPath: String): String {
//    println("currentPath: $currentPath")
    return currentPath.split("/").dropLast(1).last()
}

class SystemFile(cpath: String){
    val totalDiskSpace = 70_000_000
    val reqSpaceToUpdate = 30_000_000
    private val lines: List<String> = readLines(cpath)
    private var currentPath = "/"
    var startDir = Directory(null, currentPath)
    private var currentDir: Directory? = startDir

    init {
        this.startDir = generateSystemFile()
    }

    fun generateSystemFile(): Directory {
        for (line in lines.drop(1)){
            val command = line.split(" ")

            if (command[1] == "cd"){
                if (command[2] == ".."){
                    if (currentPath != "/"){
                        val lastElement = getLastElement(currentPath)
                        currentPath = currentPath.replace("$lastElement/", "")
                        if (currentDir != null) { currentDir = currentDir!!.parent }
                    }
                    else{
                        currentPath = "/"
                        if (currentDir?.parent == null){
                            continue
                        }
                        else{
                            currentDir = currentDir!!.parent
                        }
                    }
                }
                else{
                    if (currentDir?.directories?.toString()?.contains(command[2]) == true){
                        currentPath = currentPath + command[2] + "/"
                        for (element in currentDir?.directories!!){
                            if (element.name == command[2]){
                                currentDir = element
                            }
                        }
                        continue
                    }
                    else{ // may be unreachable tbh #TODO
                        val lastElement = getLastElement(currentPath)
                        val newDir = Directory(currentDir, lastElement)
                        currentDir?.directories?.add(newDir)
                        currentDir = newDir
                    }
                }
            }
            else if (command[0] == "dir"){
                if (currentDir?.directories?.toString()?.contains(command[1]) == true){
                    continue
                }
                else{
                    currentDir?.addDir(Directory(currentDir, command[1]))
                }
            }
            else if(command[1] == "ls"){
                continue
            }
            else{
//                println(command)
                currentDir?.addFile(command[1],command[0].toInt())
                currentDir?.calculateValue(command[0].toInt())

            }
        }
        return this.startDir
    }

    fun checkVal(dir: Directory): Int {
        var folderSize = 0
        if (!dir.visited){
            if (dir.totalSize <= 100_000){
                folderSize = dir.totalSize
                dir.visited = true
            }
        }
        for (dir in dir.directories){
            folderSize += checkVal(dir)
        }
        return folderSize
    }
}

class PartTwo(var toFree: Int, private val sys: SystemFile){
    var enough: List<Int> = listOf<Int>()

    init {
        toFree = sys.reqSpaceToUpdate - toFree
        val startdir = sys.startDir.directories
        checkToDelete(startdir)
    }

    fun checkToDelete(dirs: MutableList<Directory>){
        for (dir in dirs){
            if (dir.totalSize > this.toFree){
                enough += dir.totalSize
                try {
                    checkToDelete(dir.directories)
                }
                catch (e: Exception){
                    continue
                }

            }
        }
    }
}


fun main() {
    val cpath = System.getProperty("user.dir") + "/2022/inputs/Day7.txt"
    val beginTime = System.nanoTime()
    val sysFile = SystemFile(cpath)
    val usedSpace = sysFile.checkVal(sysFile.startDir)// Part 1
    val toDelete = PartTwo(sysFile.totalDiskSpace - sysFile.startDir.totalSize, sysFile)
    println(toDelete.enough.min())
    val endTime = System.nanoTime()
    println("Elapsed time in microseconds: ${(endTime-beginTime)/1000}")
    activateConsole(sysFile.startDir)

}