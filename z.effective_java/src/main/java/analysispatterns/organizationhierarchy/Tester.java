package analysispatterns.organizationhierarchy;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tester {

    @DisplayName("测试组织的父级,子级的数量.")
    @Test
    public void test() {
        final Organization operatingUnit = new OperatingUnit("operationUnit", null);
        final Organization region = new Region("region", operatingUnit);
        final Organization division = new Division("division", region);
        final Organization salesOffice = new SalesOffice("salesOffice", division);

        Assertions.assertEquals(3, operatingUnit.getDescendents().size());
        Assertions.assertEquals(3, salesOffice.getAncestors().size());

    }
}
