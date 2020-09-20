import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun checkCommission() {
        val pastPaymentsOfMonth = 5000
        val payments = 10000
        val paymentsWithPast = payments + pastPaymentsOfMonth
        val typeOfCard = TYPE_VISA_OR_MIR

        val commission =  checkCommission(
            typeOfCard = typeOfCard,
            payments = payments,
            paymentsWithPast = paymentsWithPast
        )

        assertEquals(commission, 76);
    }

}