class RemoveDuplicatesMain{
	public static void main(String[] args){
		String Shafeeq = "Hello the Shafeeq you are   the best and the worst Shafeeq the Shaf ShafS";
		System.out.println("Input string");
		System.out.println(Shafeeq);
		System.out.println("Duplicates Removed");
		removeDuplicates(Shafeeq);
	}
	public static void removeDuplicates(String s){
		int i = 0;
		for(int j = 0; j<s.length(); j++)
		{
			if((j==s.length()-1)&&(s.substring(j,j+1) != " ")){
				tokenChecker(i, j+1, s + " ");
			}
			else if(s.substring(j,j+1).equals(" ")){
				tokenChecker(i,j,s);
				i = ++j;
			}
		}
	}
	public static void tokenChecker(int i, int j, String token){
		boolean Dupes = false;
		int tokenlen = j-i;
		for(int n = 1; j - tokenlen /*start of the string*/ - n >= 0; n++){
			if(token.substring(j - tokenlen - n, j - n + 1).equals(token.substring(i,j+1))){
				Dupes = true;
			}
		}
		if(Dupes == false) System.out.print(token.substring(i,j+1));
	}
}