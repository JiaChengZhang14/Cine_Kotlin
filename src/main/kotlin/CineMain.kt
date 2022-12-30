

import enums.ESTADOS.LIBRE
import enums.ESTADOS.RESERVADO
import models.Butaca

val FREE_SEAT = Butaca(estado = LIBRE)
val RESERVED_SEAT = Butaca(estado = RESERVADO)

var totalCash: Double = 0.0
fun main() {
    val row = requestRowSize()
    val column = requestColumnSize()
    val seatsMatrix = Array(row) { Array<Butaca?>(column) { null } }
    placeSeats(seatsMatrix)
    //Función que reserva sitios
    val reservation = reverseSeat(seatsMatrix, column, row)
    processReservation(reservation, seatsMatrix)
    //Función que cancela la reserva de un sitio que se elija.
    val toBeCancelledSeat = cancelReservation(reservation, seatsMatrix, column, row)
    processCancellation(toBeCancelledSeat, seatsMatrix)

}

fun processCancellation(toBeCancelledSeat: String, seatsMatrix: Array<Array<Butaca?>>) {
    val processedCancellation = toBeCancelledSeat.split(":").toTypedArray()
    val selectedRow = processedCancellation[0]
    val selectedColumn = processedCancellation[1]
    val processedRow = rowLetterToNumber(selectedRow)


    changeSeatStatusToFree(seatsMatrix, selectedColumn, processedRow)
    printSeats(seatsMatrix)
}

fun changeSeatStatusToFree(seatsMatrix: Array<Array<Butaca?>>, selectedColumn: String, processedRow: Int): Array<Array<Butaca?>> {

    seatsMatrix[processedRow][selectedColumn.toInt()-1] = FREE_SEAT
    return seatsMatrix
}

fun cancelReservation(reservation: String, seatsMatrix: Array<Array<Butaca?>>, column: Int, row: Int): String {

    var toBeCancelledSeat = ""
    val regex = """[A-Z][:][0-9]+""".toRegex()

    println("Introduce el asiento que has reservado, para que podamos cancelar la reserva por usted: ")
    do {

        do {
            toBeCancelledSeat = readln()
            if (!regex.matches(toBeCancelledSeat.uppercase())) {
                println("Debes el introducir la LETRA de la fila y el NÚMERO de la columna. Ejemplo: A:1 ")
            }
            if (regex.matches(toBeCancelledSeat.uppercase())) {
                if (!doesColumnExist(toBeCancelledSeat, column)){
                    println("La columna que has elegido no existe, elige otro asiento.")
                }
            }
            if (regex.matches(toBeCancelledSeat.uppercase())) {
                if (!doesRowExist(toBeCancelledSeat, row)){
                    println("La fila que has elegido no existe, elige otro asiento.")
                }
            }

        } while (!regex.matches(toBeCancelledSeat.uppercase()) || !doesColumnExist(toBeCancelledSeat, column))

        // Desde aquí hacia arriba, nos aseguramos de que el asiento que el usuario ha elegido está escrito de la manera que queremos y que está dentro de los límites de la matriz de asientos.
        if (!isSeatReserverd(toBeCancelledSeat, seatsMatrix)){
            println("El asiento que has elegido, no ha sido reservado anteriormente, elige otro: ")
        }

    }while (!isSeatReserverd(toBeCancelledSeat, seatsMatrix))
    println("Has cancelado la reserva correctamente")
    totalCash -= 5.25
    return toBeCancelledSeat
}

fun isSeatReserverd(toBeCancelledSeat: String, seatsMatrix: Array<Array<Butaca?>>):Boolean {
    val aux = toBeCancelledSeat.split(":").toTypedArray()
    val auxRow = aux[0]
    val processedRow = rowLetterToNumber(auxRow)
    val auxColumn = aux[1]
    return seatsMatrix[processedRow][auxColumn.toInt()-1] == RESERVED_SEAT

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

    seatsMatrix[processedRow][selectedColumn.toInt() - 1] = RESERVED_SEAT
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


