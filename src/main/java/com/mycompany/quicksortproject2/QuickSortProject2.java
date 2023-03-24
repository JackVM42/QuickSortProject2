/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.quicksortproject2;

import java.util.Random;

/**
 *
 * @author jackvanmilligen
 */
public class QuickSortProject2 {

    
    
    
    public static void main(String[] args) {
        
        //make an array of certain length with certain max number
        int[] arr = makeArray(10, 10);
        
        
        //print before staement with order of array items using printArray method
        System.out.println("Before: ");
        printArray(arr);
        
        //use quicksort method on our array
        quicksort(arr);
        
        //print out after statement
        System.out.println("After Accending: ");
        printArray(arr);
        
        //For reverse
        
        //use quicksort method on our array
        revQuicksort(arr);
        
        //print out after statement
        System.out.println("After Reverse: ");
        printArray(arr);
    }
    
    
    
    /**
     * Sorts an array of ints in accending order
     * Overloaded method which allows you to quicksort an int array just passing an array without needing and upper and lower index
     * @param arr the array which is to be sorted
     */
    private static void quicksort(int[] arr) {
    quicksort(arr, 0, arr.length - 1, 1);
  }
    
    /**
     * Method which uses the quicksort algorithm in order to sort an array of ints in order based off order
     * @param arr Array to be sorted
     * @param lowInd Index of the first item in the array to be sorted
     * @param highInd Index of the last item to be sorted
     * @param order 1 means accending, 0 means decending
     */
    private static void quicksort(int[] arr, int lowInd, int highInd, int order) {
        
        //makes sure that if your algorithm is tyrying to sort a single number it will just quit here since it is already in order
    if (lowInd >= highInd) {
      return;
    }

    int pivot = medianPivot(arr, lowInd, highInd);
    
    //partions our array section using the partition method and then gets a return of the left pointer variable at the end of teh partition method
    //For accending order
    int lp = 0;
    
    if(order == 1){
        lp = partition(arr, lowInd, highInd, pivot);
    }
    
    //for decending
    if(order == 0){
        lp = revPartition(arr, lowInd, highInd, pivot);
    }


    //recursively quicksorts our smaller and smaller sections until the sections become of length one and the return statement at the top runs
    if(lp - 1 - lowInd > 10){
        quicksort(arr, lowInd, lp - 1, order);
    }
    else{
        insertionSort(arr, lowInd, lp - 1, order);
    }
    
    if(highInd - lp + 1 > 10){
       quicksort(arr, lp + 1, highInd, order); 
    }
    else{
       insertionSort(arr, lp + 1, highInd, order);
    }

  }
    
    /**
     * This method takes a array and a pivot value which is at the end of the sub array and places all of the values larger than the pivot to the right and all the smaller ones to the left
     * @param arr array to be sorted
     * @param lowInd the index of the first value in the partition section
     * @param highInd index of the last value in the partition section
     * @param pivot value of the pivot number
     * @return the value of the left pointer at the end for use in the quicksort method
     */
    private static int partition(int[] arr, int lowInd, int highInd, int pivot) {
    
       //left pointer starts at the index of the bottom if the partition section and right pointer starts one left of the pivot number
    int lp = lowInd;
    int rp = highInd - 1;

    //stops running once the two pointers have reached eachother
    while (lp < rp) {

      // lp works right through the array until it finds a number greater than the pivot number, or reaches the right pointer.
      while (arr[lp] <= pivot && lp < rp) {
        lp++;
      }

      // rp works left through the array until it finds a number less than the pivot number, or reaches the left pointer.
      while (arr[rp] >= pivot && lp < rp) {
        rp--;
      }
        
      //once the left pointer has a number bigger than the pivot and the right pointer has a number smaller we can just switch them
      swap(arr, lp, rp);
    }
    
    //makes sure that the last number is in order
    if(arr[lp] > arr[highInd]) {
      swap(arr, lp, highInd);
    }
    else {
      lp = highInd;
    }
    
    //returns the value of left pointer
    return lp;
  }
    
    
    /**
     * Will swap the two items in the array at the two passed indices
     * @param arr1 the array which the swap will occur in
     * @param index1 the first swap index
     * @param index2 the second swap index
     */
    private static void swap(int[] arr1, int index1, int index2){
        
        int tempstore = arr1[index1];
        arr1[index1] = arr1[index2];
        arr1[index2] = tempstore;
    }
    
    
    /**
     * Prints a section of the array out
     * @param arr Array to print out
     * @param lowInd Index of first item to include
     * @param highInd Index of last item to be included
     */
    private static void printArray(int[] arr, int lowInd, int highInd){
        
        for(int i = lowInd; i <= highInd; i++){
            System.out.println("Index " + i + ": " + arr[i]);
        }
    }
    
    
    /**
     * Prints out the entire array
     * @param arr array to be printed
     */
    private static void printArray(int[] arr){
        
        for(int i = 0; i < arr.length; i++){
            System.out.println("Index " + i + ": " + arr[i]);
        }
    }
    
