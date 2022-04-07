package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Trie {

	TrieNode root = new TrieNode(' '); 

	//Add a word to trie, Iteration, Time O(s), Space O(s), s is word length
	public void insert(String word) {       
		TrieNode node = root; 	 
		for (char ch : word.toCharArray()) {	           
			if (node.getChild(ch) == null) 
				node.children.add(new TrieNode(ch));
			node = node.getChild(ch);	            		           
		}
		node.isEnd = true;
	}

	//find the node with prefix's last char, then call helper to find all words using recursion
	//Recursion, Time O(n), Space O(n), n is number of nodes involved (include prefix and branches)
	public List<String> autoComplete(String prefix) {     
		TrieNode node = root;
		List<String> res = new ArrayList<String>(); 
		for (char ch: prefix.toCharArray()) { //find end of prefix
			node = node.getChild(ch);	     
			if (node == null) 
				return new ArrayList<String>();      
		} 
		helper(node, res, prefix.substring(0, prefix.length()-1));
//		for(String s: res)
//			System.out.println(s);
		return res;
	}

	//recursion helper, Time O(n), Space O(n), n is number of nodes in branches
	public void helper(TrieNode node, List<String> res, String prefix) {		
		if (node.isEnd)  
			res.add(prefix + node.data);		
		for (TrieNode child : node.children) 				
			helper(child, res, prefix + node.data);						
	}

	public static void autoCompleteWord(String keyword, PreSearch ps, int numberOfResults, int flag) {
		if(flag==1) {
			Trie trie = getFilledTrie(ps);
			List<?> phraseList=trie.autoComplete(keyword);
			try{
				String phrase=(String) phraseList.get(0);
				System.out.println("Word autocompleted to : "+phrase);
				Iterator<Entry<String,HashSet<Integer>>> iterator = ps.index.entrySet().iterator();
				while(iterator.hasNext()) {  			
					Map.Entry<String, HashSet<Integer>> me = (Map.Entry<String, HashSet<Integer>>)iterator.next();
					String word = me.getKey().toString();
					if(phrase.equalsIgnoreCase(word)) {
						Search.searchPhrase(word,numberOfResults);
					}
				}
				SearchSimillarWords.searchSimillar(phrase,numberOfResults,ps,2);
			} catch (IndexOutOfBoundsException e) {
				Search.flag=2;
				SearchSimillarWords.searchSimillar(keyword,numberOfResults,ps,2);
			}
		}
	}

	public static Trie getFilledTrie(PreSearch ps) {
		Trie trie = new Trie();
		Iterator<Entry<String,HashSet<Integer>>> iterator = ps.index.entrySet().iterator();
		while(iterator.hasNext()) {  			
			Map.Entry<String, HashSet<Integer>> me = (Map.Entry<String, HashSet<Integer>>)iterator.next();
			trie.insert(me.getKey().toString());
		}
		return trie;
	}

}