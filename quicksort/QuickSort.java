class Sort{
    public void quickSort(int left,int right,int nums[]){
        if(left>=right)
            return;
        int temp=nums[left];
        int lIndex=left;
        int rIndex = right;
        while(lIndex<rIndex){
            while(rIndex>lIndex&&nums[rIndex]>temp)
                rIndex--;
            nums[lIndex]=nums[rIndex];
            while(rIndex>lIndex&&nums[lIndex]<temp)
                lIndex++;
            nums[rIndex]=nums[lIndex];
        }
        nums[rIndex]=temp;
        quickSort(left, lIndex-1, nums);
        quickSort(lIndex+1, right, nums);
    }
    public static void main(String[] args) {
        int a[]={6,3,5,4,3123,12,312,43};
        new Sort().quickSort(0, a.length-1, a);
        for(int i:a){
            System.out.println(i);
        }
    }
}