package roughWorkPackage;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestString {
    public static void main(String[] args) {
//        String str2 = "abc";
//        String str1 = "xyz";
//
//        if(str1.compareTo(str2)<0){
//            System.out.println("small!");
//        }else{
//            System.out.println("big!!");
//        }

        List<Integer> values = List.of(1,2,3,4,5,5,5,5,5,5,5,5,2,34,55,67);
        Random random = new Random();
        int i = random.nextInt(values.size()-1);
        System.out.println(i+" : "+values.get(i));





    }
}
