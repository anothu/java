class sort {
    public static void partSort(int left, int right, int a[], int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            partSort(left, mid, a, temp);
            partSort(mid + 1, right, a, temp);
            int lIndex = left;
            int rIndex = mid+1;
            int index = left;
            while (lIndex <= mid && rIndex <= right) {
                if (a[lIndex] < a[rIndex])
                    temp[index++] = a[lIndex++];
                else
                    temp[index++] = a[rIndex++];
            }
            while (lIndex <= mid)
                temp[index++] = a[lIndex++];
            while (rIndex <= right)
                temp[index++] = a[rIndex++];
            for (int i = left; i <= right; i++)
                a[i] = temp[i];
        }
    }

    public void arraySort(int a[]) {
        int temp[] = new int[a.length];
        partSort(0, a.length - 1, a, temp);
    }

    public static void main(String[] args) {
        int a[] = { 1, 43, 4, 2, 5, 7, 8, 234, 2344 };
        new sort().arraySort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}