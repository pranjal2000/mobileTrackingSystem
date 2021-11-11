public class RoutingMapTree{
	Exchange root;
	MobilePhoneSet allPhones;
	public RoutingMapTree(){
		root = new Exchange (0);
		allPhones = new MobilePhoneSet();
	}
	public void setRoot(Exchange v){
		root = v;
	}
	public Exchange root(){
		return root;
	} 
	public Boolean isEmpty(){
		return (root==null);
	}
	public Boolean isRoot(Exchange v){
		return (root==v);
	}
	public Boolean containsExchangeId(int a){
		if (root.identifier==a)
			return true;
		else{
			for (int i=0;i<root.children.size();i++){
				if ((root.children.getithChild(i).smallTree()).containsExchangeId(a))
					return true;
			}
		return false;	
		}
	}
	public Boolean containsExchange(Exchange a){
		if (root==a)
			return true;
		else{
			for (int i=0;i<root.children.size();i++){
				if ((root.children.getithChild(i).smallTree()).containsExchange(a))
					return true;
			}
		return false;	
		}
	}
	public Exchange returnExchange(int a){
		try{	
			if (containsExchangeId(a)){
				if (root.identifier==a){
					return root;
				}	
				else{
					for (int i=0;i<root.children.size();i++){
						if ((root.children.getithChild(i).smallTree()).containsExchangeId(a))
							return (root.children.getithChild(i).smallTree()).returnExchange(a);	
					}
					return null;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - No exchange with identifier "+a+" found in the network!");
			return null;
		}			
	}
	public void switchOn(MobilePhone a,Exchange b){
		try{	
			if (a.status())
				throw new Exception();
			else{
				a.switchOn();
				a.parent = b;
				Exchange c = b;
				while (!isRoot(c)){
					c.residentSet().mobilePhoneSet.insert(a);
					c = c.parent;		
				}
				if (!getPhoneBoolId(a))
					allPhones.mobilePhoneSet.insert(a);
				c.residentSet().mobilePhoneSet.insert(a);
			}
		}
		catch (Exception e){
			System.out.println("Error - Mobile phone with identifier "+a.id+" is already switched on");
			return;
		}	
	}
	public void switchOff(MobilePhone a){
		try{	
			if (!a.status())
				throw new Exception();
			else{
				a.switchOff();
				Exchange c = a.parent;
				while (!isRoot(c)){
					c.residentSet().mobilePhoneSet.delete(a);
					c = c.parent;		
				}
				c.residentSet().mobilePhoneSet.delete(a);
				a.parent = null;
			}
		}
		catch (Exception e){
			System.out.println("Error - Mobile phone with identifier "+a.id+" is already switched off");
			return;
		}	
	}
	public Boolean getPhoneBoolId(MobilePhone v){
		for (int i=0;i<allPhones.mobilePhoneSet.size();i++){
			MobilePhone p= (MobilePhone)allPhones.mobilePhoneSet.getithMember(i+1);
			if (p==v) 
				return true;
		}
		return false;
	}
	public Boolean getPhoneBool(int num){
		for (int i=0;i<allPhones.mobilePhoneSet.size();i++){
			MobilePhone p= (MobilePhone)allPhones.mobilePhoneSet.getithMember(i+1);
			if (p.number()==num) {
				return true;
			}
		}
		return false;
	}
	public MobilePhone getPhone(int num) {
		if (getPhoneBool(num)){
			for (int i=0;i<allPhones.mobilePhoneSet.size();i++){
				MobilePhone p=(MobilePhone)allPhones.mobilePhoneSet.getithMember(i+1);
				if (p.number()==num) {
					return (p);
				}
			}
			return null;
		}
		else{
			MobilePhone p = new MobilePhone(num);
			p.switchOff();
			return p;
		}	
	}
	public void addExchange(int p,int n){
		try{	
			if (!containsExchangeId(n)){
				try{	
					if (containsExchangeId(p)){
						Exchange child = new Exchange(n);
						Exchange pa = returnExchange(p);
						pa.children.add(child);
						child.setParent(pa);
					}
					else 
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("Error - No exchange with identifier "+p+" found in the network!");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - Exchange with identifier "+n+" already exists in the tree!");
			return;
		}		
	}
	public void switchOnMobile(int a,int b) {
		try{
			if (containsExchangeId(b)){
				try{	
					if (returnExchange(b).numChildren()==0){
						switchOn(getPhone(a),returnExchange(b));
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("Error - Exchange with identifier "+b+" is not a base station!");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - No exchange with identifier "+b+" found in the network!");
			return;
		}
	}
	public void switchOffMobile(int a){
		try{	
			if (getPhoneBool(a))
				switchOff(getPhone(a));
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - No mobile phone with identifier "+a+" found in the network");
			return;
		}	
	}
	public void queryNthChild(int a,int b){
		try{	
			if (containsExchangeId(a)){
				try{
					if (b<returnExchange(a).numChildren() && b>=0){
						int ans = returnExchange(a).child((returnExchange(a).numChildren())-b-1).identifier;
						System.out.println(ans);
					}
					else
						throw new Exception();	
				}
				catch (Exception e){
					System.out.println("Error - Exchange with identifier "+a+" doesnot contain these many members!");
					return;
				}
			}		
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - No exchange with identifier "+a+" found in the network!");
			return;
		}
	}
	public void queryMobilePhoneSet(int a){
		try{
			if (containsExchangeId(a)){
				int n = returnExchange(a).residentSet().mobilePhoneSet.size();
				if (n==0){
					System.out.println("");
				}
				else{
					int i;
					for (i=n;i>1;i--){
						MobilePhone p =(MobilePhone)returnExchange(a).residentSet().mobilePhoneSet.getithMember(i);
						System.out.print(p.number() + " , ");
					}
					MobilePhone p =(MobilePhone)returnExchange(a).residentSet().mobilePhoneSet.getithMember(i);
					System.out.println(p.number());
				}
			}
			else
				throw new Exception();	
		}
		catch (Exception e){
			System.out.println("Error - No exchange with identifier "+a+" found in the network!");
			return;
		}		
	}
	public Exchange findPhone(MobilePhone m){
		try{
			if (m.status()){
				return m.parent;
			}
			else
				throw new Exception();
			}
		catch (Exception e){
			System.out.println("Error - No mobile phone with identifier "+m.id+" found in the network");
			return null;
		}
	}
	public void queryFindPhone(int a){
		try{
			if (getPhoneBool(a)){
				try{
					if (getPhone(a).status()){
						System.out.println(getPhone(a).parent.identifier);
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("Error - Mobile phone with identifier "+a+" is currently switched off");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - No mobile phone with identifier "+a+" found in the network");
			return;
		}
	}
	public Exchange lowestRouter(Exchange a,Exchange b){
		try{
			if (containsExchange(a) && containsExchange(b)){
				try{
					if (a.numChildren()==0 && b.numChildren()==0){
						Exchange a1 = a;
						while (!a1.smallTree().containsExchange(b) && a1!=b){
							a1 = a1.parent;
						}
						return a1;
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					if (a.numChildren()==0)
						System.out.println("Error - Exchange with identifier "+b.identifier+" is not a base station!");
					else
						System.out.println("Error - Exchange with identifier "+a.identifier+" is not a base station!");
					return null;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			if (!containsExchange(a))
				System.out.println("Error - No exchange with identifier "+a.identifier+" found in the network!");
			else
				System.out.println("Error - No exchange with identifier "+b.identifier+" found in the network!");
			
			return null;
		}
	}
	public void queryLowestRouter(int a,int b){
		try{
			if (containsExchangeId(a) && containsExchangeId(b)){
				try{
					Exchange x = returnExchange(a);
					Exchange y = returnExchange(b);
					if (x.numChildren()==0 && y.numChildren()==0){
						// Exchange a1 = x;
						// while (!a1.smallTree().containsExchange(y)){
						// 	a1 = a1.parent;
						// }
						// System.out.println(a1.identifier);
						Exchange s = lowestRouter(x,y);
						System.out.println(s.identifier);
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					if (returnExchange(b).numChildren()==0)
						System.out.println("Error - Exchange with identifier "+a+" is not a base station!");
					else
						System.out.println("Error - Exchange with identifier "+b+" is not a base station!");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			if (!containsExchangeId(a))
				System.out.println("Error - No exchange with identifier "+a+" found in the network!");
			else
				System.out.println("Error - No exchange with identifier "+b+" found in the network!");
			return;
		}
	}
	public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		try{
			if (allPhones.mobilePhoneSet.isMember(a) && allPhones.mobilePhoneSet.isMember(b)){
				try{
					if (a.status() && b.status()){
						Exchange a1 = a.parent.makeCopy();
						ExchangeList list = new ExchangeList();
						while (a1.identifier != lowestRouter(a.parent,b.parent).identifier){
							list.add(a1);
							a1 = a1.parent.makeCopy();
						}
						while (a1.identifier != b.parent.identifier){
							list.add(a1);
							for (int i=0;i<a1.numChildren();i++){
								Exchange c = a1.children.getithChild(i).makeCopy();
								if (c.identifier==b.parent.identifier || a1.subtree(i).containsExchange(b.parent)){
									a1 = c;
									break;
								}
							}
						}
						list.add(a1);
						return list;
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("Either one of them is switched off!");
					return null;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Either one of them doesnot exist!");
			return null;
		}
	}
	public void queryFindCallPath(int a, int b){
		try{
			if (getPhoneBool(a) && getPhoneBool(b)){
				try{
					MobilePhone x = getPhone(a);
					MobilePhone y = getPhone(b);
					if (x.status() && y.status()){
						// Exchange a1 = x.parent;
						// Exchange b1 = y.parent;
						// Exchange lr = lowestRouter(x.parent,y.parent);
						// while (a1 != lr){
						// 	System.out.print(a1.identifier+" , ");
						// 	a1 = a1.parent;
						// }
						// while (a1 != b1){
						// 	System.out.print(a1.identifier+" , ");
						// 	for (int i=0;i<a1.numChildren();i++){
						// 		Exchange c = a1.children.getithChild(i);
						// 		if (c==b1 || a1.subtree(i).containsExchange(b1)){
						// 			a1 = c;
						// 			break;
						// 		}
						// 	}
						// }
						// System.out.print(a1.identifier);
						// System.out.println("");
						ExchangeList s = routeCall(x,y);
						int i;
						for (i=s.size()-1;i>0;i--){
							System.out.print(s.getithChild(i).identifier+" , ");
						}
						System.out.print(s.getithChild(i).identifier);
						System.out.println("");
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					if (!getPhone(a).status())
						System.out.println("Error - Mobile phone with identifier "+a+" is currently switched off");
					else
						System.out.println("Error - Mobile phone with identifier "+b+" is currently switched off");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			if (!getPhoneBool(a))
				System.out.println("Error - No mobile phone with identifier "+a+" found in the network");
			else
				System.out.println("Error - No mobile phone with identifier "+b+" found in the network");
			return;
		}
	}
	public void movePhone(MobilePhone a,Exchange b){
		try{
			if (allPhones.mobilePhoneSet.isMember(a)){
				try{
					if (a.status()){
						try{
							if (containsExchange(b)){
								try{
									if (b.numChildren()==0){
										switchOff(a);
										switchOn(a,b);
									}
									else
										throw new Exception();
								}
								catch (Exception e){
									System.out.println("This exchange is not a base station!");
									return;
								}
							}
							else
								throw new Exception();
						}
						catch (Exception e){
							System.out.println("This exchange doesnot exist!");
							return;
						}
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("This phone is switched off!");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("This phone doesnot exist!");
			return;
		}
	}
	public void movePhone(int a, int b){
		try{
			if (getPhoneBool(a)){
				try{
					if (getPhone(a).status()){
						try{
							if (containsExchangeId(b)){
								try{
									if (returnExchange(b).numChildren()==0){
										switchOff(getPhone(a));
										switchOn(getPhone(a),returnExchange(b));
									}
									else
										throw new Exception();
								}
								catch (Exception e){
									System.out.println("Error - Exchange with identifier "+b+" is not a base station!");
									return;
								}
							}
							else
								throw new Exception();
						}
						catch (Exception e){
							System.out.println("Error - No exchange with identifier "+b+" found in the network!");
							return;
						}
					}
					else
						throw new Exception();
				}
				catch (Exception e){
					System.out.println("Error - Mobile phone with identifier "+a+" is currently switched off");
					return;
				}
			}
			else
				throw new Exception();
		}
		catch (Exception e){
			System.out.println("Error - No mobile phone with identifier "+a+" found in the network");
			return;
		}
	}
	public void performAction(String actionMessage) {
		String[] s = actionMessage.trim().split(" +");
		switch (s[0]){
			case "addExchange": addExchange(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "switchOnMobile": switchOnMobile(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "switchOffMobile": switchOffMobile(Integer.parseInt(s[1]));break;
			case "queryNthChild": System.out.println("queryNthChild "+Integer.parseInt(s[1])+" "+Integer.parseInt(s[2])+": ");
								  queryNthChild(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "queryMobilePhoneSet": System.out.println("queryMobilePhoneSet "+Integer.parseInt(s[1])+": ");
										queryMobilePhoneSet(Integer.parseInt(s[1]));break;
			case "queryFindPhone" : System.out.println("queryFindPhone "+Integer.parseInt(s[1])+": ");
									queryFindPhone(Integer.parseInt(s[1]));break;
			case "queryLowestRouter" : System.out.println("queryLowestRouter "+Integer.parseInt(s[1])+" "+Integer.parseInt(s[2])+": ");
									   queryLowestRouter(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "queryFindCallPath" : System.out.println("queryFindCallPath "+Integer.parseInt(s[1])+" "+Integer.parseInt(s[2])+": ");
									   queryFindCallPath(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			case "movePhone" : movePhone(Integer.parseInt(s[1]),Integer.parseInt(s[2]));break;
			default : System.out.println("Action not defined!");
		}
	}
} 