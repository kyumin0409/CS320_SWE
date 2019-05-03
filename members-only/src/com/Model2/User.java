package com.Model2;

/*import post.*;
import stripe.*;
import db.*;*/
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class User {

	public String email;
	public String firstName;
	public String lastName;
	public CreditCard creditCard;
	public String stripeCreditCardID;
	/*
	public String creditCardNumber;
	public String expirationMonth;
	public String expirationYear;
	public String CVV;
	public String stripeCreditCardID;
	public String zipcode; */
	public int points;
	public String invitedBy;
	public boolean isValidated; // Is User currently verified by CC?
	public String type;
	public String description;
	public String profilePic;

	//Additional fields made by DB team
	public String address;
	public String city;
	public String state;
	public String zip;
	public String ccNum;
	public String ccv;
	public String expM;
	public String expY;
	public String birthday;
	public String phone;
	public boolean hasInvited;
	public boolean privacy;
	public boolean loggedIn;
	public String password;

	public User(String email,
				String firstName,
				String lastName,
				int points,
				String invitedBy,
				String type,
				String creditCardNumber,
				String expirationMonth,
				String expirationYear,
				String CVV,
				String zipcode,
				String profilePic) {
		this.creditCard = new StripeCreditCard(email, creditCardNumber, zipcode, CVV, expirationMonth, expirationYear);
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		this.password = "";
		this.profilePic = profilePic;
		this.stripeCreditCardID = creditCard.getId();
		this.invitedBy = invitedBy;
		this.isValidated = false;
		this.type = type;
		//this.addUserToDB();
		this.description = "";
	}

	public void addUserToDB() {
		if(Database.adapter.getUser(this.email) == null ) Database.adapter.createUser(this);
	}
	
	public boolean checkIfUserValid(double charge) {
		if(creditCard.verifyCharge(charge)) this.isValidated = true;
		return isValidated;
	}
	
	/**
	 *  Increments User point total in the DB by either a positive
	 *  or negative amount (if points are removed).
	 *
	 *  @param	points Integer representing the number of points to be
	 *                 incremented or decremented (using a negative number)
	 *  @return	void
	 */

	public void addPoints(int points) {
		Database.adapter.updateUser(email, "points", points);
	}

	/**
	 *  Charges the User’s stored credit card
	 *
	 *  @return String indicating if it is successful
	 */

	public String chargeCreditCard() {
		return creditCard.charge();
	}

	/**
	 *  Updates user's account info for any valid field in the DB
	 *
	 *  @param  fieldName	String indicating which field in the DB
	 * 						the data should be stored in
	 *  @param	data		A generic Object that is the data to be
	 * 						stored in the DB field.
	 *  @return String indicating if it is successful
	 */

	public String updateAccountInfo(String fieldName, Object data) {
		if(Database.adapter.updateUser(this.email, fieldName,data)) return (fieldName + "Account Information Successfully Updated To: " + data);
		return (fieldName + "Account Information Unsuccessfully Updated To: " + data);
	}

	public boolean updateProfileDescription(String text) {
		//Database.adapter.updateProfile(text);
		//this.description = text;
		return true;
	}

	public boolean updateProfilePhoto(String photo) {
		//Database.adapter.updateProfilePhoto(photo);
		//this.profilePic = photo;
		return true;
	}

	/**
	 *  |========================================================================|
	 *  |     Admin Functionality | TODO because none of these work	|			 |
	 *  |========================================================================|
	 *
	 */

	//edit image
	public String editImage(Post post, BufferedImage updatedImage) {
		if(this.type.equals("admin")) {
			Database.adapter.updatePost(Integer.parseInt(post.postID),"image",updatedImage);
			return "Image successfully updated";
		} else return "Image unsuccessfully updated";
	}

	//edit post hashtag
	//edit comment hashtag
	public String editHashtag(Post post, String hashtag) {
		if(this.type.equals("admin")) {
			Database.adapter.updatePost(Integer.parseInt(post.postID),"hashtag",hashtag);
			return "Hashtag successfully updated";
		} else return "Hashtag unsuccessfully updated";
	}

	public String editPost(Post post, String text) {
		if(this.type.equals("admin")) {
			Database.adapter.updatePost(Integer.parseInt(post.postID),"text",text);
			return "Post successfully updated";
		} else return "Post unsuccessfully updated";
	}
}