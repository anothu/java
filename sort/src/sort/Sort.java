package sort;

import java.time.chrono.MinguoChronology;

public class Sort {
	static void bubble(float[] a) {
		for(int i = 0; i < a.length-1; i++) {
			for(int j = 0; j < a.length-1-i; j++) {
				if(a[j]>a[j+1]) {
					float temp = a[j];
					a[j] = a[j+1];
					a[j+1] =temp;
				}
			}
		}
	}
	
	static void selection(float[] a) {
		for(int i = 0; i < a.length-1; i++) {
			float min = a[i];
			int index = i;
			float temp;
			for(int j = i+1 ; j < a.length; j++) {
				if(a[j] < min ) {
					min = a[j];
					index = j;
				}			
			}
			temp = a[index];
			a[index] = a[i];
			a[i] = temp;
		}
	}
	
	static void insert(float a[]) {
		for(int i=1; i<a.length; i++) {
			float temp = a[i];
			for(int j=i-1; j>-1; j--) {
				if(temp<a[j]) {
					a[j+1] = a[j];
					a[j] = temp;
				}
			}
		}
	} 

	static void shell(float[] a) {
		int aLength = a.length;
		for(int gap=aLength/2; gap>0; gap/=2) {
			for(int i=gap; i<a.length; i++) {
				float temp = a[i];
				for(int j=i-gap; j>=0; j-=gap) {
					if(temp<a[j]) {
						a[j+gap] = a[j];						
						a[j] = temp;
					}
				}
			}
		}
	}
	
	static void combine(float[] a, int low, int mid, int high) {
		float[] temp = new float[high-low+1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		while(i<=mid && j<=high) {
			if(a[i]<a[j]) {
				temp[k++] = a[i++];
			}else {
				temp[k++] = a[j++];
			}
		}
		while(i<=mid) {
			temp[k++] = a[i++];
		}
		while(j<=high) {
			temp[k++] = a[j++];
		}
		for(int x=0;x<temp.length;x++){
			a[x+low] = temp[x];
		}
	}
	static void merge(float[] a,int low,int high) {
		if(low < high) {
			int mid = (low + high)/2;
			merge(a, low, mid);
			merge(a, mid+1, high);
			combine(a,low,mid,high);
		}
	}
	
	static int quick(float[] a, int left, int right) {
		int i = left, j = right;
		float index = a[left];
		while(i<j) {
			while(i<j && a[j]>=index) {
				j--;
			}
			if(i<j)
			{
				a[i] = a[j];
				i++;
			}
			
			while(i<j && a[i]<index) {
				i++;
			}
			if(i<j) {
				a[j] = a[i];
				j--;
			}	
		}
		a[i] = index;
		return i;
	}
	static void quicksort(float[] a, int l, int r) {
		if(l<r) {
			int i = quick(a, l, r);
			quicksort(a, l, i-1);
			quicksort(a, i+1, r);
		}
	}
	
	static void bulidMaxHeap(float[] a, int len) {
		for(int i = (len/2)-1; i>=0; i--) {			
			if(a[i] < a[2*i+1] && (2*i+1) < len) { //如果父节点小于左子
				float temp = a[i];
				a[i] = a[2*i+1];
				a[2*i+1] = temp;
			}
			if(((2*(2*i+1)+1) < len && a[2*i+1]<a[2*(2*i+1)+1]) || ((2*(2*i+1)+2) < len && a[2*i+1] < a[2*(2*i+1)+2])) {
				bulidMaxHeap(a, len);
			}
			
			if((2*i+2) < len && a[i]<a[2*i+2]) { //如果父节点小于右子
				float temp = a[i];
				a[i] = a[2*i+2];
				a[2*i+2] = temp;
			}
			if(((2*(2*i+2)+1) < len && a[2*i+2]<a[2*(2*i+2)+1]) || ((2*(2*i+2)+2)<len && a[2*i+1]<a[2*(2*i+2)+2])) {
				bulidMaxHeap(a, len);
			}
		}
	}
	static void swap(float[] a, int len) {
		float temp = a[0];
		a[0] = a[len-1];
		a[len-1] = temp;
	}
	static void heap(float[] a) {
		for(int i=a.length; i>0; i--) {
			bulidMaxHeap(a, i);
			swap(a, i);
		}
	}
	
	static float Min(float[] a) {
		float min = 999;
		for(float i:a) {
			if(i<min) {
				min = i;
			}
		}
		return min;
	}
	
	static float Max(float[] a) {
		float max = -1;
		for (float f : a) {
			if(f>max) {
				max = f;
			}
		}
		return max;
	}
	static void count(float[] a) {
		float min = Min(a);
		float max = Max(a);
		int len = a.length;
		int[] temp = new int[(int)(max-min+1)];
		for(int i=0; i<len; i++) {
			temp[(int)(a[i]-min)] += 1;
		}
		for(int i=0,index=0; i<temp.length; i++) {
			int item = temp[i];
			while(item-- != 0) {
				a[index++] = i+min;
			}
		}
	}

	static void radix(float[] a) {
		float max = a[0];
		
		for(float f:a) {
			if(f>max) {
				max = f;
			}		
		}
		for(int exp=1; max/exp>0; exp*=10) {
			float[] temp = new float[a.length];
			float[] buckets = new float[10];
			for(float f:a) {
				buckets[((int)f/exp)%10] ++;
			}
			for(int i=1; i<10; i++) {
				buckets[i] += buckets[i-1];
			}
			for(int i = a.length-1; i>=0; i--) {
				temp[(int)--buckets[(int)(a[i]/exp)%10]] = a[i];
			}
			System.arraycopy(temp, 0, a, 0, a.length);
		}
	}
	public static void main(String[] args) {
		float[] a = {8,2,3,5,1,8,4,9,48,54};
		radix(a);
		for (float f : a) {
			System.out.println(f);
		}
	}
}
