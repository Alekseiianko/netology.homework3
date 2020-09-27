import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun checkCommission_MOM() {
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_MASTERCARD_OR_MAESTRO

        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )

        assertEquals(commission, 0);
    }

    @Test
    fun checkCommission_MOMwithCommission() {
        val pastPaymentsOfMonth = 80000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_MASTERCARD_OR_MAESTRO

        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )

        assertEquals(commission, 80);
    }

    @Test
    fun checkCommission_VOM() {
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_VISA_OR_MIR

        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )

        assertEquals(commission, 75);
    }

    @Test
    fun checkCommission_DEF() {
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_DEFAULT

        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )

        assertEquals(commission, 0);
    }

    @Test
    fun sendMoney_MOM(){
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_MASTERCARD_OR_MAESTRO
        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )
        var resultMoney = payments - commission

        val result = "Отправлено: $payments . Получено: $resultMoney ."

        assertEquals(result, "Отправлено: 10000 . Получено: 10000 .")

    }

    @Test
    fun sendMoney_MOMError(){
        val pastPaymentsOfMonth = 500000
        val payments = 160000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_MASTERCARD_OR_MAESTRO
        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )

        val result = "Превышен лимит. Перевод невозможен."

        assertEquals(result, "Превышен лимит. Перевод невозможен.")

    }

    @Test
    fun sendMoney_VOM(){
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_VISA_OR_MIR
        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )
        var resultMoney = payments - commission

        val result = "Отправлено: $payments . Получено: $resultMoney ."

        assertEquals(result, "Отправлено: 10000 . Получено: 9925 .")

    }

    @Test
    fun sendMoney_VOMError(){
        val pastPaymentsOfMonth = 500000
        val payments = 180000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_VISA_OR_MIR
        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )
        var resultMoney = payments - commission

        val result = "Превышен лимит. Перевод невозможен."

        assertEquals(result, "Превышен лимит. Перевод невозможен.")

    }

    @Test
    fun sendMoney_DEF(){
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_DEFAULT
        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )
        var resultMoney = payments - commission

        val result = "Отправлено: $payments . Получено: $resultMoney ."

        assertEquals(result, "Отправлено: 10000 . Получено: 10000 .")

    }

    @Test
    fun sendMoney_DEFError(){
        val pastPaymentsOfMonth = 30000
        val payments = 20000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_DEFAULT
        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )
        var resultMoney = payments - commission

        val result = "Превышен лимит. Перевод невозможен."

        assertEquals(result, "Превышен лимит. Перевод невозможен.")

    }

}