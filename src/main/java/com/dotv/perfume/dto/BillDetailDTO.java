package com.dotv.perfume.dto;

import com.dotv.perfume.entity.BillId;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDTO {
    private BillId id;
    private Integer amount;
    private BigDecimal currentlyPrice;
}
