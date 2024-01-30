import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

class Player {

    /**
     * @param protonsStart The initial number of protons
     * @param neutronsStart The initial number of neutrons
     * @param protonsTarget The desired number of protons
     * @param neutronsTarget The desired number of neutrons
     */
    public static List<String> solve(int protonsStart, int neutronsStart, int protonsTarget, int neutronsTarget) {
        
        List<String> actions = new ArrayList<>();

        while(protonsStart > protonsTarget || neutronsStart > neutronsTarget) {
            actions.add("ALPHA");
            protonsStart -= 2;
            neutronsStart -= 2;
        }

        while(protonsStart < protonsTarget) {
            actions.add("PROTON");
            protonsStart ++;
        }

        while(neutronsStart < neutronsTarget) {
            actions.add("NEUTRON");
            neutronsStart ++;
        }

        return actions;
    }

    /* Ignore and do not change the code below */
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * Try a solution
     */
    public static void trySolution(List<String> recipe) {
        System.out.println("" + gson.toJson(recipe));
    }

    public static void main(String args[]) {
        try (Scanner in = new Scanner(System.in)) {
            trySolution(solve(
                gson.fromJson(in.nextLine(), new TypeToken<Integer>(){}.getType()),
                gson.fromJson(in.nextLine(), new TypeToken<Integer>(){}.getType()),
                gson.fromJson(in.nextLine(), new TypeToken<Integer>(){}.getType()),
                gson.fromJson(in.nextLine(), new TypeToken<Integer>(){}.getType())
            ));
        }
    }
    /* Ignore and do not change the code above */
}
