fun tip(bill: Int, percentage: Int = 10): Int {
    return (bill.toDouble()  * percentage.toDouble() * 0.01).toInt()
}