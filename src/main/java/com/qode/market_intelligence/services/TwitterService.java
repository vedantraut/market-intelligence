package com.qode.market_intelligence.services;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qode.market_intelligence.model.Tweet;

import twitter4j.*;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TwitterService {
	
	@Value("${scraper.nitter.base-url}")
	private String nitterUrl;
	
//	private final Twitter twitter;

//	  @Value("${tweets.default.query}")
//	  private String defaultQuery;
//
//	  @Value("${tweets.search.lang:en}")
//	  private String defaultLang;
	  
//	  public TwitterService(Twitter twitter) {
//	        this.twitter = twitter;
//	    }
	  
	  public List<Tweet> fetchByHashtag(String hashtag) {
	        List<Tweet> tweets = new ArrayList<>();

	        String url = nitterUrl + URLEncoder.encode(hashtag, StandardCharsets.UTF_8);

	        try {
	        	Document doc = Jsoup.connect(url)
	        		    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36")
	        		    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
	        		    .header("Accept-Encoding", "gzip, deflate, br")
	        		    .header("Accept-Language", "en-US,en;q=0.9")
	        		    .timeout(10000)
	        		    .get();

	        		System.out.println(doc.outerHtml());


	            Elements items = doc.select("div.timeline-item");
	            for (Element el : items) {
	                String username = el.select("a.username").text();
	                String timestamp = el.select("span.tweet-date > a").attr("title");
	                String content = el.select("div.tweet-content").text();

	                // Basic hashtag/mention extraction
	                List<String> hashtags = el.select("div.tweet-content a[href*='%23']")
	                    .eachText();
	                List<String> mentions = el.select("div.tweet-content a[href*='/']")
	                    .eachText();

//	                tweets.add(new Tweet(username, timestamp, content, hashtags, mentions));
	                tweets.add(new Tweet(username, timestamp, content, hashtags, mentions));
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return tweets;
	    }
	 
	 /*
	public List<Tweet> searchTweets(String queryParam, Integer maxCount, boolean excludeRetweets) {
	    String q = (queryParam == null || queryParam.isBlank()) ? defaultQuery : queryParam;
	    int target = (maxCount == null || maxCount <= 0) ? 200 : Math.min(maxCount, 18000); // guardrail
	    List<Tweet> out = new ArrayList<>();

	    try {
	      Query query = new Query(q);
	      query.setCount(100);              // max per page for v1.1
	      query.setLang(defaultLang);       // optional filter by language
	      // Note: v1.1 doesn't honor since/until reliably; we'll post-filter by time.

	      Instant cutoff = Instant.now().minus(Duration.ofHours(24)); // last 24h

	      long lastMaxId = Long.MAX_VALUE;

	      while (out.size() < target) {
	        if (lastMaxId != Long.MAX_VALUE) {
	          query.setMaxId(lastMaxId - 1); // paginate backwards
	        }

	        QueryResult result = twitter.search(query);
	        List<Status> statuses = result.getTweets();

	        if (statuses == null || statuses.isEmpty()) break;

	        for (Status s : statuses) {
	          // Skip retweets if requested
	          if (excludeRetweets && s.isRetweet()) continue;

	          Instant created = s.getCreatedAt().toInstant();
	          if (created.isBefore(cutoff)) {
	            // We've gone past 24h window; stop paging further
	            return out;
	          }

	          List<String> hashtags = new ArrayList<>();
	          if (s.getHashtagEntities() != null) {
	            Arrays.stream(s.getHashtagEntities()).forEach(h -> hashtags.add("#" + h.getText()));
	          }

	          List<String> mentions = new ArrayList<>();
	          if (s.getUserMentionEntities() != null) {
	            Arrays.stream(s.getUserMentionEntities()).forEach(m -> mentions.add("@" + m.getScreenName()));
	          }

	          String text = s.getText();
	          if (s.isRetweet() && s.getRetweetedStatus() != null && s.getRetweetedStatus().getText() != null) {
	            // If tweet_mode=extended is not effective in your env, consider s.getText() vs getRetweetedStatus().getText()
	            text = s.getText();
	          }

	          Tweet dto = new Tweet(
	            s.getId(),
	            s.getUser().getScreenName(),
	            s.getUser().getName(),
	            text,
	            created,
	            s.getRetweetCount(),
	            s.getFavoriteCount(),
	            s.getLang(),
	            hashtags,
	            mentions
	          );
	          out.add(dto);

	          if (out.size() >= target) break;

	          // Track lowest ID for pagination
	          if (s.getId() < lastMaxId) lastMaxId = s.getId();
	        }

	        // Handle rate limits if needed
	        if (result.hasNext()) {
	          query = result.nextQuery();
	        } else {
	          break;
	        }
	      }

	    } catch (TwitterException te) {
	      // Rate-limit friendly handling
	      RateLimitStatus rl = te.getRateLimitStatus();
	      if (rl != null) {
	        int wait = rl.getSecondsUntilReset() + 2;
	        throw new RuntimeException("Rate limited. Retry after ~" + wait + " seconds", te);
	      }
	      throw new RuntimeException("Twitter API error: " + te.getErrorMessage(), te);
	    }

	    return out;
	  }	
	*/
}
