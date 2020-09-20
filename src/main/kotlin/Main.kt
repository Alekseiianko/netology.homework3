public const val TYPE_MASTERCARD_OR_MAESTRO = "Mastercard or Maestro"
public const val TYPE_VISA_OR_MIR = "Visa or Mir"
public const val TYPE_DEFAULT = "VK Pay"

fun main() {
    val seconds = 3200
    val minutes = seconds / 60
    val lastCharacterOfMinutes = minutes % 10
    val hours = minutes / 60
    val lastCharacter = hours % 10
    val text = getTimeAgo(seconds, lastCharacterOfMinutes, minutes, lastCharacter, hours)
    println(text)

    val pastPaymentsOfMonth = 5000
    val payments = 10000
    val paymentsWithPast = payments + pastPaymentsOfMonth

    val typeOfCard = TYPE_DEFAULT


    var commission = checkCommission(typeOfCard, payments, paymentsWithPast)
    var result = payments - commission
    val textOfSend = sendMoney(typeOfCard,payments,paymentsWithPast,commission, result)
    println(textOfSend)

}

private fun getTimeAgo(
    seconds: Int,
    lastCharacterOfMinutes: Int,
    minutes: Int,
    lastCharacter: Int,
    hours: Int
): String =
    when (seconds) {
        in 0..60 -> "Был(а) в сети только что"
        in 61..3600 -> when (lastCharacterOfMinutes) {
            1 -> if (minutes != 11) "Был(а) в сети $minutes минуту назад" else
                "Был(а) в сети $minutes минут назад"
            in 2..4 -> "Был(а) в сети $minutes минуты назад"
            in 5..9 -> "Был(а) в сети $minutes минут назад"
            else -> "Был(а) в сети $minutes минут назад"
        }
        in 3601..86400 -> when (lastCharacter) {
            0 -> "Был(а) в сети $hours часов назад"
            1 -> "Был(а) в сети $hours час назад"
            in 2..4 -> "Был(а) в сети $hours часа назад"
            else -> "Был(а) в сети $hours часов назад"
        }
        in 86401..172800 -> "Был(а) в сети сегодня"
        in 172801..259200 -> "Был(а) в сети вчера"
        else -> "Был(а) в сети давно"
    }

public fun checkCommission(
    typeOfCard:String,
    payments:Int,
    paymentsWithPast:Int
) : Int =
    when (typeOfCard) {
        TYPE_MASTERCARD_OR_MAESTRO -> if (paymentsWithPast < 75000) 0 else (payments / 100 * 0.6 + 20).toInt()

        TYPE_VISA_OR_MIR -> (payments / 100 * 0.75).toInt()

        else -> 0
    }

private fun sendMoney(
    typeOfCard:String,
    payments:Int,
    paymentsWithPast:Int,
    commission: Int = checkCommission(typeOfCard, payments, paymentsWithPast),
    result:Int
) : String =
    when (typeOfCard) {
        TYPE_MASTERCARD_OR_MAESTRO -> if (payments > 150_000 || paymentsWithPast > 600_000) {
            "Превышен лимит. Перевод невозможен."
        } else {
            "Отправлено: $payments . Получено: $result ."
        }

        TYPE_VISA_OR_MIR -> if (payments > 150_000 || paymentsWithPast > 600_000) {
            "Превышен лимит. Перевод невозможен."
        } else {
            if(commission > 35) commission else 35
            "Отправлено: $payments . Получено: $result ."
        }

        else -> if(payments > 15000 || paymentsWithPast > 40000){
            "Превышен лимит. Перевод невозможен."
        } else {
            "Отправлено: $payments . Получено: $result ."
        }

    }