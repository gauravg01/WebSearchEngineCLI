package com;

import java.util.LinkedList;

public class TrieNode {		
	char data;     
	LinkedList<TrieNode> children = new LinkedList<>();
	boolean isEnd = false; 

	//Constructor, Time O(1), Space O(1)
	TrieNode(char c) {
		data = c;
	} 

	//find the node by char, the same functionality as children[ch] in array implementation 
	//Time O(k), Space O(1), k is number of children of this node 
	TrieNode getChild(char c) {
		if (children != null)
			for (TrieNode child : children)
				if (child.data == c)
					return child;
		return null;
	}	    
}