package krg.petr.naumen.model.enumeration;

public enum Status {

    // Active Inactive Suspended Closed
    ACTIVE(0, "Активен"),
    INACTIVE(1, "Неактивен"),
    SUSPENDED(2, "Приастановлен"),
    CLOSED(3, "Закрыт");

    private final int code;
    private final String displayName;

    Status(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Status fromCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (Status activity : Status.values()) {
            if (activity.code == code) {
                return activity;
            }
        }

        throw new IllegalArgumentException("No activity found for code " + code);
    }
}