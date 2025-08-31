# Nitter Scraper for Nifty50 Tweets

This project is a Java-based attempt to scrape tweets related to `#nifty50` using Jsoup.  
It was built for educational purposes to understand web scraping techniques, rate-limiting issues, and possible mitigation strategies.

---

## **Approach**

1. **Tech Stack**
   - Java 17
   - Jsoup Library

2. **Plan**
   - Scrape tweets from public Nitter mirrors (like `https://nitter.net/` or `https://nitter.lacontrevoie.fr`).
   - Use custom headers and a browser-like user agent to bypass basic bot detection.

3. **Implemented Steps**
   - Built the scraper to fetch search results for `nifty50`.
   - Added user-agent headers, timeout handling, and retry attempts.
   - Tested on multiple mirrors (`nuku.trabun.org`, `nitter.lacontrevoie.fr`, etc.).

---

## **Current Status**

Unfortunately, all requests are being blocked with **HTTP 403 (Forbidden)** or **HTTP 503 (Service Unavailable)** errors due to:
- Strict anti-bot measures on Nitter mirrors.
- Rate-limiting and possibly Cloudflare-based protection.

---

## **Next Steps**

- Implement **proxy rotation** or **VPN-based IP cycling**.
- Try **official Twitter API** (even free-tier keys).
- Check for other stable mirrors or public APIs.
- Update the repo as soon as a reliable solution is achieved.

---

## **Running the Project**
```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/nitter-scraper-nifty50.git
cd nitter-scraper-nifty50

# Open in your favorite IDE (like IntelliJ) and run the main class
