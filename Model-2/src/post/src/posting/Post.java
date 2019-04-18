package posting;

/*
 * Created by marcrossi@umass.edu
 */

import java.sql.Timestamp;
import java.util.ArrayList;

public class Post 
{
	user poster;
	String postID;
	String text;
	int flag = 0;
	int pointsForPost = 5;
	Timestamp timestamp;
	ArrayList<Comment> comments; 
	ArrayList<String> hashtags;
	ArrayList<String> adminHashtags;
	 
	/* The constructor for post will take in a User class variable, a String that 
	 * is the unique post id of that post and the string of text associated with 
	 * the post.
	 * @param thePoster the user object who the post belongs to
	 * @param postID the unique post ID associated with this post
	 * @param text the text to be populated in the UI 
	 */
	public Post(user poster, String postID, String text) 
	{
		this.poster = poster;
		this.postID = postID;
		this.text = text;
		
		comments = new ArrayList<Comment>();	
		hashtags = new ArrayList<String>();
		adminHashtags = new ArrayList<String>();
		
		this.createTimeStamp();
		this.addPoints();
		this.parseForHashtags();
	}
	
   /* This function will parse the text for hashtags 
	* @param none
	* @return an array of the hashtags found in the text
	*/
	void parseForHashtags()
	{
		String words[] = text.split(" ");
		for(String word: words)
		{
			if(word.charAt(0)=='#') 
			{
				hashtags.add(word);
			}
		}
	}
	
   /* Removes one hashtag from the arraylist of hashtags
	* @param toBeRemoved the string to be removed from arraylist
	* @return returns true if it worked and false if not found or fail
	*/
	boolean removeHashtag(String tag) 
	{
		if(adminHashtags.contains(tag)) 
		{
			adminHashtags.remove(tag);
			return true;
		}
		return false;
	}
	
   /* Add a hashtag to the arraylist of hashtags
    * @param toBeAdded the string to be added to the arraylist
	* @return returns a boolean to indicate the success or failure of the action
	*/
	boolean addHashtag(String tag) 
	{
		if(adminHashtags.contains(tag)) 
		{
			return false;
		}
		adminHashtags.add(tag);
		return true;	
	}
	
   /* The removeComments method will remove a comment from the arraylist
	* @param none
	* @return a boolean to confirm the success or fail of this action
	*/
	boolean removeComment(Comment comment) 
	{
		if(comments.contains(comment)) 
		{
			comments.remove(comment);
			return true;
		}
		return false;
	}
	
   /* The addComments method will add an object of type comment to the arrayList  
	* of type comments
	* @param comment the comment to be added
	* @return a boolean communicating the success of the addition
	*/
	boolean addComment(Comment comment) 
	{
		if(comments.contains(comment)) 
		{
			return false;
		}
		comments.add(comment);
		return true;	
	}
	
   /* The getUser method will return the user object of the poster
	* @param none
	* @return an object of type user that is the user who created the post
	*/
	user getUser() 
	{
		return poster;
	}
	
	/*
	 * allows for flaggin a post
	 * @param int 0 = no flag, 1 = sent to mod, 2 = sent to owner
	 * @return none 
	 */
	void setFlag(int flag)
	{
		this.flag = flag;
	}
	
   /* getText will return the text of the post
	* @params none
	* @return a string containing the text of the post
	*/
	String getText() 
	{
		return text;
	}
	
   /* The setText class will set the text of the post to the string
    * @param newText will be new value of the post’s text 
	* @return a boolean indicating success
	*/
	void setText(String newText) 
	{
		text = newText;
		this.parseForHashtags();
	}
	
   /* getPostId will return the unique postId
	* @param none
	* @return a string containing the unique postID
	*/
	String getPostID()
	{
		return postID;
	}
	
   /* The createTimestamp method will create a Timestamp to help with 
	* sorting of posts.
	* @params none
	* @return none;
	*/
	void createTimeStamp()
	{
		timestamp = new Timestamp(System.currentTimeMillis());
	}
	
   /* The compareTimestamps method will compare this post’s timestamp with      
	* another post’s timestamp and return true if this time is newer than the 
	* incoming post.
	* @param post a post to compare the timestamp of to this object’s timestamp
	* @return boolean if this timestamp is newer than the object passed in
	*/
	boolean compareTimes(Post otherPost)
	{
		if(this.timestamp.compareTo(otherPost.timestamp) < 0)
		{
			return true;
		}
		return false;
	}
	
   /* Adds Points to the users point stack
    * @params none
    * @return a boolean indicating success or failure
    */
	void addPoints() 
	{
		this.poster.addPoints(this.pointsForPost);
		return;
	}
}