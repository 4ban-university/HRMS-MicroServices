package com.hrms.soen6841.position.controller;

import com.hrms.soen6841.position.pojo.Position;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import javax.validation.Valid;


@RestController
@RequestMapping("/")
public class PositionController {

    @GetMapping("/")
    public List<Position> getPositionsList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Position>> response = restTemplate.exchange(
                "http://localhost:8700/position/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Position>>(){});
        List<Position> positions = response.getBody();
        return positions;
    }

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Position> response = restTemplate.exchange(
                "http://localhost:8700/position/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Position>(){});
        Position position = response.getBody();
        return position;
    }

    @PutMapping("/{id}")
    public Position editPosition(@RequestBody Position newPosition, @PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Position> response = restTemplate.exchange(
                "http://localhost:8700/position/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newPosition),
                new ParameterizedTypeReference<Position>(){});
        Position position = response.getBody();
        return position;
    }

    @DeleteMapping("/{id}")
    public String deletePosition(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                "http://localhost:8700/position/" + id,
                HttpMethod.DELETE,
                null,
                String.class);
        return "Position deleted successfully";
    }
}
