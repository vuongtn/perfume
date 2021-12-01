package com.dotv.perfume.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountDTO {
    private Integer id;
    //private Integer idRole;
    @NotBlank(message = "Không được để trống")
    private String userName;
    @NotBlank(message = "Không được để trống")
    private String password;
    @NotBlank(message = "Không được để trống")
    private String fullName;
    private String phone;
    private String address;
    @NotBlank(message = "Không được để trống")
    private String email;
    private String avatar;
    //private Boolean status;

}
