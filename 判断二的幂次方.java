class test{
    public boolean idEr(int num){
        if(num==0||((num&(num-1))==0)){
	    return true;
	}
	return false;
    }
    public static void main(String []args){
    	System.out.println(new test().idEr(1023));
    }
}
