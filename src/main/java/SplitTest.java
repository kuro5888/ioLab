public class SplitTest {

    void split()
    {
        String myString = "the quick brown fox";
        String[] arr = myString.split(" ", 2);

        String firstWord = arr[0];   //the
        String theRest = arr[1];     //quick brown fox

        System.out.println(firstWord);
        System.out.print(theRest);
    }
}
