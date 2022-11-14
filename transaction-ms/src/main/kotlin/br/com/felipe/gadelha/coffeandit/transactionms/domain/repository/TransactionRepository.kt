package br.com.felipe.gadelha.coffeandit.transactionms.domain.repository

import br.com.felipe.gadelha.coffeandit.transactionms.domain.entity.Transaction
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: ReactiveMongoRepository<Transaction, String>