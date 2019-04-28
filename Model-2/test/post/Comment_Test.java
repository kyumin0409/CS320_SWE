package post;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Comment_Test
{
	int ids;
	user marc = new user();
	Post post1;
	Comment comment1;

	/*
	 * test designed to test that getting the associated postedId is working
	 */
	@Test
	void testAssociatedPost()
	{
		post1 = new Post(marc, createId(), "comment test");
		comment1 = new Comment(marc, createId(), "comment test", post1);

		assertEquals(comment1.getAssociatedPostID(), post1);
	}

	/*
	 * to test that the comment was added to the Posts arraylist of comments
	 */
//	@Test
//	void testSuccessfulLink()
//  {
//    post1 = new Post(marc, createId(), "comment test");
//    comment1 = new Comment(marc, createId(), "comment test", post1);
//
//    assertEquals(post1.comments.size(), 1);
//    assertEquals(post1.comments.get(0), comment1);
//  }

	/*
	 * Helper for creating posts
	 */
	String createId()
	{
		String value = Integer.toString(ids);
		ids++;
		return value;
	}
}