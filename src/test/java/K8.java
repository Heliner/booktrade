import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class K8 {
    @Test
    public void testFilter() {
        List list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        /*filter作用是选取符合lambda表达式的值*/
        list.stream().mapToInt(pa -> (int) pa).filter((pa) -> {
            return pa % 2 == 0;
        }).forEach(System.out::println);
//        list.stream().sorted((param1,param2)->{return param1>param2?1:-1;}).forEach(System.out::println);
    }
}
