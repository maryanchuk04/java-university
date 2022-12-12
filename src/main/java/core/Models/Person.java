package core.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.Enums.Sex;
import core.Exceptions.ValidationException;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


public class Person extends BaseModel {
    @NotEmpty(message = "FirstName should be set")
    @JsonProperty("firstName")
    private String firstName;
    @NotEmpty(message = "LastName should be set")
    @JsonProperty("lastName")
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthday")
    @Nullable
    private LocalDate birthday;
    @NotNull
    @JsonProperty("sex")
    private Sex sex;
    public Person(){}

    protected Person(Builder<?> builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthday = builder.birthday;
        this.sex = builder.sex;
    }


    public Person(String firstName, String lastName, LocalDate birthday, Sex sex, UUID id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.setId(id);
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                '}';
    }

    public static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private Sex sex;
        private UUID id;

        public abstract T getThis();

        public T withFirstName(String name) {
            this.firstName = name;
            return this.getThis();
        }

        public T withBirthday(LocalDate Date) {
            this.birthday = Date;
            return this.getThis();
        }

        public T withLastName(String lastName) {
            this.lastName = lastName;
            return this.getThis();
        }

        public T withSex(Sex sex) {
            this.sex = sex;
            return this.getThis();
        }

        public T withId(UUID id){
            this.id = id;
            return this.getThis();
        }

        public Person build() throws ValidationException {
            var person = new Person(this);
            var res = validate(person);
            return res;
        }

        public Person validate(Person obj) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Person>> violations = validator.validate(obj);
            if (violations.isEmpty()) {
                return obj;
            } else {
                StringBuilder sb = new StringBuilder();
                for (var violation : violations) {
                    sb.append(violation.getInvalidValue()).append(" : ").append(violation.getMessage());
                }
                return null;
            }
        }

    }

}
