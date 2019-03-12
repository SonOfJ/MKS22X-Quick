import java.util.*;
public class Quick {
  public static int partition(int[] data, int start, int end) {
    Random randgen = new Random();
    int index = start + randgen.nextInt(end - start + 1); //Index of pivotal value.
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
      } else if (data[start] == value) {
        start = start + 1; //Leave it.
      }
    }
    return index;
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
}
