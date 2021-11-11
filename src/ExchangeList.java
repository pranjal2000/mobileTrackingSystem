public class ExchangeList{
	public int size;
	public Exchange head;
	public ExchangeList(){
		size = 0;
		head = null;
	}
	public int size(){
		return size;
	}
	public Boolean isEmpty(){
		return (size==0);
	}
	public Exchange getNeighbour(Exchange v){
		try{	
			if (v.neighbour == null)
				throw new Exception();
			return v.neighbour;
		}
		catch (Exception e){
			System.out.println("No neighbour");
			return null;
		}
	}
	public void add(Exchange v){
		Exchange u = head;
		head = v;
		v.setNeighbour(u);
		size++;
	}
	public Exchange getithChild(int i){
		try{	
			if (size<=i)
				throw new Exception();
			else{
				Exchange counter = head;
				while (i!=0){
					i--;
					counter=counter.neighbour;
				}
				return counter;
			}
		}
		catch (Exception e){
			System.out.println("It doesnot have this many members!");
			return null;
		}	
	}
}	