package nl.dubio.auth;

import nl.dubio.models.Right;

public enum AdminRights {

    DILEMMAS(Constants.DILEMMAS), USERINFO(Constants.USERINFO), STATISTICS(Constants.STATISTICS);

    private final String right;

    AdminRights(String right) {
        this.right = right;
    }

    public final String toString() {
        return right;
    }

    public static AdminRights fromString(String rightString) {
        for (AdminRights right: AdminRights.values()) {
            if (rightString.equalsIgnoreCase(right.toString()))
                return right;
        }
        return null;
    }

    public boolean hasRight(Right right) {
        if (this == DILEMMAS) {
            return right.isCanEditDilemma();
        }
        if (this == USERINFO) {
            return right.isCanEditUserInfo();
        }
         if (this == STATISTICS) {
             return right.isCanViewStatistics();
         }
         return false;
    }

    public static class Constants {
        public static final String DILEMMAS = "dilemmas";
        public static final String USERINFO = "userinfo";
        public static final String STATISTICS = "statistics";
    }
}
