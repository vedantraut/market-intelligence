package com.qode.market_intelligence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {
	
	  @Value("${twitter.api.consumerKey}")
	  private String consumerKey;

	  @Value("${twitter.api.consumerSecret}")
	  private String consumerSecret;

	  @Value("${twitter.api.accessToken}")
	  private String accessToken;

	  @Value("${twitter.api.accessTokenSecret}")
	  private String accessTokenSecret;

	  @Bean
	  public Twitter twitterClient() {
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(false)
	      .setOAuthConsumerKey(consumerKey)
	      .setOAuthConsumerSecret(consumerSecret)
	      .setOAuthAccessToken(accessToken)
	      .setOAuthAccessTokenSecret(accessTokenSecret)
	      // optional: be explicit about v1.1 endpoints
	      .setTweetModeExtended(true); // get full text
	    return new TwitterFactory(cb.build()).getInstance();
	  }

}
