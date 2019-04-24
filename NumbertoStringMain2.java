class NumbertoStringMain2{
	public static void main(String[] args){
		int a = 0;
		
		try{
			a = Integer.parseInt(args[0]);
		}catch(NumberFormatException X){
			System.out.println("Digits only please.");
		}

		Math4.numberValue(a);
	}
}
	
class Math4{
	public static void numberValue(int a){
		if(a<0){
			System.out.print("minus ");
			a = -a;
		}
		if(a/1000000>0 &&a/1000000000==0){
			hundredsToWord(a/1000000);
			System.out.print(" million ");
		}
		thousandsToWord(a%1000000);
		if(a==0) System.out.print("zero");
		
	}
	public static void thousandsToWord(int a){
		if(a/1000>0 && a/1000000==0){
			hundredsToWord(a/1000);
			System.out.print(" thousand ");
		}
		hundredsToWord(a%1000);
	}
	public static void hundredsToWord(int a){
		if((a/100)>0){
			numberToWord((a/100));
			System.out.print(" hundred ");
		}
		if(((a%1000)%100)>0) tensToWord(a);
	}
	private static void numberToWord(int a){
		switch(a){
			case 1: System.out.print("one"); break;
			case 2: System.out.print("two"); break;
			case 3: System.out.print("three"); break;
			case 4: System.out.print("four"); break;
			case 5: System.out.print("five"); break;
			case 6: System.out.print("six"); break;
			case 7: System.out.print("seven"); break;
			case 8: System.out.print("eight"); break;
			case 9: System.out.print("nine"); break;
		}
	}
	private static void tensToWord(int A){
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
				case 9: numberToWord(((a%1000)%100)/10);
			}
			if(a/10 > 1)System.out.print("ty ");
			numberToWord(a%10);
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
				case 9: numberToWord(a%10); System.out.print("teen");
			}
		}
	}
}