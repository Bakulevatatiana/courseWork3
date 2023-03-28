package me.bakuleva.coursework3.service;

import me.bakuleva.coursework3.dto.SockRequest;
import me.bakuleva.coursework3.exception.InSufficientSockQuantityException;
import me.bakuleva.coursework3.exception.InvalidSockRequestException;
import me.bakuleva.coursework3.model.Color;
import me.bakuleva.coursework3.model.Size;
import me.bakuleva.coursework3.model.Sock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SockService {
    private final Map<Sock, Integer> socks = new HashMap<>();

    public void addSock(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock= mapToSock(sockRequest);
        if (socks.containsKey(sock)){
            socks.put(sock,socks.get(sock)+ sockRequest.getQuantity());

        }
        else {
            socks.put(sock,sockRequest.getQuantity());
        }
    }
    public void issueSock(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);

    }
    public void  decreaseSockQuantity(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        int sockQuantity = socks.getOrDefault(sock, 0);
        if (sockQuantity >= sockRequest.getQuantity()) {
            socks.put(sock, sockQuantity - sockRequest.getQuantity());
        } else {
            throw new InSufficientSockQuantityException("Носков нет");
        }
    }
    public void removeDefectiveSocks (SockRequest sockRequest){
        decreaseSockQuantity(sockRequest);
    }

    public int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPercentage() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPercentage() > cottonMax) {
                continue;
            }
            total += entry.getValue();

        }
        return  total;
    }

    private void validateRequest(SockRequest sockRequest) {

        if (sockRequest.getColor() == null || sockRequest.getSize() == null) {
            throw new InvalidSockRequestException("Все поля должны быть заполнены");
        }
        if (sockRequest.getQuantity() <= 0) {
            throw new InvalidSockRequestException("Колличество должно быть больше 0");

        }
        if (sockRequest.getCottonPercentage() < 0 || sockRequest.getCottonPercentage() > 100) {
            throw new InvalidSockRequestException("Должен быть от 0 до 100");

        }

    }
    public Sock mapToSock(SockRequest sockRequest){
        return new Sock(sockRequest.getColor(),sockRequest.getSize(),sockRequest.getCottonPercentage());


    }



}
