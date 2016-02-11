COMP330-TwitterParser
Author: Shan-e-Ali H. Shah
Date: 2/4/16
Version: 1.0
Project ID: Twitter Parser
CS Class: COMP 330
Programming Language: Java

*Note: First time using Java so bear with me and feel free to provide tons of constructive criticism or advice
       about Java conventions, syntax or structure or anything.

Problem Description: Program inputs and parses string tweet message, separates
                     mentions, replies, emails, URL links and hashtags and stores
     		             all components plus original message for reference and printing.

Overall Design: 	
	        System structure: Tweet Class: constructor takes string and parses to split up
	                          tweet components and store them in specials hashmap of string
                            key category and string arraylist value of component itself.
              				      contains methods to get and set all tweet components, referenced by 
                            the checkTweet parser method that populates the specials container.
	 

Future Plans: 
	    Parsing: The regex patterns used for parsing the tweet to kick out matches for tweet components in Tweet class
		           method checkTweet is very basic and will probably lead to a ton of unrecognized, or erroneously recognized 
               and categorized components. One of the priorities is to refine the regex patterns with a more discerning and detailed
               system.
	    Structure: Coming into class a week late and having to catch up with a weeks worth of reading, homework and programming for all my classes 
                 led to me rushing the hell out of this project. As such, the program is very focused on fulfilling the most basic requirements and 
                 operating on data that exactly matches the example string provided by the professor. The code won't fit well into a wider 
	               implementation in a bigger program, and all its functionality is cramped into one rushed Tweet class. The plan is to 
                 create a more general message interface or abstract superclass of which TwitterMessage can be a subclass, along with a more
                 general message parsing utility class, and an interface to share some of the methods between them. 
      Data input: At present the program takes in data from a set value string in an arbitrary side class, which is extremely limited functionality.
                  Although I don't know how to even remotely do this at present, I'd like to learn to read the data in from a real twitter stream in
                  real time from Twitter itself. 
      Testing: Never done unit testing before, so my testing methods are extremely basic and don't test with any real depth the functionality of the code.
               Still learning more at present so I can make more discerning tests.  	
