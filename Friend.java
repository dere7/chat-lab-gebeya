public class Friend {
    private String username;
    private String fullName;
    private String lastIp;
    private String image;

    public Friend() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
