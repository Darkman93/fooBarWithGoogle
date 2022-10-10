import java.util.Arrays;
import  java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static int[] solution(int[] data, int n) {
        // Your code here
        if(n == 0){
            return new int[0];
        }
        Map<Integer, Long> frequency = frequencyMap(Arrays.stream(data).boxed());
        return deleteElements(data, n, frequency);
    }
    
    public static <Integer> Map<Integer, Long> frequencyMap(Stream<Integer> elements){
        return elements.collect(
            Collectors.groupingBy(
                Function.identity(),
                HashMap::new,
                Collectors.counting()
                )
            );
    }
    
    public static int[] deleteElements(int[] datas, int n, Map<Integer, Long> map){
        int index = 0;
        int[] support = new int[datas.length];
        int max = 0;
        for(int i = 0; i<datas.length;i++){
            if(map.get(datas[i]) <= n){
                support[i] = 1;
                max++;
            }
            else{
                support[i] = 0;
            }
        }
        int[] result = new int[max];
        for(int i = 0; i<support.length;i++){
            if(support[i] == 1){
                result[index] = datas[i];
                index++;
            }
        }
        return result;
    }
}