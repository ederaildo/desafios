package com.desafio.crawlers.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.desafio.crawlers.CrawlersBot;

/**
 * @author Ederaildo Fontes
 *
 */
@SpringBootApplication
public class CrawlersApplication {

	public static void main(String[] args) {	
		SpringApplication.run(CrawlersApplication.class, args);
	}
}