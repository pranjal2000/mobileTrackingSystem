import java.util.*;
public class Myset{
	DLinkedList l;
	protected DNode current;
	public Myset(){
		l = new DLinkedList();
	} 
	public int size(){
		return l.size();
	}
	public Boolean isEmpty(){
		return (l.isEmpty());
	}
	public Boolean isMember (Object o){
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			if (current.getNext().getElement()==o)
				return true;
			current = current.getNext();
		}
		return false; 
	}
	public void insert(Object o){
		DNode n = new DNode(o);
		l.addFirst(n); 
	}
	public void delete(Object o){
		try{
			current = l.getHead();
			if (!isMember(o))
				throw new Exception(); 
			while (current.getNext()!= l.getTail()){
				if (current.getNext().getElement() == o){
					current = current.getNext();
					l.remove(current);
					l.size--;
					break;
				}
				current = current.getNext();	
			}
		}
		catch (Exception e){
			System.out.println("No such object exists!");
			return;
		}	
	}
	public Myset union(Myset a){
		Myset y = a;
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			Object x = current.getNext().getElement();
			if (!a.isMember(x))
				y.insert(x);
			current = current.getNext();
		}
		return y; 
	}
	public Myset intersection(Myset a){
		Myset y= new Myset();
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			Object x = current.getNext().getElement();
			if (a.isMember(x))
				y.insert(x);
			current = current.getNext();
		}
		return y; 
	}
	public void update(Myset a){
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			Object x = current.getNext().getElement();
			if (!a.isMember(x))
				insert(x);
			current = current.getNext();
		}
	}
	public Object getithMember(int i){  
		try{
			if (size()<i)
				throw new Exception();
			else{
				DNode counter = l.getHead();
				while (i!=0){
					i--;
					counter=counter.getNext();
				}
				return counter.getElement();
			}
		}
		catch (Exception e){
			System.out.println("It doesnot have this many members!");
			return null;
		}	
	}
}