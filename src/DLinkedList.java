import java.util.*;
public class DLinkedList{
	int size;
	protected DNode head,tail;
	public DLinkedList(){
		size=0;
		head = new DNode(null, null, null);
		tail = new DNode(null, head, null);
		head.setNext(tail);
	}
	public int size(){
		return size;
	}
	public Boolean isEmpty(){
		return (size==0);
	}
	public DNode getHead(){
		return head;
	}
	public DNode getTail(){
		return tail;
	}
	public DNode getFirst() throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("List is Empty");
		return head.getNext();
	}
	public DNode getLast() throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("List is Empty");
		return tail.getPrev();
	}
	public DNode getPrev(DNode v) throws IllegalArgumentException{
		if (v==head)
			throw new IllegalArgumentException("Cannot move back past the head of the list");
		return v.getPrev();
	}
	public DNode getNext(DNode v) throws IllegalArgumentException{
		if (v==tail)
			throw new IllegalArgumentException("Cannot move forward past the tail of the list");
		return v.getNext();
	}
	//Add DNode z before DNode v
	public void addBefore (DNode v,DNode z) throws IllegalArgumentException{
		DNode u = getPrev(v);
		z.setPrev(u);
		z.setNext(v);
		u.setNext(z);
		v.setPrev(v);
		size++;
	}
	//Add DNode z after DNode v
	public void addAfter (DNode v, DNode z) throws IllegalArgumentException{
		DNode u = getNext(v);
		z.setNext(u);
		z.setPrev(v);
		v.setNext(z);
		u.setPrev(z);
		size++;
	}
	public void addFirst(DNode v){
		addAfter(head,v);
	}
	public void addLast(DNode v){
		addBefore (tail,v);
	}
	public void remove(DNode v){
		DNode u = getPrev(v);
		DNode w = getNext(v);
		w.setPrev(u);
		u.setNext(w);
		v.setPrev(null);
		v.setNext(null);
	}
	public Boolean hasPrev(DNode v){
		return (v!=head);
	}
	public Boolean hasNext(DNode v){
		return (v!=tail);
	}
}