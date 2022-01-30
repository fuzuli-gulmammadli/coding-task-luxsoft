package az.fuzuli.gulmammadli.codingtask.controllers;

import az.fuzuli.gulmammadli.codingtask.utils.FileUtils;
import az.fuzuli.gulmammadli.codingtask.dtos.AppDataDto;
import az.fuzuli.gulmammadli.codingtask.services.AppDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/app-data")
public class AppDataController {

    private final AppDataService appDataService;
    private final FileUtils fileUtils;

    @PostMapping("/new")
    public List<AppDataDto> addNewAppData(@RequestParam("file") MultipartFile file) {
        List<String> lines = fileUtils.parsePlainTextFile(file);
        return appDataService.saveNewAppData(lines);
    }

    @GetMapping("/{id}")
    public AppDataDto getAppData(@PathVariable String id) {
        return appDataService.getAppData(id);
    }

}
