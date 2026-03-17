package com.iokays.immutability.demo;

public final class ImmutableQuotaDemo {
    public static void main(String[] args) {
        MutableQuota mutable = new MutableQuota(10, 4);
        mutable.setLimit(3);

        QuotaSnapshot original = QuotaSnapshot.of(10, 4);
        QuotaSnapshot updated = original.reserve(3);

        QuotaEditor editor = original.toEditor();
        editor.expand(2).reserve(3);
        QuotaSnapshot batchResult = editor.build();

        System.out.println("[mutable]");
        System.out.println("quota = " + mutable);
        System.out.println("isValid = " + mutable.isValid());
        System.out.println("remaining = " + mutable.remaining());
        System.out.println();

        System.out.println("[immutable]");
        System.out.println("original = " + original);
        System.out.println("updated = " + updated);
        System.out.println("sameReference = " + (original == updated));
        System.out.println("originalUnaffected = " + (original.used() == 4));
        System.out.println();

        System.out.println("[mutable-companion]");
        System.out.println("editor = " + editor);
        System.out.println("snapshot = " + batchResult);
        System.out.println("sameValueAsOriginal = " + batchResult.equals(original));
    }
}
