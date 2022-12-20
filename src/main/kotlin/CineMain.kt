import enums.ESTADOS
import models.Butaca

fun main() {
    createSeats()
}

fun createSeats() {
    val row = requestRowSize()
    val column = requestColumnSize()
    val seatsMatrix = Array(row){Array<Butaca?>(column){null}}
    placeSeats(seatsMatrix)
    printSeats(seatsMatrix)

}

fun placeSeats(seatsMatrix: Array<Array<Butaca?>>) {
    for (i in seatsMatrix.indices){
        for(j in seatsMatrix[i].indices){
            seatsMatrix[i][j] = Butaca()
        }
    }
}

fun printSeats(seatsMatrix: Array<Array<Butaca?>>) {
    for(i in seatsMatrix.indices){
        println(seatsMatrix[i].contentToString())
    }

}


fun requestColumnSize(): Int {
    val regexColumn = """\d+""".toRegex()
    val minColumnSize = 1
    var column = ""
    do {
        println("¿Cuántas columnas de butacas quieres?")
        column = readln()
        if (!regexColumn.matches(column) || column.toInt() < minColumnSize) {
            println("¡EL VALOR INTRODUCIDO DEBER SER UN NUMERO ENTERO")
        }
    } while (!regexColumn.matches(column) || column.toInt() < minColumnSize)
    return column.toInt()
}

fun requestRowSize(): Int {
    val regexRow = """\d+""".toRegex()
    /*
    Vamos a nombrar las butacas con letras en la fila, por lo que el máximo valor permitido en la fila es de 26
    por ejemplo, el asiento A:1 sería la posición 1:1 en la matriz de las butacas.
     */
    val maxRowSize = 26
    val minRowSize = 1
    var fila = ""
    do {
        println("¿Cuántas filas de butacas quieres?")
        fila = readln()
        if (!regexRow.matches(fila) || fila.toInt() > maxRowSize || fila.toInt() < minRowSize) {
            println("¡EL VALOR INTRODUCIDO DEBER SER UN NUMERO ENTERO ENTRE 1 Y 26!")
        }
    } while (!regexRow.matches(fila) || fila.toInt() > maxRowSize || fila.toInt() < minRowSize)
    return fila.toInt()
}


