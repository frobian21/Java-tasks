class StringReorderMain{
	public static void main(String[] args){
		String Shafeeq = "Hello the Shafeeq you are the  best and the worst Shafeeq the Shaf ShafS";
		System.out.println("Input string");
		System.out.println(Shafeeq);
		System.out.println("String reordered");
		System.out.println(StringReorder(Shafeeq));
		System.out.println("\nInput string");
		System.out.println(Shafeeq);
		System.out.println("Words backwards");
		System.out.println(WordReverser(Shafeeq));
	}
	public static String StringReorder(String original){
		String ordered = "";
		int i = 0, j = 0, minlength = original.length(), maxlength=0;
		if(original.substring(minlength-1,minlength).equals(" ")==false){
			original = original + " ";
			minlength++;
		}
		for(j = 0; j<original.length();j++){
			if(original.substring(j,j+1).equals(" ") && ! original.substring(j-1,j).equals(" ")){
				if(j-i<minlength) minlength = j-i;
				if(j-i>maxlength) maxlength = j-i;
				i = ++j;
			}
		}
		for(;minlength<=maxlength; minlength++){
			for(i = 0,j = 0;j<original.length();j++){
				if(original.substring(j,j+1).equals(" ")){	
					if(j!=0 && !(original.substring(j-1,j).equals(" "))){
						if(j-i == minlength){
							ordered = ordered + original.substring(i,j+1);
						}
					}
					i = j+1;
				}
			}
		}
		return ordered;
	}
	public static String WordReverser(String original){
		int i = 0;
		String ordered = "";
		if(original.charAt(original.length()-1)!=' '){
			original = original + " ";
		}
		for(int j = 0; j<original.length(); j++)
		{
			if(original.substring(j,j+1).equals(" ")){
				for(int n=1;j-n-i>=0;n++){
					ordered = ordered + original.substring(j-n,j-n+1);
				}
				ordered = ordered + " ";
				i = j+1;
			}
		}
		return ordered;
	}
}