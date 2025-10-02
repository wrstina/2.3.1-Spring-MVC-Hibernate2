package model;


import javax.persistence.*;

    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String Name;
        private String email;
        private Integer age;

        public User() {} //hiber

        public User(String Name, String email, Integer age) {
            this.Name = Name;
            this.email = email;
            this.age = age;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return Name; }
        public void setName(String Name) { this.Name = Name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
    }

