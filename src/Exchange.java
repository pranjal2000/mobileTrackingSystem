import java.util.*;
public class Exchange{
	Exchange parent;
	int identifier;
	Exchange neighbour;
	ExchangeList children; //For Base Stations, it will be empty!!
	MobilePhoneSet mobilePhoneSet;
	public Exchange(int number){
		parent = null;
		neighbour = null;
		children = new ExchangeList();
		identifier = number;
		mobilePhoneSet = new MobilePhoneSet();
	}
	public void setIdentifier(int n){
		identifier = n;
	}
	public Exchange parent(){
		return parent;
	}
	public void setParent(Exchange v){
		parent = v;
	}
	public void setNeighbour(Exchange n){
		neighbour = n;
	}
	public Exchange child(int i) {
		return children.getithChild(i);
	}
	public int numChildren(){
		return children.size();
	}
	public Boolean isRoot(){
		return (parent==null);
	}
	public RoutingMapTree smallTree(){
		RoutingMapTree sub = new RoutingMapTree();
		Exchange v = this;
		sub.setRoot(v);
		return sub;
	}
	public RoutingMapTree subtree(int i){
		try{	
			if (children.size()<=i)
				throw new Exception();
			else{
				return children.getithChild(i).smallTree();
			}
		}
		catch (Exception e){
			System.out.println("It doesnot have this many members!");
			return null;
		}		
	}
	public MobilePhoneSet residentSet(){
		return mobilePhoneSet;
	}
	public Exchange makeCopy(){
		Exchange x = new Exchange(this.identifier);
		x.setNeighbour(this.neighbour);
		x.setParent(this.parent);
		x.children = this.children;
		x.mobilePhoneSet = this.mobilePhoneSet;
		return x;
	}	
}