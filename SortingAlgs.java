// Sorting algorithm implementations
// Antonio Martinez

import java.util.Arrays;

public class SortingAlgs
{
    public static void main(String[] args)
    {
        int[] myArray = {10, 20, 5, 3, 17, 14, 25, 18};
        System.out.println("The original array is " + Arrays.toString(myArray));
        
        int[] testArray = myArray.clone();
        selectionSort(testArray);
        System.out.println("The array sorted by Selection Sort is " + 
                Arrays.toString(testArray));
        
        testArray = myArray.clone();
        mergeSort(testArray);
        System.out.println("The array sorted by Merge Sort is " + 
                Arrays.toString(testArray));
        
        testArray = myArray.clone();
        quickSort(testArray);
        System.out.println("The array sorted by Quick Sort is " + 
                Arrays.toString(testArray));
    }
    
    // find the index of the smallest element in the specified range
    public static int minIndex(int[] numArray, int first, int last)
    {
        int indMin = first;
        
        for (int i = first + 1; i <= last; i++)
            if (numArray[i] < numArray[indMin])
                indMin = i;
        
        return indMin;
    }
    
    // selection sort
    public static void selectionSort(int[] numArray)
    {
        int last = numArray.length - 1;
        
        for (int i = 0; i < last; i++)
        {
            int indMin = minIndex(numArray, i, last);
            int temp = numArray[indMin];
            numArray[indMin] = numArray[i];
            numArray[i] = temp;
        }
    }
    
    // merge two halves into one sorted array
    public static void merge(int[] numArray, int first, int last)
    {
        int mid = (first + last) / 2;
        int n = last - first + 1;
        int[] tempArray = new int[n];
        
        int i1 = first;
        int i2 = mid + 1;
        int j = 0;
        
        while (i1 <= mid && i2 <= last)
        {
            if (numArray[i1] < numArray[i2])
            {
                tempArray[j] = numArray[i1];
                i1++;
            }
            else
            {
                tempArray[j] = numArray[i2];
                i2++;
            }
            
            j++;
        }
        
        while (i1 <= mid)
        {
            tempArray[j] = numArray[i1];
            i1++;
            j++;
        }
        
        while (i2 <= last)
        {
            tempArray[j] = numArray[i2];
            i2++;
            j++;
        }
        
        for (j = 0; j < n; j++)
            numArray[first + j] = tempArray[j];
    }
    
    // recursive helper method for mergeSort
    public static void mergeSortRec(int[] numArray, int first, int last)
    {
        if (first < last)
        {
            int mid = (first + last) / 2;
            mergeSortRec(numArray, first, mid);
            mergeSortRec(numArray, mid + 1, last);
            merge(numArray, first, last);
        }
    }
    
    // merge sort
    public static void mergeSort(int[] numArray)
    {
        mergeSortRec(numArray, 0, numArray.length - 1);
    }
    
    // split an array into two sections, with all the elements less than the pivot in the 
    // left section and all the elements greater than the pivot in the right section
    public static int split(int[] numArray, int first, int last)
    {
        // set the first element as the pivot
        int pivot = first; // can also use pivot = (int)(Math.random() * numArray.length)        
        first++;
        
        while (true)
        {
            // starting from the left of the array, find the first element that is greater than the pivot
            while (first <= last && numArray[first] < numArray[pivot])
                first++;

            // starting from the right of the array, find the first element that is less than the pivot 
            while (first <= last && numArray[last] > numArray[pivot])
                last--;

            // swap the two elements
            if (first < last)
            {
                int temp = numArray[first];
                numArray[first] = numArray[last];
                numArray[last] = temp;
            }
            else
                break;
        }
        
        // swap the pivot with the element at index last
        int temp = numArray[last];
        numArray[last] = numArray[pivot];
        numArray[pivot] = temp;
        
        return last;
    }
    
    // recursive helper method for quickSort
    public static void quickSortRec(int[] numArray, int first, int last)
    {
        if (first < last)
        {
            int pivot = split(numArray, first, last);
            quickSortRec(numArray, first, pivot - 1);
            quickSortRec(numArray, pivot + 1, last);
        }
    }
    
    // quick sort
    public static void quickSort(int[] numArray)
    {
        quickSortRec(numArray, 0, numArray.length - 1);
    }
}
