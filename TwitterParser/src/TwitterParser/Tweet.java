package TwitterParser;
import java.util.*;
		
public class Tweet 
{
	
	private final HashMap<String, ArrayList<String>> specials = new HashMap<String, ArrayList<String>>();
	private String message;
	
	public Tweet()
	{
		message = "";
	}
	
	public Tweet(String message)
	{
		this.message = message;
	}
	
	public void setMessage(String message)
	{
		if(message != null && !message.isEmpty())
		{
			this.message = message;
		}
		else
		{
			System.out.println("Error: could not store message for tweet.");
		}
	}

	public void addAtSign(String user, String replyOrMention)
	{
		if(user != null && !user.isEmpty() && replyOrMention != null && !replyOrMention.isEmpty())
		{
			if(replyOrMention == "mention")
			{
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
			else
			{
				if(!specials.containsKey("reply"))
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
	
	public void addHashTags(String hashtag)
	{
		if(hashtag != null && !hashtag.isEmpty())
		{
			if(!specials.containsKey("#"))
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
	
	public void addLink(String link, String emailOrUrl)
	{
		if(link != null && !link.isEmpty())
		{
			if(emailOrUrl == "URL")
			{
				if(!specials.containsKey("URLs"))
				{
					specials.put("URLs", new ArrayList<String>());
					specials.get("URLs").add(link);
				}
				else
				{
					specials.get("URLs").add(link);
				}
			}
			else
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
	

	public String getHashTags()
	{
		String tagList = "";
		if(specials.containsKey("#"))
		{
			for(String tag : specials.get("#"))
			{
				tagList += (tag + " ");
			}
			return tagList;
		}
		else
		{
			return "There are no hashtags included in this tweet.";
		}
	}
	
	public String getMentions()
	{
		String mentionList = "";
		
		if(specials.containsKey("mentions"))
		{
			for(String mention : specials.get("mentions"))
			{
				mentionList += (mention + " ");
			}
			
			return mentionList;
		}
		else
		{
			return "There are no users mentioned in this tweet";
		}
	}
	
	public String getURLs()
	{
		String urlList = "";
		
		if(specials.containsKey("URLs"))
		{
			for(String url : specials.get("URLs"))
			{
				urlList += (url + " ");
			}
			return urlList;
		}
		else
		{
			return "There are no URLs included in this tweet.";
		}
	}
	
	public String getEmails()
	{
		String emailList = "";
		
		if(specials.containsKey("emails"))
		{
			for(String email : specials.get("emails"))
			{
				emailList += (email + " ");
			}
			return emailList;
		}
		else
		{
			return "There are no emails included in this tweet.";
		}
	}
	
	public String getReply()
	{
		if(specials.containsKey("reply"))
		{
			return ("This tweet is a reply to " + specials.get("reply"));
		}
		else
		{
			return "This tweet is not a reply to anyone";
		}
	}
	
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
	
	public String getMessage()
	{
		return this.message;
	}
	
}
