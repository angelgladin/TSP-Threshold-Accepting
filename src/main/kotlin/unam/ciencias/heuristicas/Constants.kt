package unam.ciencias.heuristicas

/**
 * Static variables that use the our TSP system.
 *
 */
class Constants {
    companion object {

        /** TODO */
        const val EPSILON = 10e-2
        /** TODO */
        const val EPSILON_P = 10e-2
        /** Initial system temperature */
        const val T = 8.0
        /** Upper bound for iterations when calculating a batch */
        const val L: Int = 10e3.toInt()
        /** Cooling factor that determines how slow or fast the temperature [T] it's going to decrease */
        const val PHI = 0.90
        /** Earth radius in meters */
        const val EARTH_RADIUS_IN_METERS = 6373000.0

        /**
         * TODO
         *
         * @return
         */
        fun randomPercentage(): Double {
            var aux = Math.random()
            while (aux !in 0.85..0.95)
                aux = Math.random()
            return aux
        }

    }
}

