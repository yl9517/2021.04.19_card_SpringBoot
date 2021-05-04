package com.springweb.domain.user;

import com.springweb.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name , String email , Role role){

        this.name = name;
        this.email = email;
        this.role = role;

    }
    //회원 업데이트
    public User update(String name){ //네이버,카카오 상에서 이름 바뀌면 해당 사이트에서도 바뀌어야 하기 때문
        this.name = name;
        return this;
    }


    public String getRolekey(){
        return this.role.getKey();
    }

}
