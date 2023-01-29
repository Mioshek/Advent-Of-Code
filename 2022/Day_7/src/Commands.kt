package com.day7.commands
//Debugger MioshBash Console
import Directory
import kotlin.system.exitProcess

fun activateConsole(system: Directory){
    var system = system
    while (true){
        val input = readLine()
        println(input)
        if (input?.startsWith("cd ..") == true){
            if (system.name != "/"){
                system = system.parent!!
                }
        }
        else if (input?.startsWith("cd") == true){
            for (dir in system.directories){
                val directoryName = input.split(" ")[1]
                if (dir.name == directoryName){
                    system = dir
                }
            }
        }
        else if (input?.startsWith("ls") == true){
            for (dir in system.directories){
                println("directory: ${dir.name}")
            }
            var fileSize = 0
            for (file in system.files){
                println("file: ${file.key}")
                fileSize += file.value
            }
            println("total file size in current folder without directories: $fileSize")
        }
        else if (input == "clear"){
            for (i in 0..20){
                println()
            }
        }
        else if (input == "exit"){
            exitProcess(0)
        }
        else if (input == "size"){
            println("DirSize = ${system.dirSize}")
            println("DirTotalSize = ${system.totalSize}")
        }

    }
}

