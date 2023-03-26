package me.bakuleva.coursework3.controller;

import me.bakuleva.coursework3.dto.SockRequest;
import me.bakuleva.coursework3.exception.InSufficientSockQuantityException;
import me.bakuleva.coursework3.exception.InvalidSockRequestException;
import me.bakuleva.coursework3.model.Color;
import me.bakuleva.coursework3.model.Size;
import me.bakuleva.coursework3.model.Sock;
import me.bakuleva.coursework3.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;

    }
    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidSockRequestException(InvalidSockRequestException invalidSockRequestException){
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }
    @ExceptionHandler(InSufficientSockQuantityException.class)
    public ResponseEntity<String> handleInSufficientSockQuantityException(InSufficientSockQuantityException invalidSockRequestException ){
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }
    @PostMapping

    public void addSocks(@RequestBody SockRequest sockRequest){
        sockService.addSock(sockRequest);

    }
    @PostMapping
    public void issueSock(@RequestBody SockRequest sockRequest){
        sockService.issueSock(sockRequest);
    }
    @GetMapping
    public int getSocksCount(@RequestParam(required = false,name = "color")Color color,
                             @RequestParam(required = false,name = "size")Size size,
                             @RequestParam(required = false,name = "cottonMin")Integer cottonMin,
                             @RequestParam(required = false,name = "cottonMax")Integer cottonMax){
        return sockService.getSockQuantity(color,size,cottonMin,cottonMax);

    }
    @GetMapping
    public void removeDefectiveSocks(@RequestBody SockRequest sockRequest){
        sockService.removeDefectiveSocks(sockRequest);
    }

}

