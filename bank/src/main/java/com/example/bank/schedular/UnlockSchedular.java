package com.example.bank.schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.bank.repository.UserRepository;

@Component
public class UnlockSchedular {
	
	@Autowired
	UserRepository repo;

	@Scheduled(cron = "0 0 0 * * ?")
	public void scheduleFetchVideosMetadataFromYouTube() {
		repo.unlockUsers(0);
	}
	
}
