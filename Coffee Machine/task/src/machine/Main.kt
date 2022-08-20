package machine

data class Drink(val water: Int, val milk: Int, val beans: Int, val price: Int)

class CoffeeMachine() {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var disCups = 9
    private var money = 550

    companion object {
        private const val BUY_COMMAND = "buy"
        private const val FILL_COMMAND = "fill"
        private const val TAKE_COMMAND = "take"
        private const val REMAINING_COMMAND = "remaining"
        private const val BACK_COMMAND = "back"
    }

    enum class MachineStatus {
        OK, NOT_ENOUGH_WATER, NOT_ENOUGH_MILK, NOT_ENOUGH_BEANS, NOT_ENOUGH_CUPS
    }

    fun processDrink(drink: Drink) {
        water -= drink.water
        milk -= drink.milk
        beans -= drink.beans
        disCups -= 1
        money += drink.price
    }

    fun checkResoures(drink: Drink): MachineStatus {
        val resources = mutableListOf<Int>(
            water - drink.water,
            milk - drink.milk,
            beans - drink.beans,
            disCups - 1
        )
        val min = resources.minOrNull() ?: 0
        if (min < 0) {
            when (resources.indexOf(min)) {
                0 -> {
                    return MachineStatus.NOT_ENOUGH_WATER
                }
                1 -> {
                    return MachineStatus.NOT_ENOUGH_MILK
                }
                2 -> {
                    return MachineStatus.NOT_ENOUGH_BEANS
                }
                3 -> {
                    return MachineStatus.NOT_ENOUGH_CUPS
                }
            }
        }

        return MachineStatus.OK
    }

    fun buy(id: Int) {
        val drinks = arrayOf<Drink>(
            Drink(250, 0, 16, 4),
            Drink(350, 75, 20, 7),
            Drink(200, 100, 12, 6),
        )

        when (checkResoures(drinks[id - 1])) {
            MachineStatus.OK -> {
                processDrink(drinks[id - 1])
                println("I have enough resources, making you a coffee!")
            }
            MachineStatus.NOT_ENOUGH_WATER -> {
                println("Sorry, not enough water!")
            }
            MachineStatus.NOT_ENOUGH_MILK -> {
                println("Sorry, not enough milk!")
            }
            MachineStatus.NOT_ENOUGH_BEANS -> {
                println("Sorry, not enough coffee beans!")
            }
            MachineStatus.NOT_ENOUGH_CUPS -> {
                println("Sorry, not enough disposable cups!")
            }
        }

    }


    fun machineInterface(command: String) {
        when (command) {
            BUY_COMMAND -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                val command = readLine()!!
                if (command == BACK_COMMAND) {
                    return
                }
                buy(command.toInt())
            }
            FILL_COMMAND -> {
                fill()
            }
            TAKE_COMMAND -> {
                take()
            }
            REMAINING_COMMAND -> {
                printStatus()
            }
        }
    }

    fun printStatus() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$disCups disposable cups")
        println("$$money of money")

    }

    fun fill() {
        println("Write how many ml of water do you want to add: ")
        water += readLine()!!.toInt()
        println("Write how many ml of milk do you want to add: ")
        milk += readLine()!!.toInt()
        println("Write how many grams of coffee beans do you want to add: ")
        beans += readLine()!!.toInt()
        println("Write how many disposable cups of coffee do you want to add: ")
        disCups += readLine()!!.toInt()
    }

    fun take() {
        println("I gave you $$money")
        money = 0
    }
}

const val EXIT_COMMAND = "exit"

fun main() {
    val machine = CoffeeMachine()
    while (true) {
        println("Write action (buy, fill, take, remaining, exit): ")
        val command = readLine()!!
        if (command == EXIT_COMMAND) {
            break
        }
        machine.machineInterface(command)
    }
}

