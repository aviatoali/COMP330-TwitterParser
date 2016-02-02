package TwitterParser;
import java.util.regex.*;

public class BasicLineParser {

	
		public static void main(String[] args)
		{	
			String tweet = "@franky goes to #hollywood. See http://cnn.com.";
			//String mention;
			//String hashtag;
			//usernames are 1-15 chars, letters, numbers and underscores only, case blind
			tweetChecker("(@[A-Za-z0-9_]{1,15})|(#[A-Za-z0-9_]{1,139})| ([A-Za-z:/]+\\.[A-Za-z0-9_%-.]{1,139})", tweet);
		}
		
		public static void tweetChecker(String toFind, String toCheck)
		{
			Pattern checkTweet = Pattern.compile(toFind);
			Matcher findMatch = checkTweet.matcher(toCheck);
			
			while(findMatch.find())
			{
				if(findMatch.group().length() !=0)
				{
					System.out.println(findMatch.group().trim());
				}
				System.out.println("Start index: " + findMatch.start());
				System.out.println("End index: " + findMatch.end());
			}
			
		}
}