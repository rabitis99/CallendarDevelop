package com.example.schedule.entity;

import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// id 자동생성
    private Long id;

    @Column(length = 20,nullable = false)
    private String authorName;
    @Column(length = 100,nullable = false,unique = true)
    private String email;
    private String password;

    public Author(String authorName, String authorEmail, String authorPassword) {
        this.email=authorEmail;
        this.authorName=authorName;
        this.password=authorPassword;
    }

    public void updateAuthorName(String authorName){
        if (authorName==null|| authorName.isEmpty()){
                throw new CustomException(ErrorCode.NOT_NULL_AND_NOT);
        }
        this.authorName=authorName;
    }

    public void setPassword(String password) {
        if (authorName==null|| authorName.isEmpty()){
            throw new CustomException(ErrorCode.NOT_NULL_AND_NOT);
        }
        this.password = password;
    }
}
