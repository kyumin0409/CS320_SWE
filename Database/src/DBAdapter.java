import org.json.*;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.imageio.ImageIO;
import java.sql.*;


public class DBAdapter {

private String DBAddress = "jdbc:mysql://localhost:3306/sys"; //access address for database. figure out l8tr
	
	private Connection getConnection() throws SQLException{
		//function used to get connection to database
		Connection conn = DriverManager.getConnection(DBAddress,"root","root");
		System.out.println("Log: Connection Established!");
		return conn;
		/*ResultSet rs = conn.createStatement().executeQuery("select * from user");
		ResultSetMetaData rsmd = rs.getMetaData();
		
		System.out.println("Query Results: \n\n");
		
		while(rs.next()) {
			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				System.out.print(rs.getString(i)+"\t\t");
			}
			System.out.println();
		}*/
	}
	
	//User class
	/*private class User {
		//Instance variables
		int ID;
		private String email;
		private String password;
		private String type;
			
		//Constructor
		public User(int ID, String email, String password, String type) {
			this.ID = ID;
			this.email = email;
			this.password = password;
			this.type = type;
		}
	}*/
	
	//Admin class
	private class Admin extends User{

		public Admin(int ID, String email, String password, String type) {
			super(ID, email, password, type);
			// TODO Auto-generated constructor stub
		}
	}
	
	//Mod class
	private class Mod extends User{

		public Mod(int ID, String email, String password, String type) {
			super(ID, email, password, type);
			// TODO Auto-generated constructor stub
		}
	}
	
	//Member class
	private class Member extends User{
		//Variables
		private String firstName;
		private String lastName;
		private String address1;
		private String address2;
		private String zipCode;
		private String state;
		private String stripeID;
		private int phoneNum;
		private int ccNum;
		private int ccSec;
		private int ccExpiryMonth;
		private int ccExpiryYear;
		private int points;
		
		//Constructor
		public Member(int ID, String email, String password, String type, String firstName, String lastName,
				String address1, String address2, String zipCode, String state, String stripeID, int phoneNum, int ccNum,
				int ccSec, int ccExpiryMonth, int ccExpiryYear, int points) {
			super(ID, email, password, type);
			this.firstName = firstName;
			this.lastName = lastName;
			this.address1 = address1;
			this.address2 = address2;
			this.zipCode = zipCode;
			this.state = state;
			this.stripeID = stripeID;
			this.phoneNum = phoneNum;
			this.ccNum = ccNum;
			this.ccSec = ccSec;
			this.ccExpiryMonth = ccExpiryMonth;
			this.ccExpiryYear = ccExpiryYear;
			this.points = points;
		}
	}

/*
* USER FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS 
*/
	public boolean createUser(User usr) throws SQLException{
		//stub
		Connection conn = getConnection();
		ResultSet rs = conn.createStatement().executeQuery("INSERT INTO User (email, name,type) VALUES ("+usr.email + "," +usr.password+"," + usr.type);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		System.out.println("Query Results: \n\n");
		
		while(rs.next()) {
			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				System.out.print(rs.getString(i)+"\t\t");
			}
			System.out.println();
		}
		return true;
	}

	public JSONObject getUser(int id) { 
		//general function- returns entire user.
		//stub
		return null;
	}
	
	public boolean setUser(int id, JSONObject user) { 
		//general function - sets all fields of user based on input object.
		//stub
		return true;
	}
	
	public String getUserType(int id) {
		//stub
		return null;
	}
	
	public boolean setUserType(int id, String type) {
		//stub
		return true;
	}

	public String getUserName(int id) {
		//stub
		return null;
	}
	
	public boolean setUserName(int id, String name) {
		//stub
		return true;
	}
	
	public JSONObject getUserAddress(int id) {
		//stub
		return null;
	}
	
	public boolean setUserAddress(int id, JSONObject address) {
		//stub
		return true;
	}
	
	public int getUserPhone(int id) {
		//stub
		return 0;
	}
	
	public boolean setUserPhone(int id, int number) {
		//stub
		return true;
	}
	
	public String getUserBirthday(int id) {
		//stub
		return null;
	}
	
	public boolean setUserBirthday(int id, String bday) {
		//stub
		return true;
	}
	
	public int getUserPoints(int id) {
		//stub
		return 0;
	}
	
	public boolean setUserPoints(int id, int amt) {
		//stub
		return true;
	}
	
	public int[] getUserFollowing(int id) {
		//stub
		return null;
	}
	
	public boolean setUserFollowing(int id, int[] users) {
		//stub
		return true;
	}
	
	public int getUserInviter(int id) {
		//stub
		return 0;
	}
	
	public boolean setUserInviter(int id, int inviter) {
		//stub
		return true;
	}
	
	public int[] getUsersInvited(int id) {
		//stub
		return null;
	}
	
	public boolean setUsersInvited(int id) {
		//stub
		return true;
	}
	
	public boolean getUserHasInvited(int id) {
		//stub
		return true;
	}
	
	public boolean setUserHasInvited(int id, boolean status) {
		//stub
		return true;
	}
	
	public JSONObject getUserCard(int id) {
		//stub
		return null;
	}
	
	public boolean setUserCard(int id, JSONObject card) {
		//stub
		return true;
	}
	
	public boolean getUserCanLogin(int id) {
		//stub
		return true;
	}
	
	public boolean setUserCanLogin(int id, boolean state) {
		//stub
		return true;
	}
	
	public boolean getUserPrivate(int id){
		//stub
		return true;
	}

	public boolean setUserPrivate(int id, boolean state) {
		//stub
		return true;
	}
	
	public int[] getUserPosts(int id) {
		//returns post IDs, not post
		//stub
		return null;
	}
	
	public boolean setUserPosts(int id, int[] posts) {
		//stub
		return true;
	}
	
	public boolean deleteUser(int id) {
		//stub
		return true;
	}
	
	public boolean snap() {
		//deletes half of all users
		return true;
	}
	
