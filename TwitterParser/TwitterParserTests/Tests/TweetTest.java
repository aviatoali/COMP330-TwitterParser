/**
 * 
 */
package Tests;

import org.junit.After;
import org.junit.Before;

import TwitterParser.Tweet;
import junit.framework.TestCase;

/**
 * @author Shan-e-Ali
 *
 */
public class TweetTest extends TestCase 
{
	private Tweet fixture;
	
	@Before
	public void setUp() throws Exception
	{
		fixture = new Tweet("@franky goes to #hollywood. See http://cnn.com. sshah37@gmail.com");
	}

	@After
	public void tearDown() throws Exception 
	{
		fixture = null;
	}
	/**
	 * Test method for {@link TwitterParser.Tweet#Tweet()}.
	 */
	public void testTweet()
	{
		Tweet emptyConstructor = new Tweet();
		assertEquals("", emptyConstructor.getMessage());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#Tweet(java.lang.String)}.
	 */
	public void testTweetString() 
	{
		assertNotNull(fixture);
		assertEquals("@franky goes to #hollywood. See http://cnn.com. sshah37@gmail.com", fixture.getMessage());
	}
	/**
	 * Test method for {@link TwitterParser.Tweet#getHashTags()}.
	 */
	public void testGetHashTags()
	{
		assertNotNull(fixture);
		assertEquals("hollywood", fixture.getHashtags());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#getMentions()}.
	 */
	public void testGetMentions()
	{
		assertNotNull(fixture);
		assertEquals("There are no users mentioned in this tweet", fixture.getMentions());
	}
	
	public void testGetMentionCount()
	{
		assertNotNull(fixture);
		assertEquals(0, fixture.getMentionCount());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#getURLs()}.
	 */
	public void testGetURLs() {
		assertNotNull(fixture);
		assertEquals("http://cnn.com", fixture.getURLs());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#getEmails()}.
	 */
	public void testGetEmails() {
		assertNotNull(fixture);
		assertEquals("sshah37@gmail.com", fixture.getEmails());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#getReply()}.
	 */
	public void testGetReply() {
		assertNotNull(fixture);
		assertEquals("This tweet is a reply to [franky]", fixture.getReply());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#isReply()}.
	 */
	public void testIsReply() {
		assertNotNull(fixture);
		assertTrue(fixture.isReply());
	}

	/**
	 * Test method for {@link TwitterParser.Tweet#getMessage()}.
	 */
	public void testGetMessage() {
		assertNotNull(fixture);
		assertEquals("@franky goes to #hollywood. See http://cnn.com. sshah37@gmail.com", fixture.getMessage());
	}

}
