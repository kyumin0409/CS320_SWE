

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DBTestSuite {

	//declare database instance and data here
	

	@Test
	public void testConnection(){
		//tests if we can successfully connect to the database.
	}
	
	@Test
	public void testUserCreation(){
		//tests if we can successfully connect to the database,
		//create a user, then fetch that user from the DB.
		
		//Also covers case where user already exists.
	}
	
	@Test
	public void testUserModification(){
		//tests if we can successfully connect to the database,
		//edit a user's data, then fetch that updated user data from the DB.
		
		//Also covers case if user does not exist.
	}
	
	@Test
	public void testUserDeletion(){
		//tests if we can successfully connect to the database,
		//delete a user, then fail to fetch that user from the DB.
		
		//Also covers case if user does not exist.
	}
	
	@Test
	public void testPostCreation() {
		//tests if we can successfully connect to the database,
		//create a post, then fetch that post from the DB.
		
		//Also covers case if the post already exists.
	}
	
	@Test
	public void testPostModification() {
		//tests if we can successfully connect to the database,
		//edit a post's data, then fetch that updated post data from the DB.
				
		//Also covers case if the post does not exist.
	}
	
	@Test
	public void testPostDeletion(){
		//tests if we can successfully connect to the database,
		//delete a post, then fail to fetch that post from the DB.
		
		//Also covers case if the post does not exist.
	}
	
	@Test
	public void testGetUserPosts(){
		//tests if we can successfully connect to the database,
		//fetch all posts by the user, and return an array of posts.
		
		//Also covers case if the user does not exist.
	}
	
	@Test
	public void testGetFlaggedPosts(){
		//tests if we can successfully connect to the database,
		//fetch all flagged posts, and return an array of posts.

	}
	
	@Test
	public void testGetRecentPosts(){
		//tests if we can successfully connect to the database,
		//fetch all recent posts, and return an array of posts.
	}
	
	@Test
	public void testGetTopPosts(){
		//tests if we can successfully connect to the database,
		//fetch all "top" posts (sorted by comment #), and return an array of posts.
	}
	
	@Test
	public void testImageCreation() {
		//tests if we can successfully connect to the database,
		//create an image entry, then fetch that image from the DB.
		
		//Also covers case if the image already exists.
	}
	
	@Test
	public void testImageModification() {
		//tests if we can successfully connect to the database,
		//edit an image's data, then fetch that updated image data from the DB.
				
		//Also covers case if the image does not exist.
	}
	
	@Test
	public void testImageDeletion() {
		//tests if we can successfully connect to the database,
		//delete an image, then fail to fetch that image's info from the DB.
				
		//Also covers case if the image does not exist.
	}
	
	@Test
	public void testImageAndFilters() {
		//tests if we can successfully connect to the database,
		//fetch an image, and return it and its filters.
				
		//Also covers case if the image does not exist.
	}
	
	
	@Test
	public void testURLCreation() {
		//tests if we can successfully connect to the database,
		//create a URL entry, then fetch that URL from the DB.
		
		//Also covers case if the URL already exists.
	}
	
	@Test
	public void testURLModification() {
		//tests if we can successfully connect to the database,
		//edit a URL's data, then fetch that updated URL data from the DB.
				
		//Also covers case if the URL does not exist.
	}
	
	@Test
	public void testURLDeletion() {
		//tests if we can successfully connect to the database,
		//delete a URL, then fail to fetch that URL's info from the DB.
				
		//Also covers case if the image does not exist.
	}
	
	public static void main(String[] args) {
		DBTestSuite test = new DBTestSuite();
	}
	
}