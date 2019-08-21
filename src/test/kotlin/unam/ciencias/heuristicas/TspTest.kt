package unam.ciencias.heuristicas

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import unam.ciencias.heuristicas.algorithm.Graph
import unam.ciencias.heuristicas.algorithm.TSP
import unam.ciencias.heuristicas.data.DbConnector
import unam.ciencias.heuristicas.model.City
import kotlin.test.assertTrue


object TspTest : Spek({
    Feature("Evaluate permutation for TSP") {

        Scenario("Parse input of the TSP instance (40 cities)") {

            val graph = Graph<Int, City>()

            val tspInstance =
                """
                1,2,3,4,5,6,7,75,163,164,165,168,172,327,329,331,332,333,489,490,491,492,493,496,652,653,654,
                656,657,792,815,816,817,820,978,979,980,981,982,984
                """.trimIndent().replace("\n", "").split(",").map { it.toInt() }

            lateinit var tsp: TSP<Int>

            Given("A database with the cities, populate the graph with them") {
                DbConnector.getCities().forEach { graph.addNode(it.id, it) }

                DbConnector.getConnectionsBetweenTwoCities()
                    .forEach { graph.addEdge(it.idCity1, it.idCity2, it.distance) }
            }

            And("Create an Instance of TSP") {
                tsp = TSP(graph, ArrayList(tspInstance))
            }

            Then("Must be the same normalizer") {
                val normalizer = 182907823.060000002384186
                assertTrue { tsp.normalizer() == normalizer }
            }

            Then("Must be the same max distance") {
                val maxDistance = 4970123.959999999962747
                assertTrue { maxDistance == tsp.maxDistance() }
            }

            And("Must be the same cost function") {
                val costFunction = 4526237.801017570309341
                assertTrue { costFunction == tsp.costFunction() }
            }

        }

        Scenario("Parse input of the TSP instance (150 cities)") {

            val graph = Graph<Int, City>()

            val tspInstance =
                """
                1,2,3,4,5,6,7,8,9,11,12,14,16,17,19,20,22,23,25,26,27,74,75,77,163,164,165,166,167,168,169,171,
                172,173,174,176,179,181,182,183,184,185,186,187,297,326,327,328,329,330,331,332,333,334,336,339,
                340,343,344,345,346,347,349,350,351,352,353,444,483,489,490,491,492,493,494,495,496,499,500,501,
                502,504,505,507,508,509,510,511,512,520,652,653,654,655,656,657,658,660,661,662,663,665,666,667,
                668,670,671,673,674,675,676,678,792,815,816,817,818,819,820,821,822,823,825,826,828,829,832,837,
                839,840,978,979,980,981,982,984,985,986,988,990,991,995,999,1001,1003,1004,1037,1038,1073,1075
                """.trimIndent().replace("\n", "").split(",").map { it.toInt() }

            lateinit var tsp: TSP<Int>

            Given("A database with the cities, populate the graph with them") {
                DbConnector.getCities().forEach { graph.addNode(it.id, it) }

                DbConnector.getConnectionsBetweenTwoCities()
                    .forEach { graph.addEdge(it.idCity1, it.idCity2, it.distance) }
            }

            And("Create an Instance of TSP") {
                tsp = TSP(graph, ArrayList(tspInstance))
            }

            Then("Must be the same normalizer") {
                val normalizer = 722989785.090000391006470
                assertTrue { tsp.normalizer() == normalizer }
            }

            Then("Must be the same max distance") {
                val maxDistance = 4978506.480000000447035
                assertTrue { maxDistance == tsp.maxDistance() }
            }

            And("Must be the same cost function") {
                val costFunction = 6210491.034747813828290

                val costFunctionWithLessPrecision = String.format("%.7f", costFunction).toDouble()
                val tspCostFunctionWithLessPrecision = String.format("%.7f", tsp.costFunction()).toDouble()

                assertTrue { costFunctionWithLessPrecision == tspCostFunctionWithLessPrecision }
            }

        }

    }
})
