package ts.edu.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @Size(min = 3, max = 45, message = "{name.size.error}")
    @NotNull
    private String name;

    @Size(min = 5, max = 45)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
