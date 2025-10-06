package model;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
@NotBlank(message = "name can't be empty")
@Size(min = 2, max = 20)
@Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s\\-']+$")
@Column(name = "name", nullable = false)
        private String name;

@NotBlank(message = "email can't be empty")
@Column(name = "email", unique = true, nullable = false)
        private String email;

@NotNull(message = "enter valid age")
@Min(value = 18, message = "come back when you turn 18")
@Max(value = 120, message = "probably you are not alive")
@Column(name = "age", nullable = false)
        private Integer age;

        public User() {} //hiber

        public User(String name, String email, Integer age) {
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
    }

