import java.util.*;
public class Quick {
  public static int partition(int[] data, int start, int end) {
    Random randgen = new Random();
    int index;
    if (data[start] >= data[(start + end) / 2] && data[start] <= data[end] || data[start] <= data[(start + end) / 2] && data[start] >= data[end]) { //Choosing the median of the start, middle, and end values to be the pivotal value.
      index = start;
    }
    if (data[(start + end) / 2] >= data[start] && data[(start + end) / 2] <= data[end] || data[(start + end) / 2] <= data[start] && data[(start + end) / 2] >= data[end]) {
      index = (start + end) / 2;
    } else {
      index = end - 1;
    }
    int temp = data[start]; //Switch start and pivot.
    data[start] = data[index];
    data[index] = temp;
    int value = data[start]; //Value of pivotal element.
    index = start;
    start = start + 1;
    while (start <= end) { //When the loop hasn't reached the end.
      if (data[start] > value) { //If the value is bigger than the pivot, it should go to the very right.
        int hold = data[end];
        data[end] = data[start];
        data[start] = hold;
        end = end - 1; //The value at the end is now sorted.
      } else if (data[start] < value) { //If the value in question belongs on the left side.
        int hold = data[index];
        data[index] = data[start];
        data[start] = hold;
        start = start + 1; //Check the next value.
        index = index + 1; //The value at the beginning is also sorted.
      } else if (data[start] == value) { //Duplicate.
        if (randgen.nextInt(2) == 0) { //50% chance of moving to the end.
          int hold = data[end];
          data[end ] = data[start];
          data[start] = hold;
          end = end - 1;
        } else { //50% chance of moving to the beginning.
          int hold = data[index];
          data[index] = data[start];
          data[start] = hold;
          start = start + 1;
          index = index + 1;
        }
      }
    }
    return index;
  }
  private static int[] partitionDutch(int[] data, int lo, int hi) {
    int lt = lo; //Index for lower bound.
    int gt = hi; //Index for higher bound.
    int i = lt + 1; //Index that will be used to go through the region that must be examined.
    int index = lo;
    while (i <= gt) { //While i has not surpassed the upper bound yet.
      if (data[i] == data[index]) {
        i = i + 1; //Bounds don't change because there is a duplicate.
      } else if (data[i] < data[index]) { //Smaller than the pivotal value.
        int hold = data[lt]; //Switching time.
        data[lt] = data[i];
        data[i] = hold;
        lt = lt + 1; //Lower bound obviously has to move up.
        i = i + 1;
        index = index + 1;
      } else { //Bigger than the pivotal value.
        int hold = data[gt]; //Switching time.
        data[gt] = data[i];
        data[i] = hold;
        gt = gt - 1; //Upper bound must move down.
      }
    }
    int[] ans = new int[]{lt, gt};
    return ans;
  }
  public static int quickselect(int[] data, int k) {
    int index = partition(data, 0, data.length - 1); //Start off by checking the whole list.
    while (k - 1 != index) {
      if (k - 1 < index) { //k is smaller than the randomly chosen index.
        index = partition(data, 0, index); //We know k has to be between 0 and the index.
      } else {
        index = partition(data, index, data.length - 1); //We know k has to be between the index and the end of the array.
      }
    }
    return data[index];
  }
  public static void quicksort(int[] data) {
    quicksortH(data, 0, data.length - 1);
  }
  private static void quicksortH(int[] data, int lo, int hi) {
    if (lo >= hi) { //Done looking through the range.
      return; //Stop the function by returning nothing.
    }
    int[] dutch = partitionDutch(data, lo, hi);
    quicksortH(data, lo, dutch[0] - 1); //Deals with the first part of the array, which goes from the start to lt - 1.
    quicksortH(data, dutch[1] + 1, hi); //Deals with the other part, which goes from gt + 1 to the end.
  }
}
