package designpatterns.mediator;

import com.google.common.collect.Lists;

import java.util.List;

public class ConcreteMediator extends Mediator {

    private List<Colleague> colleagues = Lists.newArrayList();

    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void replay(Colleague colleague) {
        colleagues.stream().filter(v -> !v.equals(colleague)).forEach(v -> v.receive());
    }
}
