import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SusuGroup
 */
public class SusuGroup {
    public BigDecimal weeklyAmount;
    public ArrayList<String> members = new ArrayList<>();
    public int currentWeek = 1;
    public int nextReceiver = 0;
    public HashMap<Integer, HashMap<String, BigDecimal>> payments = new HashMap<>();
    public ArrayList<String> payoutHistory = new ArrayList<>();

    public SusuGroup(BigDecimal weeklyAmount) {
        this.weeklyAmount = weeklyAmount;
        payments.put(1, new HashMap<>());
    }

    public void registerMember(String name) {
        name = name.trim();
        if (name.equals("")) throw new RuntimeException("empty name");
        if (members.contains(name)) throw new RuntimeException("already exists");
        if (currentWeek > 1) throw new RuntimeException("too late to add");
        members.add(name);
        System.out.println("Added " + name);
    }

    public void recordContribution(String memberName, BigDecimal amount) {
        if (!members.contains(memberName)) throw new RuntimeException("unknown member");
        HashMap<String, BigDecimal> thisWeek = payments.get(currentWeek);
        BigDecimal already = thisWeek.getOrDefault(memberName, BigDecimal.ZERO);
        BigDecimal total = already.add(amount);
        if (total.compareTo(weeklyAmount) > 0) throw new RuntimeException("paying too much");
        thisWeek.put(memberName, total);
        System.out.println(memberName + " paid " + amount);
    }

    public void closeWeek() {
        HashMap<String, BigDecimal> thisWeek = payments.get(currentWeek);
        for (String m : members) {
            BigDecimal paid = thisWeek.getOrDefault(m, BigDecimal.ZERO);
            if (paid.compareTo(weeklyAmount) < 0) {
                System.out.println("Cannot close, " + m + " still owes " + weeklyAmount.subtract(paid));
                return;
            }
        }
        BigDecimal pot = weeklyAmount.multiply(new BigDecimal(members.size()));
        String receiver = members.get(nextReceiver);
        payoutHistory.add("Week " + currentWeek + " -> " + receiver + " gets " + pot);
        System.out.println("Week " + currentWeek + " closed. " + receiver + " gets " + pot);
        nextReceiver = (nextReceiver + 1) % members.size();
        currentWeek++;
        payments.put(currentWeek, new HashMap<>());
    }

    public void showReport() {
        System.out.println("\nREPORT week " + currentWeek);
        System.out.println("Members: " + members);
        System.out.println("History: " + payoutHistory);
        System.out.println("Done.\n");
    }
}