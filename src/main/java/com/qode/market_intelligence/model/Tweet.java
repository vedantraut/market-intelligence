package com.qode.market_intelligence.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
//	private long id;
//	private String username;
//	private String displayName;
//	private String text;
//	//	  private Instant createdAt;
//	private int retweetCount;
//	private int favoriteCount;
//	private String lang;
//	private List<String> hashtags;
//	private List<String> mentions;
	
	 private String username;
	 private String timestamp;
	 private String content;
	 private List<String> hashtags;
	 private List<String> mentions;

}