    /**
     * Makes an integer arry full of random ints
     * @param length length of array
     * @param maxNum largest number which can be included in the array
     * @return the array which has been made
     */
    private static int[] makeArray(int length, int maxNum){
        
        Random rand = new Random();
        
        //make array length long and fill it with random ints
        int[] arr = new int[length];
        for(int i = 0; i <= length - 1; i++){
            arr[i] = rand.nextInt(maxNum);
        }
        return arr;
    }
    
    private static int median(int[] arr, int lowInd, int highInd){
        
        int midInd = (highInd - lowInd)/2 + lowInd;
        
        //find max
        int max = arr[lowInd];
        
        if(arr[midInd] > max){
            max = arr[midInd];
        }
        if(arr[highInd] > max){
            max = arr[highInd];
        }
        
        
        //find min
        int min = arr[lowInd];
        
        if(arr[midInd] < min){
            min = arr[midInd];
        }
        if(arr[highInd] < min){
            min = arr[midInd];
        }
        
        //find pivot value
        int pivot = arr[lowInd] + arr[midInd] + arr[highInd] - max - min;
        
        //find ind of median
        if(pivot == arr[lowInd]){
            return lowInd;
        }
        
        if(pivot == arr[midInd]){
            return midInd;
        }
        
        if(pivot == arr[highInd]){
            return highInd;
        }
            
        return highInd;
    }
    
    /**
     * picks a random index within the sort range to set as our pivot then sets pivot equal to that indices value and moves that index to the end
     * @param arr array to find pivot in
     * @param lowInd low index we are working with
     * @param highInd the high index of teh array section we are working on
     * @return the pivot value
     */
    private static int randomPivot(int[] arr, int lowInd, int highInd){
         
        int pivotInd = new Random().nextInt(highInd - lowInd) + lowInd;
        int pivot = arr[pivotInd];
        swap(arr, pivotInd, highInd);
        return pivot;
    }
    
    /**
     * picks median of teh first last and mid value in the array and uses that as the pivot
     * @param arr array to chose pivot in
     * @param lowInd low index we are working with
     * @param highInd the high index of the array section we are working on
     * @return the value of the pivot
     */
    private static int medianPivot(int[] arr, int lowInd, int highInd){
            
        int pivotInd = median(arr, lowInd, highInd);
        int pivot = arr[pivotInd];
        swap(arr, pivotInd, highInd);
        return pivot;
    }
    
    /**
     * Simple insertion sort algorithm there to use when sub arrays get too short
     * @param arr array to be sorted
     * @param lowInd low index of section of array being sorted
     * @param highInd high index of part of array being sorted
     * @param order Accending or decending
     */
    private static void insertionSort(int[] arr, int lowInd, int highInd, int order){
        
        //if order is accending
        if(order == 1){
            //iterates through each item in the array
	    for (int i = lowInd + 1; i <= highInd; i++) {
                //this one iterates back through the array one element at a time
		    for(int j = i ; j > lowInd ; j--){
			    
                            if(arr[j] < arr[j-1]){
				    swap(arr, j, j-1);
			    }
		    }
	    }
        }
        
        //if order is decending
        if(order == 0){
            //iterates through each item in the array
	    for (int i = lowInd + 1; i <= highInd; i++) {
                //this one iterates back through the array one element at a time
		    for(int j = i ; j > lowInd ; j--){
			    
                            if(arr[j] > arr[j-1]){
				    swap(arr, j, j-1);
			    }
		    }
	    }
        }

            
    }
    
    
    /**
     * Sorts an array of ints in decending order
     * Overloaded method which allows you to quicksort an int array just passing an array without needing and upper and lower index
     * @param arr the array which is to be sorted
     */
    private static void revQuicksort(int[] arr) {
    quicksort(arr, 0, arr.length - 1, 0);
  }
    
    
    
    private static int revPartition(int[] arr, int lowInd, int highInd, int pivot) {
    
       //left pointer starts at the index of the bottom if the partition section and right pointer starts one left of the pivot number
    int lp = lowInd;
    int rp = highInd - 1;

    //stops running once the two pointers have reached eachother
    while (lp < rp) {

      // lp works right through the array until it finds a number greater than the pivot number, or reaches the right pointer.
      while (arr[lp] >= pivot && lp < rp) {
        lp++;
      }

      // rp works left through the array until it finds a number less than the pivot number, or reaches the left pointer.
      while (arr[rp] <= pivot && lp < rp) {
        rp--;
      }
        
      //once the left pointer has a number bigger than the pivot and the right pointer has a number smaller we can just switch them
      swap(arr, lp, rp);
    }
    
    //makes sure that the last number is in order
    if(arr[lp] < arr[highInd]) {
      swap(arr, lp, highInd);
    }
    else {
      lp = highInd;
    }
    
    //returns the value of left pointer
    return lp;
  }
    
}

