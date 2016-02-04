package TwitterParser;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class to define a Tweet as an object with components: message(stored as String message) and specials @, #, emails, URLs(stored in hashmap of key string for category , and value arraylist of strings for contents)
public class Tweet 
{
	//stores tweet status (reply or not), mentioned users, hashtags, linked website urls and e-mails, and the tweet message itself
	private final HashMap<String, ArrayList<String>> specials = new HashMap<String, ArrayList<String>>();
	//stores full, unedited tweet message
	private String message;
	
	//default constructor
	public Tweet()
	{
		message = "";
	}
	
	//parameterized constructor. Takes in string for full message, then calls Tweet class tweetChecker method to parse matches for specials and organize them into specials hashmap 
	public Tweet(String message)
	{
		this.message = message;
		//Check @ special char			
		tweetChecker("(\\S)?([^A-Za-z0-9])?(@[A-Za-z0-9&&[^\\.]]{1,15})\\s");
		
		//Check # special char
		tweetChecker("#[A-Za-z0-9]{1,139}");
		
		//Check URL special char
		tweetChecker("([A-Za-z&&[^@]]{4,7}://)?(www.)?([A-Za-z0-9]+)?(@?[A-Za-z0-9_.]{1,138})\\.([A-Za-z]{2,3})");
	}
	
	//For use with default constructor. Takes in string containing entire unedited tweet message. If message is empty from default construction, sets message to passed String value 
	public void setMessage(String message)
	{
		if(message != null && !message.isEmpty()&& this.message == "")
		{
			this.message = message;
		}
		else
		{
			System.out.println("Error: could not store message for tweet.");
		}
	}

	//Takes in string for username without "@"symbol and a string for username's status as a mention or reply. Adds users to hashmap of tweet's specials, keeping replies separate from mentions. Still need to add Retweets to this, forgot about them
	public void addAtSign(String user, String replyOrMention)
	{
		if(user != null && !user.isEmpty() && replyOrMention != null && !replyOrMention.isEmpty())
		{
			//check if the user is mentioned, and if so, add them as a mention to tweet's specials hashmap 
			if(replyOrMention == "mention")
			{
				//If mentions category doesn't exist in tweet's specials hashmap, create one and add this mentioned user to it, else add to existing mentions category
				if(!specials.containsKey("mentions"))
				{
					specials.put("mentions", new ArrayList<String>());
					specials.get("mentions").add(user);
				}
				else
				{
					specials.get("mentions").add(user);
				}
			}
			else //if passed user isn't a mention and is a reply instead, then add as reply instead of mention in tweet's specials hashmap
			{
				if(!specials.containsKey("reply")) //if tweet's specials hashmap doesn't contain a category for reply, make one and add user to it, else add user to existing reply category
				{
					specials.put("reply", new ArrayList<String>());
					specials.get("reply").add(user);
				}
				else
				{
					specials.get("reply").add(user);
				}
			}
		}
		else
		{
			System.out.println("Error: Could not add reply or mention to list for tweet");
		}
	}
	
	//Takes in a string containing the hashtag topic, without "#" symbol. Adds topics hashtagged in tweet to the tweet's specials hashmap under a hashtags category
	public void addHashTags(String hashtag)
	{
		if(hashtag != null && !hashtag.isEmpty())
		{
			if(!specials.containsKey("#")) //checks if the tweet's specials hashmap contains a hashtag category already, and adds topic to it, if category doesn't exist, creates one and adds topic to it. 
			{
				specials.put("#", new ArrayList<String>());
				specials.get("#").add(hashtag);
			}
			else
			{
				specials.get("#").add(hashtag);
			}
		}
		else
		{
			System.out.println("Error: could not add hashtag to list of hashtags in tweet");
		}
	}
	
	//Takes in a string for the link and a string for link's status as an email address or website URL. Adds URLs and emails as separate categories to tweet's specials hashmap
	public void addLink(String link, String emailOrUrl)
	{
		if(link != null && !link.isEmpty())
		{
			if(emailOrUrl == "URL") //if the passed link is to a website then continue
			{
				if(!specials.containsKey("URLs"))//checks if tweet's specials hashmap contains a URL category already and adds passed link to it, if it doesn't have URL category, creates URL category and adds link to it
				{
					specials.put("URLs", new ArrayList<String>());
					specials.get("URLs").add(link);
				}
				else
				{
					specials.get("URLs").add(link);
				}
			}
			else//if passed link isn't a URL, must be email so checks if tweet's specials hashmap contains email category already and adds passed link to it, if category doesn't exist, creates it and adds link to it
			{
				if(!specials.containsKey("emails"))
				{
					specials.put("emails", new ArrayList<String>());
					specials.get("emails").add(link);
				}
				else
				{
					specials.get("emails").add(link);
				}
			}
		}
		else
		{
			System.out.println("Error: links could not be added to tweet");
		}
	}
	
