class City(val name: String) {
    var degrees: Int = 0
        set(value) {
            if (value < -92 || value > 57) {
                field = when (name) {
                    "Moscow" -> 5
                    "Hanoi " -> 20
                    "Dubai" -> 30
                    else -> throw java.lang.Exception("unsupported city")
                }
            } else {
                field = value
            }
        }
}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()

 /*   val first = 20
    val second = 100
    val third = 35*/

    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    val list = mutableListOf<City>(
        firstCity,
        secondCity,
        thirdCity,
    )

    val coll = list.withIndex().minByOrNull { (_, it) -> it.degrees }
    val ind = coll?.index ?: 0
    val min = coll?.value?.degrees
    val count = list.count { it.degrees == min }

    when {
        count == 1 -> { print(list[ind].name) }
        count > 1 -> { print("neither") }
    }




}