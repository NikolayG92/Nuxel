package com.example.nuxel.adsservice.model.VO;

import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.dtos.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ResponseTemplateVO {
    private User user;
    private List<Ad> ads;
}
