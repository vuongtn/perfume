package com.dotv.perfume.dto;

import com.dotv.perfume.entity.CartId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private CartId id;
    private int amount;
}
