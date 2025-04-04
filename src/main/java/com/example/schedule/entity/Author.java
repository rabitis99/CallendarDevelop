package com.example.schedule.entity;

import com.example.schedule.Dto.requestDto.common.AuthorRequestDto;
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

    public Author(AuthorRequestDto authorRequestDto){
        this.email=authorRequestDto.getEmail();
        this.authorName=authorRequestDto.getAuthorName();
        this.password=authorRequestDto.getPassword();
    }
    public void updateAuthorName(String authorName){
        if (authorName==null){
                throw new RuntimeException("엥?");
        }
        this.authorName=authorName;
    }

    public void setPassword(String password) {
        if (password==null){
            throw new RuntimeException("엥?");
        }
        this.password = password;
    }
}
