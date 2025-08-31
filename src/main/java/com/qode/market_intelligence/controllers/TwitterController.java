package com.qode.market_intelligence.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qode.market_intelligence.model.Tweet;
import com.qode.market_intelligence.services.TwitterService;

@RestController
public class TwitterController {
	
	@Autowired
	TwitterService twitterService;
	
	@GetMapping("/fetchTweets")
	public List<Tweet> search(
		      @RequestParam(required = false) String hashtag,
		      @RequestParam(required = false, defaultValue = "200") Integer max,
		      @RequestParam(required = false, defaultValue = "true") boolean excludeRetweets) {
//		    return twitterService.searchTweets(q, max, excludeRetweets);
		
		return twitterService.fetchByHashtag(hashtag);
	}
}
