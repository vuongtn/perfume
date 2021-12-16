package com.dotv.perfume.service;

import com.dotv.perfume.dto.BillDTO;
import com.dotv.perfume.untils.MessageResponse;

import java.util.List;

public interface BillService {
    MessageResponse Order(BillDTO billDTO);
    List<BillDTO> getBillByStatus(int idAcc, int status);
    MessageResponse updateStatusBill(int status, int idBill);
}
