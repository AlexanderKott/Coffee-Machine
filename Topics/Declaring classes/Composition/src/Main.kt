class OperatingSystem(
    var name: String = "Windows"
)

class DualBoot {
    var primaryOs: OperatingSystem = OperatingSystem("cat")
    var secondaryOs: OperatingSystem = OperatingSystem("dog")
}

fun sum(a: Int = 1, b: Int, c: Int = 2, d: Int = 4) = a + b + c + d

fun main(){
    println(sum(5, 6))
}