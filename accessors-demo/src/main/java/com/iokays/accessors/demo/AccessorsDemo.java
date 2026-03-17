package com.iokays.accessors.demo;

public final class AccessorsDemo {
    private AccessorsDemo() {
    }

    public static void main(String[] args) {
        runPublicFieldBreakage();
        System.out.println();
        runAccessorProtection();
        System.out.println();
        runImmutablePublicFieldExample();
    }

    private static void runPublicFieldBreakage() {
        OpenTaskWindow window = new OpenTaskWindow(9, 18);
        window.endHour = 7;

        System.out.println("[public-fields]");
        System.out.println("window = startHour=" + window.startHour + ", endHour=" + window.endHour);
        System.out.println("isValid = " + window.isValid());
        System.out.println("durationMinutes = " + window.durationMinutes());
    }

    private static void runAccessorProtection() {
        SafeTaskWindow window = new SafeTaskWindow(9, 18);
        boolean blocked = false;
        try {
            window.setEndHour(7);
        } catch (IllegalArgumentException ex) {
            blocked = true;
        }

        System.out.println("[accessors]");
        System.out.println("blockedInvalidUpdate = " + blocked);
        System.out.println("window = " + window);
        System.out.println("durationMinutes = " + window.durationMinutes());
    }

    private static void runImmutablePublicFieldExample() {
        ImmutableTimePoint point = new ImmutableTimePoint(9, 30);

        System.out.println("[immutable-public-fields]");
        System.out.println("timePoint = " + point);
        System.out.println("hour = " + point.hour);
        System.out.println("minute = " + point.minute);
    }
}
