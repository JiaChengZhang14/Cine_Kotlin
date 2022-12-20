package models

import enums.ESTADOS

// El estado base de las butacas es libre.
data class Butaca(var estado: ESTADOS = ESTADOS.LIBRE){

    override fun toString(): String {
        return when(Butaca(estado)){
            Butaca(estado = ESTADOS.LIBRE) -> "L"
            Butaca(estado = ESTADOS.RESERVADO) -> "R"
            Butaca(estado = ESTADOS.OCUPADO) -> "O"
            else -> ({ println("Fatal Error!!!!")}).toString()
        }
    }
}