/*
* POST FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public JSONObject createPost(JSONObject post) {
		//stub
		return new JSONObject();
	}

	public JSONObject getPost(int id) {
		//general function - returns entire post object.
		//stub
		return new JSONObject();
	}

	public JSONObject setPost(int id, JSONObject post) {
		//general function - sets all post fields based on input object
		//stub
		return new JSONObject();
	}
	
	public String getPostType(int id) {
		//stub
		return null;
	}
	
	public boolean setPostType(int id, String type) {
		//stub
		return true;
	}
	
	public int getPostAuthor(int id) {
		//stub
		return 0;
	}
	
	
	public boolean setPostAuthor(int id) {
		//stub
		return true;
	}
	
	public int getPostImage(int id) {
		//returns image ID, not image
		//stub
		return 0;
	}
	
	public boolean setPostImage(int id, int picId) {
		//sets image ID, not image
		//stub
		return true;
	}
	
	public JSONObject getPostUserView(int id) {
		//stub
		return null;
	}
	
	public boolean setPostUserView(int id, JSONObject data) {
		//stub
		return true;
	}
	
	public JSONObject getPostWorldView(int id) {
		//stub
		return null;
	}
	
	public boolean setPostWorldView(int id, JSONObject data) {
		//stub
		return true;
	}
	
	public boolean getPostIsExplicit(int id) {
		//stub
		return true;
	}
	
	public boolean setPostIsExplicit(int id, boolean state) {
		//stub
		return true;
	}
	
	public boolean getPostVisibility(int id) {
		//stub
		return true;
	}
	
	public boolean setPostVisibility(int id, boolean state) {
		//stub
		return true;
	}
	
	public JSONObject getPostComments(int id) {
		//stub
		return null;
	}
	
	public boolean setPostComments(int id, JSONObject comments) {
		//stub
		return true;
	}
	
	public boolean deletePost(JSONObject post) {
		//stub
		return true;
	}
	
	public JSONObject[] getPopularPosts() {
		//stub
		return null;
	}

	public JSONObject[] getFlaggedPosts() {
		//stub
		return null;
	}
	
/*
* ASSET FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public boolean createAsset(ImageIO photo) {
		//stub
		return true;
	}
	
	public ImageIO getAssetPhoto(int id) {
		//we will save image paths in the database and fetch them from disk
		//stub
		return null;
	}
	
	public boolean setAssetPhoto(ImageIO pic) {
		//stub
		return true;
	}
	
	public boolean deleteAsset(int id) {
		//stub
		return true;
	}
/*
* URL FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public boolean createURL(JSONObject url) {
		//stub
		return true;
	}
	
	public JSONObject getURL(int id) {
		//general function - returns all fields in a URL JSONObject
		//stub
		return null;
	}
	
	public boolean setURL(JSONObject url) {
		//general function - sets all fields in a URL JSONObject
		//stub
		return true;
	}
	
	public String getURLOrg(int id) {
		//stub
		return null;
	}
	
	public boolean setURLOrg(int id, String address) {
		//stub
		return true;
	}
	
	public String getURLShort(int id) {
		//stub
		return null;
	}
	
	public boolean setURLShort(int id, String address) {
		//stub
		return true;
	}
	
	public boolean deleteURL(int id) {
		//stub
		return true;
	}
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBAdapter adapter = new DBAdapter();
		adapter.getConnection();
		User user1 = new User(11,"carolinekim@gmail.com","caroline kim","Member");
		adapter.createUser(user1);
	}

}