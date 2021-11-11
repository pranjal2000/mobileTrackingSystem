public class MobilePhone{
	int id,status;
	Exchange parent;
	public MobilePhone(int number){
		id = number;
		status = 1;
		parent = null;  //SwitchedOn
	}
	public int number(){
		return id;
	}
	public Boolean status(){
		return (status==1);
	}
	public void switchOn(){
		status = 1;
	}
	public void switchOff(){
		status = 0;
	}
	public Exchange location() throws IllegalStateException{
		try{	
			if (status==0)
				throw new Exception();
			else{
				return parent; 
			}
		}
		catch (Exception e){
			System.out.println("Mobile phone is switched off!");
			return null;
		}		 
	}
}