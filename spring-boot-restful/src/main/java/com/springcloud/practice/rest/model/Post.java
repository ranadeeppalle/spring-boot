package com.springcloud.practice.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"user"})
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
