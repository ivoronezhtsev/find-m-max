package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/nmax")
@Tag(
        name = "Поиск N-го максимального элемента в Excel-файле"
)
public class SelectController {
    private final QuickSelectService quickSelectService;

    public SelectController(QuickSelectService quickSelectService) {
        this.quickSelectService = quickSelectService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить N-ное максимальное число из файла ")
    int getNthMax(@RequestParam String filePath, @RequestParam int n) {
        return quickSelectService.findNthMax(filePath, n);
    }
}
