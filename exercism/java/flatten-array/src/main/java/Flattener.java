import java.util.ArrayList;
import java.util.List;

public class Flattener {

    @SuppressWarnings("unchecked")
    public List<Object> flatten(List<Object> list) {
        List<Object> flattenList = new ArrayList<>();
        for(var elem:list) {
            if(elem instanceof List) {
                flattenList.addAll(flatten((List<Object>) elem));
            } else if (elem != null){
                flattenList.add(elem);
            }
        }
        return flattenList;
    }
}