package com.banking.banking.Cotroller;

import com.banking.banking.DTO.TransferDTO;
import com.banking.banking.Model.Transfer;
import com.banking.banking.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    private static final double MAX_TRANSFER_AMOUNT = 1000.00;


    //Unrestricted Resource Consumption (API4)
    // Vulnerable endpoint
    @PostMapping("/vulnerable")
    public ResponseEntity<TransferDTO> transferVulnerable(@RequestBody TransferDTO transferDTO) {
        Transfer transfer = transferService.transfer(transferDTO);
        TransferDTO responseTransferDTO = new TransferDTO(transfer.getFromAccount().getId(), transfer.getToAccount().getId(), transfer.getAmount());
        return ResponseEntity.ok(responseTransferDTO);
    }

    //Unrestricted Resource Consumption (API4)
    // Secure endpoint
    @PostMapping("/secure")
    public ResponseEntity<TransferDTO> transferSecure(@RequestBody TransferDTO transferDTO) {
        if (transferDTO.getAmount() > 0) {
            return ResponseEntity.badRequest().body(null); // Request exceeds the limit
        }
        Transfer transfer = transferService.transfer(transferDTO);
        TransferDTO responseTransferDTO = new TransferDTO(transfer.getFromAccount().getId(), transfer.getToAccount().getId(), transfer.getAmount());
        return ResponseEntity.ok(responseTransferDTO);
    }
}

