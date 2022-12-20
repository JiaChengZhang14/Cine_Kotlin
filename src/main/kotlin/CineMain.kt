@file:Suppress("UNUSED_EXPRESSION")

import enums.ESTADOS.RESERVADO
import models.Butaca


var totalCash: Double = 0.0
fun main() {
    val row = requestRowSize()
    val column = requestColumnSize()
    val seatsMatrix = Array(row) { Array<Butaca?>(column) { null } }
    placeSeats(seatsMatrix)
    val reservation = reverseSeat(seatsMatrix, column, row)
    processReservation(reservation, seatsMatrix)
}

fun processReservation(reservation: String, seatsMatrix: Array<Array<Butaca?>>) {
    val processedReservation = reservation.split(":").toTypedArray()
    val selectedRow = processedReservation[0]
    val selectedColumn = processedReservation[1]
    val processedRow = rowLetterToNumber(selectedRow)
    changeSeatStatusToReserved(seatsMatrix, selectedColumn, processedRow)
    printSeats(seatsMatrix)


}

fun changeSeatStatusToReserved(seatsMatrix: Array<Array<Butaca?>>, selectedColumn: String, processedRow: Int): Array<Array<Butaca?>> {
    val reservedSeat = Butaca(estado = RESERVADO)
    seatsMatrix[processedRow][selectedColumn.toInt() - 1] = reservedSeat
    return seatsMatrix
}

private fun rowLetterToNumber(selectedRow: String): Int {
    when (selectedRow.uppercase()) {
        "A" -> return 0
        "B" -> return 1
        "C" -> return 2
        "D" -> return 3
        "E" -> return 4
        "F" -> return 5
        "G" -> return 6
        "H" -> return 7
        "I" -> return 8
        "J" -> return 9
        "K" -> return 10
        "L" -> return 11
        "M" -> return 12
        "N" -> return 13
        "O" -> return 14
        "P" -> return 15
        "Q" -> return 16
        "R" -> return 17
        "S" -> return 18
        "T" -> return 19
        "U" -> return 20
        "V" -> return 21
        "W" -> return 22
        "X" -> return 23
        "Y" -> return 24
        "Z" -> return 25
    }
    return -1
}

fun reverseSeat(seatsMatrix: Array<Array<Butaca?>>, column: Int, row: Int): String {
    var reservedSeat = ""
    val regex = """[A-Z][:][0-9]+""".toRegex()
    printSeats(seatsMatrix)
    println("Hola! ¿Que asiento quieres reservar? Estas son las butacas disponibles.")
    println("Las filas se ordenan con las letras del abecedario y las columnas con números, un ejemplo de asiento seria B:4")
    println("que representaría el asiento de la segunda fila y la cuarta columna")
    do {
        reservedSeat = readln()
        if (!regex.matches(reservedSeat.uppercase())) {
            println("Debes el introducir la LETRA de la fila y el NÚMERO de la columna. Ejemplo: A:1 ")
        }
        if (regex.matches(reservedSeat.uppercase())) {
            if (!doesColumnExist(reservedSeat, column)){
                println("La columna que has elegido no existe, elige otro asiento.")
            }
        }
        if (regex.matches(reservedSeat.uppercase())) {
            if (!doesRowExist(reservedSeat, row)){
                println("La fila que has elegido no existe, elige otro asiento.")
            }
        }

    } while (!regex.matches(reservedSeat.uppercase()) || !doesColumnExist(reservedSeat, column))
    totalCash += 5.25
    println("La entrada son 5,25€, ya se te ha hecho el cobro automáticamente")
    return reservedSeat
}

fun doesRowExist(reservedSeat: String, row: Int): Boolean {
    val filteredRow = reservedSeat.split(":").toTypedArray()
    val rowNumber = rowLetterToNumber(filteredRow[0])
    return rowNumber <= row
}

fun doesColumnExist(reservedSeat: String, column: Int): Boolean {
    val filteredColumn = reservedSeat.split(":").toTypedArray()
    return filteredColumn[1].toInt() <= column

}


fun placeSeats(seatsMatrix: Array<Array<Butaca?>>) {
    for (i in seatsMatrix.indices) {
        for (j in seatsMatrix[i].indices) {
            seatsMatrix[i][j] = Butaca()
        }
    }
}

fun printSeats(seatsMatrix: Array<Array<Butaca?>>) {
    for (i in seatsMatrix.indices) {
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


