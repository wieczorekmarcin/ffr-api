package pl.cdv.ffr.model;

public enum UserType {
    RENTIER(1), TENAT(2);

    private int userType;

    UserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
