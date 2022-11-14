package br.com.felipe.gadelha.coffeandit.transactionbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import reactor.blockhound.BlockHound;

@SpringBootApplication
@EnableRedisRepositories
public class TransactionBffApplication {

	public static void main(String[] args) { SpringApplication.run(TransactionBffApplication.class, args); }
}
