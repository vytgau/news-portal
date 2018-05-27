package com.newsportal.models;

import com.newsportal.models.enums.Gender;
import com.newsportal.models.enums.Role;
import com.newsportal.validation.UniqueEmail;
import com.newsportal.validation.UniqueUsername;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "users")
public class User {

    //TODO check fields for special characters
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 15, message = "Prisijungimo vardą turėtų sudaryti nuo 2 iki 15 simbolių")
    @UniqueUsername(message = "Šis prisijungimo vardas yra užimtas")
    private String username;

    @NotNull
    @Size(min = 2, max = 15, message = "Vartotojo vardą turėtų sudaryti nuo 2 iki 15 simbolių")
    private String firstname;

    @NotNull
    @Size(min = 2, max = 15, message = "Vartotojo pavardę turėtų sudaryti nuo 2 iki 15 simbolių")
    private String lastname;

    @NotNull
    @NotEmpty(message = "Slaptažodis negali būti tuščias")
    private String password;

    @NotNull
    @Column(unique = true)
    @NotEmpty(message = "Įveskite e. paštą")
    @UniqueEmail(message = "Šis e. paštas yra užimtas")
    @Email(message = "Nurodykite tinkamą e. paštą")
    private String email;

    //@Past(message = "Pasirinkite datą iš praeities")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    public User() {
    }

    //Laikinas default User sukurimas testavimui
    public User(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = "default_password";
        this.email = "default_email";
        this.birthdate = new Date();
        this.isBanned = false;
        this.gender = Gender.MALE;
        this.role = Role.REGULAR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String dateToString() {
        if (birthdate != null) {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(birthdate);
        }
        else
            return "Nenustatyta";
    }

    public String isBlockedString() {
        if (isBanned)
            return "Taip";
        else
            return "Ne";
    }

    public String getGenderString() {
        if (gender != null) {
            if (gender.toString().equals("MALE"))
                return "Vyras";
            else
                return "Moteris";
        }
        else return "Nenustatyta";
    }

    public String getRoleString() {
        if (role != null) {
            switch (role.toString()) {
                case "ADMIN":
                    return "Administratorius";
                case "WRITER":
                    return "Rašytojas";
                case "REGULAR":
                    return "Paprastas naudotojas";
                default:
                    return "Neatpažintas";

            }
        }
        else return "Nenustatyta";
    }

    public void changeBan()
    {
            isBanned = !isBanned;
    }
}
