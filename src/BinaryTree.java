import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//The Node class - stores content of Node

class Node {
	String word;
	
	int count = 1;
	
	// root node
	Node root;

	// left children
	Node leftChild;

	// right children
	Node rightChild;

	// constructor of Node - initializes word
	// @param String word: input string to be initialized

	Node(String word) {
		this.word = word;
	}

	// overrides toString() to print content of Node object

	public String toString() {
		return word;
	}

	// Method to add nodes to the BST
	// @param String w: input string
	// @return void

	public void addNode(String w) {

		// check whether input string is equal to current node value or not, if
		// so, return

		if (w.equals(this.word))
		{
			this.count = this.count + 1;
			return;
		}

		if (w.compareTo(this.word) < 0) // check whether input string is less
										// than current node value
		{

			// if left children is null, store value as a left children

			if (this.leftChild == null)
				this.leftChild = new Node(w);

			// if not, iterate till left children is null

			else
				this.leftChild.addNode(w);

			return;
		} else // check whether input string is greater than current node value
		{
			// if right children is null, store value as a right children

			if (this.rightChild == null)
				this.rightChild = new Node(w);

			// if not, iterate till right children is null

			else
				this.rightChild.addNode(w);

			return;
		}
	}

	// Method to search for the node having matching input string
	// @param String w: input string to search
	// @return node matching string value

	public Node searchNode(String w) {
		
		// if input string is matching with current node value

		if (this.word.equals(w))
		{
			// return current node
			return this;
		}
		// check whether input string is less than current node value

		else if (w.compareTo(this.word) < 0) {
			// check whether left children is null, if so, return null,
			// otherwise iterate process for the left subtree

			if (this.leftChild == null)
				return null;
			else
				return this.leftChild.searchNode(w);
		}

		else {
			// check whether right children is null, if so, return null,
			// otherwise iterate process for the right subtree

			if (this.rightChild == null)
				return null;
			else
				return this.rightChild.searchNode(w);
		}
	}
}

// Main method class

public class BinaryTree {
	// root node

	private static Node root;

	// constructor of the class - initializes root as null value

	public BinaryTree() {
		root = null;
	}

	// addNode() to add node to the Tree
	// @param String value: input string value
	// @return void

	public void addNode(String value) {
		// check if value is null, no need to add Node

		if (value.equals(null) || value.equals(""))
		{
			System.out.println("Blank value! Try again.");
			return;
		}

		// if root is null, tree is empty, insert value as a root node. If not,
		// iterate the process for root node

		if (root == null)
			root = new Node(value);
		else
			root.addNode(value);
	}

	// searchNode() is method to search a particular value in the Tree
	// @param String value: input string
	// @return Node: Node class object

	public static Node searchNode(String value) {
		// check if value is null, no need to search the tree

		if (value.equals(null) || value.equals(""))
		{
			System.out.println("Blank value! Try again.");
			return null;
		}
			
		// check if root is null, tree is empty, return null. Otherwise iterate
		// the process for root.

		if (root == null)
			return null;
		else
			return root.searchNode(value);
	}

	// Method to print tree elements in order form
	// @param Node item: Node object to be given as a starting point (generally
	// root)
	// @return void

	public void InOrderTreeTraversal(Node item) {
		// check if item is not null, print tree otherwise not

		if (item != null) {
			// recursive method call for left subtree
			InOrderTreeTraversal(item.leftChild);

			// prints current object

			System.out.println(item);

			// recursive method call for right subtree

			InOrderTreeTraversal(item.rightChild);
		}
	}

	// Main method throws IOException
	// @param String args[]: string array as command line argument
	// @return void

	public static void main(String args[])
	{
		try 
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			BinaryTree bst = new BinaryTree();

			// Object of File class for opening the file from specified path

			System.out.print("Enter filename with entire path here: ");
			String filepath = br.readLine();
			File file = new File(filepath);

			// scans item from file

			Scanner input = new Scanner(file);

			// for each line in the file, hasNext() is true if value is found,
			// false otherwise

			while (input.hasNext()) {
				// input.next() takes input value word by word

				String value = input.next();

				// calling addNode() to parse words in BST

				bst.addNode(value);
			}

			// command to close file

			input.close();

			System.out.println("\nElements of Tree in oder form:\n");

			// prints tree in order form by calling method

			bst.InOrderTreeTraversal(root);

			// scan word from keyboard to search in BST

			System.out.print("\nEnter word here : ");
			String search = br.readLine();
			
			// calling searchNode() to search the given input string

			Node result = searchNode(search);

			// print the result, use of ? operator to check whether word is
			// found or not

			System.out.println(result == null ? "No items found" : result
					+ " is found " + result.count + " times");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Sorry! Given file does not found. Try again");
		} 
		catch (IOException e) 
		{
			System.out.println("Sorry! Error found in Input. Try again");
		}
	}
}
