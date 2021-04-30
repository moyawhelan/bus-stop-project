import java.util.LinkedList;

public class TST<Value>  {
	
	private static class TrieNode<Value> 
	{
		private char c;                        	   // character
		private TrieNode<Value> left; 			   //left subtrie
		private TrieNode<Value> mid;			   //mid subtrie
		private TrieNode<Value> right;			   //right subtrie
		private Value val;                         // value associated with string
	}
	
	 /* 
	   * a pointer to the start of your trie
	   */
	  private TrieNode root = new TrieNode();
	  private static final int R = 256; 
	  private int N; 
	  
	  /*
	   * Returns the number of words in the trie
	   */
	  public int size() 
	  {
	    return N; 
	  }
	  /*
	   * returns true if the word is in the trie, false otherwise
	   */
	  public boolean contains(String key) 
	  {
		  if (key == null)
	          return false;
	 
	      return get(key) != null;
	  }
	  /*
	   * return the value stored in a node with a given key, returns null if word is not in trie
	   */
	  public Value get(String key) 
	  {
		  //The key is the word made up of chars
		  //Each node has a char 
		  //The node with the last char in the key (which is a word), has the value 
		  
	      if (key == null) 
	      {
	          return null; 
	      }
	      
	      if (key.length() == 0) 
	      {
	    	  return null; 
	      }
	    	  
	      TrieNode<Value> x = get(root, key, 0);
	      
	      if (x == null) 
	    	  return null;
	      
	      return x.val;

	  }
	  private TrieNode<Value> get(TrieNode<Value> x, String key, int d) 
	  {
	      if (x == null)
		  {
	    	  return null;
		  }
	      
	      char c = key.charAt(d);
	      
	      if (c < x.c)              
	    	  return get(x.left,  key, d);
	      
	      else if (c > x.c)
	    	  return get(x.right, key, d);
	      
	      else if (d < key.length() - 1)
	    	  return get(x.mid,   key, d+1);
	      
	      else
	    	  return x;
	  }
	  /*
	   * stores the Value val in the node with the given key
	   */
	  public void put(String key, Value val) 
	  {
	      if (!contains(key)) 
	    	  N++;
	      root = put(root, key, val, 0);
	  }
	  private TrieNode<Value> put(TrieNode<Value> x, String key, Value val, int d) 
	  {
	      char c = key.charAt(d);
	      if (x == null) 
	      {
	          x = new TrieNode<Value>();
	          x.c = c;
	      }
	      
	      if (c < x.c)              
	    	  x.left  = put(x.left,  key, val, d);
	      
	      else if (c > x.c)               
	    	  x.right = put(x.right, key, val, d);
	      
	      else if (d < key.length() - 1)  
	    	  x.mid   = put(x.mid,   key, val, d+1);
	      
	      else                            
	    	  x.val   = val;
	      
	      return x;
	  }
	  /*
	   * returns the linked list containing all the keys present in the trie
	   * that start with the prefix passes as a parameter, sorted in alphabetical order
	   */ 
	  public LinkedList<String> keysWithPrefix(String prefix) 
	  {
		  if (prefix == "") 
		  {
	          return null; 
	      }
		  
		 
	      
		  LinkedList<String> list = new LinkedList<String>();
	      
		  TrieNode<Value> x = get(root, prefix, 0);
	      
	      if (x == null) 
	    	  return list;
	     
	      if (x.val != null) 
	    	  list.add(prefix);
	      
	      collect(x.mid, new StringBuilder(prefix), list);
	      
	      return list; 
	  }
	  
	  // all keys in subtrie rooted at x with given prefix
	  private void collect(TrieNode<Value> x, StringBuilder prefix, LinkedList<String> list) 
	  {
	      if (x == null) 
	    	  return;
	      
	      collect(x.left,  prefix, list);
	      
	      if (x.val != null) 
	    	  list.add(prefix.toString() + x.c);
	      
	      collect(x.mid,   prefix.append(x.c), list);
	      
	      prefix.deleteCharAt(prefix.length() - 1);
	      
	      collect(x.right, prefix, list);
	  }
	  
	


}
