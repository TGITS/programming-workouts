import java.util.ArrayList;
import java.util.List;

public class Flattener {

    public List flatten(List list) {
        //return recursiveFlatten(list, new ArrayList());
        return iterativeFlatten(list);
    }

    private List recursiveFlatten(List list, List flattenList) {
        if(list == null || list.isEmpty()) {
            return flattenList;
        }
        var elem = list.get(0);
        if(elem instanceof List) {
            flattenList.addAll(recursiveFlatten((List) elem, new ArrayList<>()));
        } else if (elem != null){
            flattenList.add(elem);
        }
        return recursiveFlatten(list.subList(1, list.size()),flattenList);
    }

    private List iterativeFlatten(List list){
        List flattenList = new ArrayList();
        for(var elem:list) {
            if(elem instanceof List) {
                flattenList.addAll(iterativeFlatten((List) elem));
            } else if (elem != null){
                flattenList.add(elem);
            }
        }
        return flattenList;
    }
}