class RemoveDuplicatesMain{
	public static void main(String[] args){
		String Shafeeq = "Hello the Shafeeq you are               the best and the worst Shafeeq the Shaf ShafS";
		System.out.println("Input string");
		System.out.println(Shafeeq);
		System.out.println("Duplicates Removed");
		RemoveDuplicates(Shafeeq);
	}
	public static void RemoveDuplicates(String s){
		int i = 0;
		for(int j = 0; j<s.length(); j++)
		{
			if((j==s.length()-1)&&(s.substring(j,j+1) != " ")){
				TokenChecker(i, j+1, s + " ");
			}
			else if(s.substring(j,j+1).equals(" ")){
				TokenChecker(i,j,s);
				i = j+1;
			}
		}
	}
	public static void TokenChecker(int i, int j, String s){
		boolean Dupes = false;
		int tokenlength = j-i;
		for(int n = 1; j - n - tokenlength >= 0; n++){
			if(s.substring(j - n - tokenlength, j - n + 1).equals(s.substring(i,j+1))){
				Dupes = true;
			}
		}
		if(Dupes == false) System.out.print(s.substring(i,j+1));
	}
}