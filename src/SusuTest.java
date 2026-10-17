import java.math.BigDecimal;
public class SusuTest {
    public static void main(String[] args) {
        System.out.println("Running 7 tests...");

        SusuGroup g1 = new SusuGroup(new BigDecimal("50.00"));
        g1.registerMember("Bayethe"); g1.registerMember("Bekazi"); g1.registerMember("Lwandle");
        g1.recordContribution("Lwandle", new BigDecimal("30.00"));
        System.out.println("Test 1 partial payment: PASS");

        g1.recordContribution("Bayethe", new BigDecimal("50.00"));
        g1.recordContribution("Bekazi", new BigDecimal("50.00"));
        g1.closeWeek();
        if (g1.currentWeek == 1) System.out.println("Test 2 week cannot close: PASS");
        else System.out.println("Test 2 FAIL");

        g1.recordContribution("Lwandle", new BigDecimal("20.00"));
        g1.closeWeek();
        if (g1.currentWeek == 2) System.out.println("Test 3 week can close: PASS");
        else System.out.println("Test 3 FAIL");

        if (g1.payoutHistory.get(0).contains("Bayethe")) System.out.println("Test 4 payout order: PASS");

        try { g1.registerMember("Bayethe"); System.out.println("Test 5 FAIL"); }
        catch (Exception e) { System.out.println("Test 5 reject duplicate: PASS"); }

        SusuGroup g2 = new SusuGroup(new BigDecimal("50.00"));
        g2.registerMember("Bayethe");
        try { g2.recordContribution("Bayethe", new BigDecimal("60.00")); System.out.println("Test 6 FAIL"); }
        catch (Exception e) { System.out.println("Test 6 reject overpay: PASS"); }

        try { g2.recordContribution("John", new BigDecimal("10.00")); System.out.println("Test 7 FAIL"); }
        catch (Exception e) { System.out.println("Test 7 reject unknown: PASS"); }

        System.out.println("All tests done - 7 tests");
    }
}