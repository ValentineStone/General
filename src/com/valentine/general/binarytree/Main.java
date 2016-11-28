package com.valentine.general.binarytree;

public class Main
{
	public static void main(String[] args)
	{
		String text =
		//"1 1 1 2 3 3 3";
		//"1 2 3 34 4 5 5 5 5 5 5 5 5 5 6 7 8 93 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 4 3 4 3 4 3 4 3 4 3 4 3 4 3 4 34  3 3 3 3 3 3 3 3";
		
			"How do you pick up the threads of an old life?"
			+ "How do you go on, when in your heart you begin to understand..."
			+ "there is no going back? There are some things that time cannot mend."
			+ "Some hurts that go too deep, that have taken hold.";
		
		
		String[] words = text.toLowerCase().split("\\W+");
		
		System.err.println("words: " + words.length);
		
		//BinaryTree<String> btree = new BinaryTree<>();
		
		//tree.add((String)null);
		//tree
		//	.add("null")
		//	.add((String)null);
		
		//tree.add(words);
		
		//System.err.println(btree.toText());
		
		
		WeightedBinaryTree<String> wbtree = new WeightedBinaryTree<String>(words);
		
		//wbtree.add(words);
		
		System.err.println(wbtree.toText());
		
		wbtree.balanceOut();
	}
}
