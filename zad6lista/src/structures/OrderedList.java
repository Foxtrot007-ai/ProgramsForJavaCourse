package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList <T extends Comparable<T>> implements OrderedSequence<T>, Iterable<T> {
	private class Node <T extends Comparable<T>> {
		private Node<T> next;
		private T data;
		// ... pseudoimplementacja OrderedSequence<T>
		public Node(Node<T> n, T v)
		{
			next = n;
			data = v;
		}
		public void setNext(Node<T> n)
		{
			next = n;
		}
		public Node<T> getNext()
		{
			return next;
		}
		
		
		public void add(T data, int i)
		{
			
			 if (isEmpty() || i < 0) {
			        throw new IndexOutOfBoundsException("Index " + i);
			    }

			    Node<T> currentNode = this.next;
			    Node<T> previousNode = this;
			    int currentIndex = i;
			    
			    while (currentIndex > 0) {
			        if (currentNode == null) {
			            throw new IndexOutOfBoundsException("Index " + i);
			        }
			        previousNode = currentNode;
			        currentNode = currentNode.getNext();
			        currentIndex--;
			    }
			    
			    previousNode.setNext(new Node(currentNode, data));
		}
		
		public void remove(int i)
		{
			 if (i < 0 && this.size() != 0) {
			        throw new IndexOutOfBoundsException("Index " + i);
			    }

			    Node<T> currentNode = this.next;
			    Node<T> previousNode = this;
			    int currentIndex = i;
			    
			    while (currentIndex > 0) {
			        if (currentNode == null) {
			            throw new IndexOutOfBoundsException("Index " + i);
			        }
			        previousNode = currentNode;
			        currentNode = currentNode.getNext();
			        currentIndex--;
			    }
			    
			    previousNode.setNext(currentNode.getNext());
			    
		}
		
		public int size()
		{
			int size = 0;
		    Node<T> currentNode = this.next;
	
		    while (currentNode != null) {
		        size++;
		        currentNode = currentNode.getNext();
		    }
		    return size;
		}
		
		public boolean isEmpty() {
		    return next == null;
		}
		
		public T get_value()
		{
			return data;
		}
		
		public Node<T> get_node(int i)
		{
			
			    if (isEmpty() || i < 0) {
			        throw new IndexOutOfBoundsException("Index " + i);
			    }
			    
			    Node<T> currentNode = this.next;
			    int currentIndex = i;
			    while (currentIndex > 0) {
			        if (currentNode == null) {
			            throw new IndexOutOfBoundsException("Index " + i);
			        }
			        currentNode = currentNode.getNext();
			        currentIndex--;
			    }

			    return currentNode;
			
		}
	}
	private Node<T> head;
	// ... implementacja OrderedSequence<T>
	
	
	
	private class iterator <T extends Comparable<T>> implements Iterator<T>{
		private boolean canRemove = false;
		Node<T> head;
	    int current_node = 0;
	    
	    public iterator(OrderedList<T> list)
	    {
	        head = (Node<T>) list.get_head();
	    }
		@Override
		public boolean hasNext() {
			if(head != null)
				if(head.get_node(current_node) != null)
					return true;
			return false;
		}

		@Override
		public T next() {
			if(this.hasNext())
			{
				canRemove = true;
				current_node++;
				return head.get_node(current_node - 1).get_value();
			}
			throw new NoSuchElementException();
			
		}
		
		@Override
		public void remove()
		{
			if (!canRemove)
		    {
				throw new IllegalStateException();
		    }
			
			head.remove(current_node--);
			canRemove = false;
		}
		
	}
	
	public OrderedList()
	{
		head = new Node(null,-1);
	}
	
	public void check_null(T el)
	{
		if(el == null)throw new NullPointerException();
	}
	
	@Override
	public String toString() { 
		String temp = "";
		for(int i = 0; i < head.size(); i++)
			temp += head.get_node(i).get_value() + " ";
		
		return temp;
	}
	
	@Override
	public void insert(T el) {
	
		check_null(el);
		if(head.isEmpty()) {
			head.setNext(new Node(null,el));
			return;
		}
		int i = 0;
		
		while(head.size() > i && head.get_node(i).get_value().compareTo(el) < 0)
		{
			i++;
		}
		
		if(head.get_node(i) == null) {
		
			head.add(el, i);
		}
		else if(head.get_node(i).get_value().compareTo(el) != 0)
			head.add(el, i);
		
		
		
	}
	
	@Override
	public void remove(T el) {
		if(head == null)
			head = new Node(null,el);
		
		int i = 0;
		while(head.size() > i && head.get_node(i).get_value().compareTo(el) != 0)
		{
			i++;
		}
		
		if(i < head.size())
		head.remove(i);
	
	}
	
	@Override
	public T min() {
		if(head.isEmpty())throw new NullPointerException();
		return head.get_node(0).get_value();
	}
	
	@Override
	public T max() {
		if(head.isEmpty())throw new NullPointerException();
		return head.get_node(head.size() - 1).get_value();
	}
	
	@Override
	public T at(int pos) {
		return head.get_node(pos).get_value();
	}
	
	@Override
	public boolean search(T el) {
		if(head == null)
			return false;
		
		int i = 0;
		while(head.size() > i)
		{
			if(head.get_node(i).get_value().compareTo(el) == 0)
				return true;
			
			i++;
		}
		return false;
		
		
	}
	
	@Override
	public int index(T el) {
	
		if(head == null)
			return -1;
		
		int i = 0;
		while(head.size() > i)
		{
			if(head.get_node(i).get_value().compareTo(el) == 0)
				return i;
			
			i++;
		}
		return -1;
	}
	
	@Override
	public int size() {
	return head.size();
	}
	
	public Node<T> get_head()
	{
		return head;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new iterator(this);
	}
}

