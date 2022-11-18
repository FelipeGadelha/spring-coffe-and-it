package br.com.felipe.gadelha.coffeandit.limitms.domain.entity

enum class TransactionStatus {
    ANALYSED,
    NOT_ANALYSED,
    IN_HUMAN_ANALYSIS,
    IN_SUSPECTED_FRAUD,
    CONFIRMED_RISK
}

//enum class GatewayType {
//    PAYPAL { override fun getUri(): String { return "paypal.com?buyerId=" } },
//    PAGSEGURO { override fun getUri(): String { return "pagseguro.com?returnId=" } };
//    abstract fun getUri(): String
//}