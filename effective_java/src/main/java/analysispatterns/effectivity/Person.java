package analysispatterns.effectivity;

import analysispatterns.dualtimerecord.MfDate;
import analysispatterns.name.NamedObject;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class Person extends NamedObject {

    private List<Employment> employments = Lists.newArrayList();

    public Person(String name) {
        super(name);
    }

    void addEmployment(Employment employment) {
        this.employments.add(employment);
    }

    void addEmployment(Company company, MfDate startDate) {
        this.addEmployment(new Employment(company, startDate));
    }

    List<Employment> employments() {
        return this.employments.stream().collect(Collectors.toUnmodifiableList());
    }


}
