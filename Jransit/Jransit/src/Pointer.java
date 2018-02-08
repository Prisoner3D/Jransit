
public interface Pointer {
	/**
	 * What is a pointer?
	 * In computer science, a pointer is a programming language object, whose value refers to (or "points to") another value stored elsewhere in the computer memory using its memory address. A pointer references a location in memory, and obtaining the value stored at that location is known as dereferencing the pointer. As an analogy, a page number in a book's index could be considered a pointer to the corresponding page; dereferencing such a pointer would be done by flipping to the page with the given page number and reading the text found on the indexed page.
	 * To update the MTAApi, instead of makign a anew api object and asssingnign to a key
	 */
	public Object dereference();
	public void reference(Object api);
}
