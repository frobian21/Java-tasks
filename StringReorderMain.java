class StringReorderMain{
	public static void main(String[] args){
		String Shafeeq = "Hello the Shafeeq you are the best and the worst Shafeeq the Shaf ShafS";
		System.out.println("Input string");
		System.out.println(Shafeeq);
		System.out.println("String reordered");
		System.out.println(StringReorder(Shafeeq));
	}
	public static String StringReorder(String original){
		String ordered;
		int i = 0, minlength = original.length(), maxlength=0;
		if(original.substring(minlength-1,minlength).equals(" ")==false){
			original = original + " ";
		}
		for(int j = 0; j<original.length();j++){
			if(original.substring(j,j+1).equals(" ")){
				// length = j-i;
				if(j-i<minlength) minlength = j-i;
				if(j-i>maxlength) maxlength = j-i;
				i = ++j + 1;
			}
		}
		for(,minlength<=maxlength; minlength++){
			for(i = 0,j = 0;j<original.length()-minlength;j++){
				if(original.substring(j,j+1).equals(" ")){
					if(j-i == minlength){
						ordered = ordered + original.substring(i,j+1);
					}
					i = j+1;
				}
			}
		}
		return ordered;
	}
}