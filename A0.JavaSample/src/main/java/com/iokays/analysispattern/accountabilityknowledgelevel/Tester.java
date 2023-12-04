package com.iokays.analysispattern.accountabilityknowledgelevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 测试类
 */
public class Tester {

    Party mark;
    Party tom;
    Party stMarys;

    // 医院
    private PartyType hospital = new PartyType("Hospital");
    // 医生
    private PartyType doctor = new PartyType("Doctor");
    // 顾问
    private PartyType patient = new PartyType("Patient");
    // 顾问
    private PartyType consultant = new PartyType("Consultant");

    // 雇佣
    private ConnectionAccountabilityType appointment
            = new ConnectionAccountabilityType("Appointment");

    // 监督
    private ConnectionAccountabilityType supervision
            = new ConnectionAccountabilityType("Supervises");

    @BeforeEach
    public void setUp() {
        // 医院可以雇佣医生
        appointment.addConnectionRule(hospital, doctor);
        // 医院可以雇佣顾问
        appointment.addConnectionRule(hospital, patient);

        //医生可以监督医生
        supervision.addConnectionRule(doctor, doctor);
        //医生可以监督顾问
        supervision.addConnectionRule(consultant, doctor);
        //顾问可以监督顾问
        supervision.addConnectionRule(consultant, consultant);

        mark = new Party("mark", consultant);
        tom = new Party("tom", consultant);
        stMarys = new Party("stMarys", consultant);
    }

    @Test
    public void testNoConnectionRule() {
        assertThrows(Exception.class, () -> Accountability.create(mark, tom, appointment));
    }

}
