package com.Model2;

/*import db.*;
import stripe.*;
import user.*; */
import java.util.regex.*;

import java.awt.*;

public class Registration {
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String phoneNumber;
	private String email;
	private String zipCode;
	private String choosePassword;
	private String verifyPassword;
	private String creditCardNumber;
	private String cvv;
	private String expirationMonth;
	private String expirationYear;
	public CreditCard card;
	private String verificationCode;
	private String invitedBy;
	private String type;

	public Registration(String firstName,
						String lastName,
						String address1,
						String address2,
						String phoneNumber,
						String email,
						String zipCode,
						String choosePassword,
						String verifyPassword,
						String creditCardNumber,
						String cvv,
						String expirationMonth,
						String expirationYear,
						String verificationCode) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zipCode = zipCode;
		this.choosePassword = choosePassword;
		this.verifyPassword = verifyPassword;
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.verificationCode = verificationCode;
		this.invitedBy = Database.adapter.getUserInvite(this.verificationCode);
		this.type = "member";
	}

	/**
	 * Returns a String value indicating whether the credentials
	 * presented to the Registration class constructor from the
	 * login screen are confirmed. Specifically, confirmed means
	 * that there is no duplicate emails, and evokes the credit
	 * card processing class to ensure the proper credit card is
	 * used.
	 *
	 * Calls three methods [emailCheck(), passwordCheck(),
	 * creditCardCheck()] to verify the 3 things we need to check:
	 * is the email address unique, is the password the same as the
	 * verifyPassword field, and is the credit card chargeable.
	 *
	 * @return String indicating if all the checks pass
	 */
	public Registration verify() {
		if(!emailCheck()) return null;
		if(!passwordCheck()) return null;
		if(!zipCheck()) return null;

		if(!this.invitedBy.contains("@")) return null;

		CreditCard card = new StripeCreditCard(this.email, this.creditCardNumber, this.zipCode , this.cvv, this.expirationMonth , this.expirationYear);
		String resultCardValidation = card.verify();

		if(resultCardValidation == null) return null;
		else this.card = card;

		//Create a User object in the database.
		//NOTE: This user will still be invalid until they verify their charge.
		storeData();
		((StripeCreditCard)this.card).charge();

		return this; //Empty string is the success code according to Cole
	}

	/**
	 * Returns a boolean value indicating whether the email is
	 * unique (as in there is no database record using this specific
	 * email.
	 *
	 * @return boolean indicating if the checks pass
	 */

	private boolean emailCheck() {
		// verify that email is in proper format
		Pattern p = Pattern.compile("^(.+)@(.+)$"); // https://howtodoinjava.com/regex/java-regex-validate-email-address/
		Matcher m = p.matcher(this.email);
		boolean b = m.matches();
		System.out.println(this.email + " is valid: " + b);
		if(!b) return false;

		// Check if this email is already use by calling DB.getUser():
		// If the provided email is not associated with a user (eg. returns null),
		// Then we know the email is unique
		User ech = Database.adapter.getUser(this.email);
		if(ech != null) {
			return false;
		}
		else return true;
	}

	/**
	 *  Returns a boolean value indicating whether the password fields
	 *  for password and verifyPassword are identical.
	 *
	 *  @return boolean indicating if the checks pass
	 */

	private boolean passwordCheck()
	{
		return this.choosePassword.equals(this.verifyPassword);
	}

	private boolean zipCheck() {
		Pattern p = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"); //https://howtodoinjava.com/regex/java-regex-validate-us-postal-zip-codes/
		Matcher m = p.matcher(this.zipCode);
		boolean b = m.matches();
		return b;
	}

	/**
	 *  Sends the credentials to the database for new User
	 *  creation and storage. Returns boolean indicating
	 *  whether it is successful, as determined by the DB.
	 *
	 *  @return boolean indicating if the DB has stored the data
	 */

	private boolean storeData() {
		User newUser = new User(this.email, this.firstName, this.lastName, 0, this.invitedBy, this.type, this.creditCardNumber, this.expirationMonth, this.expirationYear, this.cvv, this.zipCode, null);
		Database.adapter.createUser(newUser);
		return true;
	}

	public String checkVerificationCode() {
		if(this.invitedBy.equals("")) return "Verification code is WRONG!";
		return "Verification code is RIGHT!";
	}
}
