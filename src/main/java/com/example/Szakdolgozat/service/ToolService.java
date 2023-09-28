package com.example.Szakdolgozat.service;

import com.example.Szakdolgozat.entities.ToolEntity;
import com.example.Szakdolgozat.entities.UserEntity;
import com.example.Szakdolgozat.repository.ToolRepository;
import com.example.Szakdolgozat.service.mapper.ToolMapper;
import com.example.Szakdolgozat.web.model.CreateToolRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.tools.Tool;
import java.time.LocalDateTime;
import java.util.List;





@Service
@AllArgsConstructor
public class ToolService {

    private final ToolRepository toolRepository;

    private final ToolMapper toolMapper;



    public ToolEntity addTool(CreateToolRequest createToolRequest) throws Exception {
        ToolEntity tool = toolMapper.map(createToolRequest);
        tool.setDateOfReceiving(LocalDateTime.now());
        return toolRepository.save(tool);
    }

}
