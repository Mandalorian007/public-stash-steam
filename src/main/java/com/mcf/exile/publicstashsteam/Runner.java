package com.mcf.exile.publicstashsteam;

import com.mcf.exile.publicstashsteam.puller.PublicStashPuller;
import com.mcf.exile.publicstashsteam.ratelimit.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Runner implements CommandLineRunner {

  @Autowired
  PublicStashPuller puller;

  @Override
  @RateLimit(1.5)
  public void run(String... strings) throws Exception {
    String result = puller.getAndSend("0");
    int count = 1;

    for(int i=0; i<200; i++) {
      count++;
      System.out.println("call #" + count);
      result = puller.getAndSend(result);
    }

    System.out.println("done");
  }
}
