package lanchonete.api.api.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public Map<String, String> verificarSistema() {

        String banco = jdbcTemplate.queryForObject(
            "SELECT current_database()",
            String.class
        );

        return Map.of(
            "status", "ONLINE",
            "aplicacao", "Lanchonete API",
            "banco", banco
        );
    }
}