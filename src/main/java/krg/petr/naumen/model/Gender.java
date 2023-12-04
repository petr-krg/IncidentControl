package krg.petr.naumen.model;

public enum Gender {

    MALE(0, "Мужской"),
    FEMALE(1, "Женский");

    private final int code;
    private  final String displayName;

    Gender(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Gender fromCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (Gender gender : Gender.values()) {
            if (gender.code == code) {
                return gender;
            }
        }

        throw new IllegalArgumentException("No gender found for code " + code);
    }
}