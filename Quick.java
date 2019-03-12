import java.util.*;
public class Quick {
  private Random randgen;
  public static int partition(int[] data, int start, int end) {
    int index = start + randgen.nextInt(end - start); //Index of pivotal value.
    int value = data[index]; //Value of pivotal element.
    data[index] = data[start]; //Switch start and pivot.
    data[start] = value;
    index = start;
    start = start + 1;
    while (start != end) { //When the loop hasn't reached the end.
      if (data[start] > pivot) { //If the value is bigger than the pivot, it should go to the very right.
        int hold = data[end];
        data[end] = data[start];
        data[start] = hold;
        end = end - 1; //The value at the end is now sorted.
      } else {
        start = start + 1; //Check the next value.
      }
    }
    if (data[start] < pivot) { //If the value in question belongs on the left side.
      data[start] = value;
      data[index] = data[start];
      index = start;
    } else { //If the value in question belongs on the right side.
      data[start - 1] = value;
      data[index] = data[start - 1];
      index = start;
    }
  }
}
