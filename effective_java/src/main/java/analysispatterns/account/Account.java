package analysispatterns.account;

import analysispatterns.dualtimerecord.MfDate;
import analysispatterns.name.NamedObject;
import analysispatterns.quantity.Currency;
import analysispatterns.quantity.Money;
import analysispatterns.range.DateRange;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Objects;

public class Account extends NamedObject {

    private final Collection<Entry> entries;

    private Currency currency;

    public Account(String name) {
        super(name);
        this.currency = new Currency("dollar");
        this.entries = Lists.newArrayList();
    }

    public void Entry(final Money money, MfDate date) {
        Preconditions.checkState(Objects.equals(this.currency, money.currency()));
        entries.add(new Entry(money, date));
    }

    private Money balance(DateRange period) {
        Money result = new Money(0, this.currency);

        return result;
    }



}
