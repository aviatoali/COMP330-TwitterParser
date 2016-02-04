package TwitterParser;
import java.util.regex.*;

public class BasicLineParser {

	final int user = 0;
	
		public static void main(String[] args)
		{
			String message = "@franky goes to #hollywood. See http://cnn.com. sshah37@gmail.com";
			//usernames are 1-15 chars, letters, numbers and underscores only, case blind
			
			Tweet tweet = new Tweet(message);
			
			
			//Check @			
			tweetChecker("(\\S)?([^A-Za-z0-9])?(@[A-Za-z0-9&&[^\\.]]{1,15})\\s", tweet);
			
			//Check #
			tweetChecker("#[A-Za-z0-9]{1,139}", tweet);
			
			//Check URL
			tweetChecker("([A-Za-z&&[^@]]{4,7}://)?(www.)?([A-Za-z0-9]+)?(@?[A-Za-z0-9_.]{1,138})\\.([A-Za-z]{2,3})", tweet);
			
			//Check class object
			System.out.println(tweet.getMessage());
			System.out.println("Users mentioned in tweet: " + tweet.getMentions());
			System.out.println("Hashtags: " + tweet.getHashTags());
			System.out.println("Emails: " + tweet.getEmails());
			System.out.println("Website URLs: " + tweet.getURLs());
			System.out.println(tweet.getReply());
		}
		
		public static void tweetChecker(String toFind, Tweet tweet)
		{
			
			Pattern checkTweet = Pattern.compile(toFind);
			Matcher findMatch = checkTweet.matcher(tweet.getMessage());
			
			while(findMatch.find())
			{
				
				if(findMatch.group().length() !=0)
				{
					if(findMatch.group().startsWith("#"))
					{
						tweet.addHashTags(findMatch.group().substring(findMatch.group().indexOf("#") + 1).trim());
					}
					
					if(findMatch.group().contains("@"))
					{
						if(findMatch.group().endsWith(".com"))
						{
							tweet.addLink(findMatch.group().trim(), "email");
						}
						else
						{
							if(findMatch.group().startsWith("@") && findMatch.start() == 0)
							{
								tweet.addAtSign(findMatch.group().substring(findMatch.group().indexOf("@") + 1).trim(), "reply");
							}
							else
							{
								tweet.addAtSign(findMatch.group().substring(findMatch.group().indexOf("@") + 1).trim(), "mention");
							}
							
						}
							
					}
		
					if(findMatch.group().endsWith(".com") && !(findMatch.group()).contains("@"))
					{
						tweet.addLink(findMatch.group().trim(), "URL");
					}
				}
			}
			
		}
}