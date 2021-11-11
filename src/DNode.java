public class DNode{
	protected Object element;
	protected DNode next,prev;
	public DNode(Object e){
		element = e;
		next = null;
		prev = null;
	}
	public DNode(Object e,DNode p,DNode n){
		element = e ;
		next = n ;
		prev = p ; 
	}
	public Object getElement(){
		return element;
	}
	public DNode getNext(){
		return next;
	}
	public DNode getPrev(){
		return prev;
	}
	public void setElement(Object newElem){
		element = newElem;
	}
	public void setNext(DNode newNext){
		next = newNext;
	}
	public void setPrev(DNode newPrev){
		prev = newPrev;
	}
}