	//accesses tweet's specials hashmap to find all topics stored in value string arraylist at key "#" and returns them as a String.
	public String getHashtags()
	{
		String tagList = "";
		if(specials.containsKey("#"))
		{
			for(String tag : specials.get("#"))
			{
				tagList += (tag + " ");
			}
			return tagList.substring(0, tagList.length()-1);
		}
		else
		{
			return "There are no hashtags included in this tweet.";
		}
	}
	
	//accesses tweet's specials hashmap to find value usernames under mention key, concatenates them to a string, then returns that string 
	public String getMentions()
	{
		String mentionList = "";
		
		if(specials.containsKey("mentions")) //Checks if there are any mentions in this tweet in the first place by checking tweet's specials hashmap for mention key
		{
			for(String mention : specials.get("mentions"))  //iterates over list of values under mention key and concatenates them to string to be returned
			{
				mentionList += (mention + " ");
			}
			
			return mentionList.substring(0, mentionList.length()-1);
		}
		else
		{
			return "There are no users mentioned in this tweet";
		}
	}
	
	//accesses tweet's specials hashmap to see if there are any value users under mentions key, and counts them to return how many mentions in the tweet
	public int getMentionCount()
	{
		int mentions = 0;
		
		if(specials.containsKey("mentions"))
		{
			for(String s: specials.get("mentions"))
			{
				mentions++;
			}
		}
		
		return mentions;
	}
	
	//access tweet's specials hashmap to find all value URLs under key URLs and concatenates them to a string to return
	public String getURLs()
	{
		String urlList = "";
		
		if(specials.containsKey("URLs"))
		{
			for(String url : specials.get("URLs"))
			{
				urlList += (url + " ");
			}
			return urlList.substring(0, urlList.length()-1);
		}
		else
		{
			return "There are no URLs included in this tweet.";
		}
	}
	
	//accesses tweet's specials hashmap to find all value emails under key email and concatenates them to a string to return
	public String getEmails()
	{
		String emailList = "";
		
		if(specials.containsKey("emails"))
		{
			for(String email : specials.get("emails"))
			{
				emailList += (email + " ");
			}
			return emailList.substring(0, emailList.length()-1);
		}
		else
		{
			return "There are no emails included in this tweet.";
		}
	}
	
	//check if this particular tweet is a reply or not through Tweet class isReply() method. If it's a reply, grabs value user from key reply in tweet's specials hashmap and returns string about tweet's status
	public String getReply()
	{
		if(isReply())
		{
			return ("This tweet is a reply to " + specials.get("reply"));
		}
		else
		{
			return "This tweet is not a reply to anyone";
		}
	}
	
	//accesses tweet's specials hashmap to see if a reply key exist, if it does, marks true that this tweet is a reply, if key reply doesn't exist, marks false that this tweet is not a reply
	public Boolean isReply()
	{
		if(specials.containsKey("reply"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//accesses tweet's message and returns it as a string
	public String getMessage()
	{
		return this.message;
	}
	
	//parses the lines of the message and uses regex patterns to match to separate out tweet components (users, reply, mentions, hashtag, emails, URLs) and organize them into tweet's specials hashmap. 
	public void tweetChecker(String toFind)
	{
		
		Pattern checkTweet = Pattern.compile(toFind);
		Matcher findMatch = checkTweet.matcher(this.message);
	
		while(findMatch.find())
		{
		
			if(findMatch.group().length() !=0)
			{
				if(findMatch.group().startsWith("#"))//Checks for hashtags to separate out and add to tweet's specials hashmap as value under key "#"
				{
					addHashTags(findMatch.group().substring(findMatch.group().indexOf("#") + 1).trim());
				}
			
				if(findMatch.group().contains("@")) //Checks for matches that contain "@"
				{
					if(findMatch.group().endsWith(".com")) //Those that contain a .com ending are classified as email and added to tweet's specials hashmap. Still need to expand this to include other postfixes, like .edu, .org, etc...
					{
						addLink(findMatch.group().trim(), "email");
					}
					else //Those that don't contain a .com are categorized as replies or mentions
					{
						if(findMatch.group().startsWith("@") && findMatch.start() == 0) //if the @ as at the beginning of a post, it can only be a reply, so adds username associated to tweet's specials hashmap as reply.
						{
							addAtSign(findMatch.group().substring(findMatch.group().indexOf("@") + 1).trim(), "reply");
						}
						else  //if the @ is elsewhere in the tweet it is a mention, so adds username associate to tweet's specials hashmap as mention 
						{
							addAtSign(findMatch.group().substring(findMatch.group().indexOf("@") + 1).trim(), "mention");
						}
						
					}
						
				}
				//If a match ends in.com, but doesn't contain an @ it isn't an email, it's a URL, so this adds it to the tweets specials hashmap as a URL. Still need to expand this to recognize .edu, .org and other postfixes
				if(findMatch.group().endsWith(".com") && !(findMatch.group()).contains("@"))
				{
					addLink(findMatch.group().trim(), "URL");
				}
			}
		}
	}
}

