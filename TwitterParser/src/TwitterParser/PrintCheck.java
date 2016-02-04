package TwitterParser;

//Just a side class to check Tweet object method calls. Ignore
public class PrintCheck {
	
		public static void main(String[] args)
		{
			String message = "@franky goes to #hollywood. See http://cnn.com. sshah37@gmail.com";	
			Tweet tweet = new Tweet(message);
			System.out.println(tweet.getMessage());
			System.out.println("Users mentioned in tweet: " + tweet.getMentions());
			System.out.println("Hashtags: " + tweet.getHashtags());
			System.out.println("Emails: " + tweet.getEmails());
			System.out.println("Website URLs: " + tweet.getURLs());
			System.out.println(tweet.getReply());
		}
		
}