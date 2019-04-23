
class MathConverter{
	public static void main(String[] args){
		int x = 88;
		System.out.print("The decimal value of x is "+x+" and the binary value is ");
		String X = math2.decimalToBinary(x);
		System.out.print(".\n");
		System.out.println(X);
		System.out.println(math2.binaryToDecimal(X));
		// System.out.println("\n"+ catty.SumUpTo3Digits(x));
	}
}	
	
class math2{
	public static String decimalToBinary(int x){
		String X;
		if(x==0) return "0";
		else{
			if(x/2==1){
				System.out.print(x/2);
				X = ""+x/2;
			}
			else X = decimalToBinary(x/2);
			System.out.print(x%2);
			return X+x%2;
		}
	}
	// public static int binaryToDecimal(String S){
		// int num=0;
		// for(int i =1;i<=S.length();i++){
			// num+=Integer.parseInt(S.substring(S.length()-i,S.length()-i+1))*Math.pow(2,i-1);
		// }
		// return num;
	// }
	
	public static int binaryToDecimal(String S){
		int power = 1,num = 0;
		for(int i = S.length();i>=1;i--){
			num += Integer.parseInt(S.substring(i-1,i))*power;
			power *= 2;
		}
		return num;
	}
}