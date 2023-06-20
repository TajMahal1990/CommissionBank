fun main() {
    println("Приложение подчета коммисии")
    print(amountFee(10_000, 75_000, "Maestro"))
    print(" рублей составит коммисия")
}
val errorDay = -1 // Лимит в день
val errorMon = -2 // Лимит в месяц
fun amountFee(
        amount: Int,
        amountMonth: Int = 0,
        TCards: String = "VK Pay"
): Int = when {
    TCards == "VK Pay" && amount > 15_000 -> errorDay // Сначала считает вк пэй в лимите
    TCards == "VK Pay" && amountMonth > (40_000 - amount) -> errorMon
    TCards != "VK Pay" && amount > 150_000 -> errorDay
    TCards != "VK Pay" && amountMonth > (600_000 - amount) -> errorMon
    else -> when { // потом уже переходим на мастеркард, визу и маэстро
        (TCards == "Mastercard" || TCards == "Maestro") && amountMonth > (75000 - amount) -> amount * 6 / 10 / 100 + 20
        (TCards == "Visa" || TCards == "МИР") && (amount * 75 / 100 / 100) >= 35 -> amount * 75 / 100 / 100
        (TCards == "Visa" || TCards == "МИР") && (amount * 75 / 100 / 100) =< 35 -> 35
        else -> 0
    }
}

