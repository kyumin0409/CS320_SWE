
import javax.imageio.ImageIO;

import java.sql.*;
import java.util.ArrayList;


public class DBAdapter {
	

	private String DBAddress = "jdbc:mysql://localhost:3306/TrackOneDB"; //access address for database. figure out l8tr
	
	
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
	private class User {
		//Instance variables
		private String name;
		private String email;
		private String password;
		private String type;
		private boolean loggedIn;
		private int ID;
		
		//Constructor
		public User(String name, String email, String password, String type, boolean loggedIn) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.type = type;
			this.loggedIn = loggedIn;
		}

		
	}
	
	//Admin class
	private class Admin extends User{
		//Constructor
		public Admin(String name, String email, String password, String type, boolean loggedIn) {
			super(name, email, password, type, loggedIn);
			// TODO Auto-generated constructor stub
		}
	}
	
	private class Owner extends User{
		//Constructor
		public Owner(String name, String email, String password, String type, boolean loggedIn) {
			super(name, email, password, type, loggedIn);
			// TODO Auto-generated constructor stub
		}
	
		
	}
	
	//Content parent class
	private class Content{
		//Variables
		private int id;
		private int flag;
		private String text;
		private int author;
		private String timeStamp;
		
		//Constructor
		public Content(int flag, String text, int author, String timeStamp) {
			this.flag = flag;
			this.text = text;
			this.author = author;
			this.timeStamp = timeStamp;
		}

		//Setters and Getters
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public int getAuthor() {
			return author;
		}

		public void setAuthor(int author) {
			this.author = author;
		}

		public String getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}	
	}
	
	//Comment class
	private class Comment extends Post{
		//Variables
		private int parent;

		//Constructor
		public Comment(int flag, String text, int author, String timeStamp, Comment[] comments, int parent) {
			super(flag, text, author, timeStamp, comments);
			this.parent = parent;
		}
		
		//Setters and Getters
		public int getParent() {
			return parent;
		}

		public void setParent(int parent) {
			this.parent = parent;
		}
		
		
	}
	
	//Post class
	private class Post extends Content{
		//Variables
		private Comment[] comments;

		//Constructor
		public Post(int flag, String text, int author, String timeStamp, Comment[] comments) {
			super(flag, text, author, timeStamp);
			this.comments = comments;
		}

		//Setters and Getters
		public Comment[] getComments() {
			return comments;
		}

		public void setComments(Comment[] comments) {
			this.comments = comments;
		}		
	}
	
	//Photo class
	private class Photo{
		//Variables
		private int photoId;
		private String photoPath;
		
		//Constructor
		public Photo(int photoId, String photoPath) {
			super();
			this.photoId = photoId;
			this.photoPath = photoPath;
		}

		//Setters and Getters
		public int getPhotoId() {
			return photoId;
		}

		public void setPhotoId(int photoId) {
			this.photoId = photoId;
		}

		public String getPhotoPath() {
			return photoPath;
		}

		public void setPhotoPath(String photoPath) {
			this.photoPath = photoPath;
		}
	}
	
	//Filter class
	private class Filter extends Photo{
		//Variables
		private int xPos;
		private int yPos;
		private boolean visibleToUser;
		
		//Constructor
		public Filter(int photoId, String photoPath, int xPos, int yPos, boolean visibleToUser) {
			super(photoId, photoPath);
			this.xPos = xPos;
			this.yPos = yPos;
			this.visibleToUser = visibleToUser;
		}
		
		//Setters and Getters
		public int getxPos() {
			return xPos;
		}

		public void setxPos(int xPos) {
			this.xPos = xPos;
		}

		public int getyPos() {
			return yPos;
		}

		public void setyPos(int yPos) {
			this.yPos = yPos;
		}

		public boolean isVisibleToUser() {
			return visibleToUser;
		}

		public void setVisibleToUser(boolean visibleToUser) {
			this.visibleToUser = visibleToUser;
		}	
	}
	
	//ImagePost class
	private class ImagePost extends Post{
		//Variables
		private Photo photo;
		private Filter[] filters;
		
		//Constructor
		public ImagePost(int flag, String text, int author, String timeStamp, Comment[] comments, Photo photo,
				Filter[] filters) {
			super(flag, text, author, timeStamp, comments);
			this.photo = photo;
			this.filters = filters;
		}

		//Setters and Getters
		public Photo getPhoto() {
			return photo;
		}

		public void setPhoto(Photo photo) {
			this.photo = photo;
		}

		public Filter[] getFilters() {
			return filters;
		}

		public void setFilters(Filter[] filters) {
			this.filters = filters;
		}
	}
	
	//URL class
	private class URL{
		//Variable
		private String shortURL;
		private String longUrl;
		
		//Constructor
		public URL(String shortURL, String longUrl) {
			super();
			this.shortURL = shortURL;
			this.longUrl = longUrl;
		}

		//Setters and Getters
		public String getShortURL() {
			return shortURL;
		}

		public void setShortURL(String shortURL) {
			this.shortURL = shortURL;
		}

		public String getLongUrl() {
			return longUrl;
		}

		public void setLongUrl(String longUrl) {
			this.longUrl = longUrl;
		}
	}
	
	//Mod class
	private class Mod extends User{
		//Constructor
		public Mod(String name, String email, String password, String type, boolean loggedIn) {
			super(name, email, password, type, loggedIn);
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
		public Member(String name, String email, String password, String type, boolean loggedIn, String firstName,
				String lastName, String address1, String address2, String zipCode, String state, String stripeID,
				int phoneNum, int ccNum, int ccSec, int ccExpiryMonth, int ccExpiryYear, int points) {
			super(name, email, password, type, loggedIn);
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
		
		//Setters and Getters
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getStripeID() {
			return stripeID;
		}

		public void setStripeID(String stripeID) {
			this.stripeID = stripeID;
		}

		public int getPhoneNum() {
			return phoneNum;
		}

		public void setPhoneNum(int phoneNum) {
			this.phoneNum = phoneNum;
		}

		public int getCcNum() {
			return ccNum;
		}

		public void setCcNum(int ccNum) {
			this.ccNum = ccNum;
		}

		public int getCcSec() {
			return ccSec;
		}

		public void setCcSec(int ccSec) {
			this.ccSec = ccSec;
		}

		public int getCcExpiryMonth() {
			return ccExpiryMonth;
		}

		public void setCcExpiryMonth(int ccExpiryMonth) {
			this.ccExpiryMonth = ccExpiryMonth;
		}

		public int getCcExpiryYear() {
			return ccExpiryYear;
		}

		public void setCcExpiryYear(int ccExpiryYear) {
			this.ccExpiryYear = ccExpiryYear;
		}

		public int getPoints() {
			return points;
		}

		public void setPoints(int points) {
			this.points = points;
		}
	}



	public boolean createUser(User usr){
		//stub
		Connection conn;
		try {
			conn = getConnection();
			int log = 0;
			if(usr.loggedIn)
				log = 1;
			int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.User (email, name,password,type,loggedIn) VALUES ('"+usr.email+"','" +usr.name+"','" +usr.password+"','"+ usr.type+"',"+log+")");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public User getUser(String email) { 
		//general function- returns entire user.
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String type = rs.getString("type");
				int loggedIn = rs.getInt("loggedIn");
				boolean log = false;
				if(loggedIn == 1) {
					log = true;
				}
				User usr = new User(name,email,password,type,log);
				return usr;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public String getUserType(String email) {
		//stub
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String type = rs.getString("type");
				return type;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserType(String email, String type) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET type = '"+type+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getUserName(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String name = rs.getString("name");
				return name;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserName(String email, String name) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET name = '"+name+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUserAddress(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String address = rs.getString("address");
				return address;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserAddress(String email, String address) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET address = '"+address+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUserPhone(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String phone = rs.getString("phone");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserPhone(String email, String phone) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET phone = '"+phone+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUserBirthday(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String birthday = rs.getString("birthday");
				return birthday;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean setUserBirthday(String email, String bday) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET birthday = '"+bday+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int getUserPoints(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int points = rs.getInt("points");
				return points;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	public boolean setUserPoints(String email, int amt) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET points = '"+amt+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String[] getUserFollowing(String email) {
		//returns the user's followers' email addresses in an array
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			int userID=0;
			while(rs.next()) {
				userID = rs.getInt("userID");	
			}
			rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Follow WHERE userID =" + userID);
			ArrayList<Integer> followerID = new ArrayList<Integer>();
			while(rs.next()) {
				followerID.add(rs.getInt("follow"));
			}
			String[] followers = new String[followerID.size()];
			for(int i=0;i<followerID.size();i++) {
				rs=conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + followerID.get(i).toString());
				while(rs.next()) {
					String fEmail = rs.getString("email");
					followers[i] = fEmail;
				}
			}
			return followers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addUserFollowing(String userEmail, String followerEmail) {
		//adds the new follower of the user to db
		Connection conn;
		
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+userEmail+"'");
			int userID = 0;
			while(rs.next()) {
				userID = rs.getInt("userID");
			}
			rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+followerEmail+"'");
			int followerID = 0;
			while(rs.next()) {
				followerID = rs.getInt("userID");
			}
			rs = conn.createStatement().executeQuery("SELECT Count(*) FROM TrackOneDB.Follow WHERE userID = " + userID + " AND follow = "+ followerID);
			int count = 0;
			while(rs.next()) {
				count = rs.getInt("Count(*)");
			}
			if(count>0) {
				return true;
			}else {
				int r = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Follow (userID, follow) VALUES ("+userID+","+followerID+")");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUserInviter(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String inviterEmail = rs.getString("inviter");
				return inviterEmail;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserInviter(String userEmail, String inviterEmail) {
Connection conn;
		
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+userEmail+"'");
			int userID = 0;
			while(rs.next()) {
				userID = rs.getInt("userID");
			}
			rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+inviterEmail+"'");
			int inviterID = 0;
			while(rs.next()) {
				inviterID = rs.getInt("userID");
			}
			
			int ra = conn.createStatement().executeUpdate("UPDATE TrackOneDB.User SET inviter = '"+inviterEmail +"' WHERE userID =" + userID);
			
			rs = conn.createStatement().executeQuery("SELECT Count(*) FROM TrackOneDB.Invite WHERE invitee = " + userID + " AND inviter = "+ inviterID);
			int count = 0;
			while(rs.next()) {
				count = rs.getInt("Count(*)");
			}
			if(count>0) {
				return true;
			}else {
				int r = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Invite (inviter, invitee) VALUES ("+inviterID+","+userID+")");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String[] getUsersInvited(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			int userID=0;
			while(rs.next()) {
				userID = rs.getInt("userID");	
			}
			rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Invite WHERE inviter =" + userID);
			ArrayList<Integer> inviteeID = new ArrayList<Integer>();
			while(rs.next()) {
				inviteeID.add(rs.getInt("invitee"));
			}
			String[] invitees = new String[inviteeID.size()];
			for(int i=0;i<inviteeID.size();i++) {
				rs=conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + inviteeID.get(i).toString());
				while(rs.next()) {
					String inEmail = rs.getString("email");
					invitees[i] = inEmail;
				}
			}
			return invitees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	public boolean setUsersInvited(int id) {
		//stub
		return true;
	}//isn't this basically the same thing as setUserInviter..?
	*/
	public boolean getUserHasInvited(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int hasInvited = rs.getInt("hasInvited");
				boolean boolInvited = false;
				if(hasInvited == 1)
					boolInvited = true;
				return boolInvited;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean setUserHasInvited(String email, boolean status) {
		Connection conn;
		try {
			conn = getConnection();
			int hasInvited;
			if(status==false) {
				hasInvited = 0;
			}
			else {
				hasInvited = 1;
			}
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET hasInvited = '"+hasInvited+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*
	public JSONObject getUserCard(int id) {
		//stub
		return null;
	}
	
	public boolean setUserCard(int id, JSONObject card) {
		//stub
		return true;
	}
	*/
	public boolean getUserCanLogin(String email) {
		//responses to validAccount attribute in DB
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int valid = rs.getInt("validAccount");
				boolean canLogin = false;
				if(valid==1)
					canLogin = true;
				return canLogin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean setUserCanLogin(String email, boolean status) {
		//responses to validAccount attribute in DB
		Connection conn;
		try {
			conn = getConnection();
			int canLogin;
			if(status==false) {
				canLogin = 0;
			}
			else {
				canLogin = 1;
			}
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET validAccount = '"+canLogin+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean getUserPrivate(String email){
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int privacy = rs.getInt("private");
				boolean priv = false;
				if(privacy==1)
					priv = true;
				return priv;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean setUserPrivate(String email, boolean status) {
		Connection conn;
		try {
			conn = getConnection();
			int privacy;
			if(status==false) {
				privacy = 0;
			}
			else {
				privacy = 1;
			}
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET private = '"+privacy+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int[] getUserPosts(String email) {
		//returns post IDs, not post
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			int userID=0;
			while(rs.next()) {
				userID = rs.getInt("userID");	
			}
			rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE userID =" + userID);
			ArrayList<Integer> postID = new ArrayList<Integer>();
			while(rs.next()) {
				postID.add(rs.getInt("postID"));
			}
			int[] arr = new int[postID.size()];
			for(int i =0; i<postID.size(); i++) {
				arr[i] = postID.get(i);
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	public boolean setUserPosts(int id, int[] posts) {
		//stub
		return true;
	}
	*///i dont think we need this method anymore. this can be handled in post table and create post methods
	
	
	public boolean deleteUser(String email) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.User WHERE email = '" + email + "'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/*
	public boolean snap() {
		//deletes half of all users
		return true;
	}
	

	/*
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
	*/
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBAdapter adapter = new DBAdapter();
		adapter.getConnection();
		User user1 = adapter.new User("Caroline Kim","ckim9@umass.edu","rbals1997!","Member",true);
		User user2 = adapter.getUser("ckim98@umass.edu");
		//adapter.setUserType("ckim9@umass.edu", "admin");
		
	}

}
