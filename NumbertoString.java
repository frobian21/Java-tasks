class NumbertoString{
	public static void main(String[] args){
		int a = 0;
		
		try{
			a = Integer.parseInt(args[0]);
		}catch(NumberFormatException X){
			System.out.println("Digits only please.");
		}		
		if(numbertoword(a/1000)) System.out.print(" thousand ");
		if(numbertoword((a%1000)/100)) System.out.print(" hundred ");
		if(((a%1000)%100)>0) tenstoword(a);
		if(a==0) System.out.print("zero");
		
		
	}
	public static boolean numbertoword(int a){
		switch(a){
			case 1: System.out.print("one"); return true;
			case 2: System.out.print("two"); return true;
			case 3: System.out.print("three"); return true;
			case 4: System.out.print("four"); return true;
			case 5: System.out.print("five"); return true;
			case 6: System.out.print("six"); return true;
			case 7: System.out.print("seven"); return true;
			case 8: System.out.print("eight"); return true;
			case 9: System.out.print("nine"); return true;
		}
		return false;
	}
	public static void tenstoword(int A){
		int a = (A%1000)%100;
		if(A/100>0) System.out.print("and ");
		if(a/10 != 1){
				switch(a/10){
				case 2: System.out.print("twen");break;
				case 3: System.out.print("thir");break;
				case 5: System.out.print("fif");break;
				case 8: System.out.print("eigh");break;
				case 4:
				case 6:
				case 7:
				case 9: numbertoword(((a%1000)%100)/10);
			}
			if(a/10 > 1)System.out.print("ty ");
			numbertoword(a%10);
		}else{
			switch(a%10){
				case 0: System.out.print("ten");break;
				case 1: System.out.print("eleven");break;
				case 2: System.out.print("twelve");break;
				case 3: System.out.print("thirteen");break;
				case 8: System.out.print("eighteen");break;
				case 4: 
				case 5:
				case 6:
				case 7:
				case 9: numbertoword(a%10); System.out.print("teen");
			}
		}
	}
}
