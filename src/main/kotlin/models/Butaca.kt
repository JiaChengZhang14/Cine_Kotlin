package models

import enums.ESTADOS

// El estado base de las butacas es libre.
data class Butaca(val estado: ESTADOS = ESTADOS.LIBRE){

    override fun toString(): String {
        when(Butaca().estado){
            ESTADOS.LIBRE -> return "L"
            ESTADOS.OCUPADO -> return "O"
            ESTADOS.RESERVADO -> return "R"
        }
    }
}


