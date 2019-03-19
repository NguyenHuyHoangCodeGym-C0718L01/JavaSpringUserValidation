package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String number = user.getPhoneNumber();
        String lastName = user.getLastName();
        String firstName = user.firstName;
        int age = user.getAge();
        String email = user.getEmail();
        //rỗng
        ValidationUtils.rejectIfEmpty(errors, "firstName", "number.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "number.empty");
        //ko đủ chiều dài
        if(lastName.length() > 45 || lastName.length() < 5 ){
            errors.rejectValue("lastName", "name.length");
        }
        if(firstName.length() > 45 || firstName.length() < 5){
            errors.rejectValue("lastName", "name.length");
        }

        if (number.length()>11 || number.length()<10){
            errors.rejectValue("phoneNumber", "number.length");
        }

        if(age < 18){
            errors.rejectValue("age", "age.length");
        }
        //bắt đầu bằng 0
        if (!number.startsWith("0")){
            errors.rejectValue("phoneNumber", "number.startsWith");
        }
        //kiểu số
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber", "number.matches");
        }
        if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            errors.rejectValue("email", "email.matches");
        }
    }
}
