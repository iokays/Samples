package analysispatterns.party;

public class Tester {

    public static void main(String[] args) {
        final var party = SimplePartyFactory.create("person", "andy", "17620393016", "china", "592721086@qq.com");
        System.out.println(party.name());
    }
}
