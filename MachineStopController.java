package com.maquina.maquina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maquina.maquina.model.domain.MachineStop;
import com.maquina.maquina.model.domain.MachineStopSumarry;
import com.maquina.maquina.model.service.MachineStopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/maquina")
public class MachineStopController {
    @Autowired
    private MachineStopService machineStopService;

    @PostMapping("/iniciar")
    public String startMachineStop(@RequestBody MachineStop machineStop) {
        
        machineStopService.startStop(machineStop);
        
        return "Parada Iniciada!";
    }
    
    @PostMapping("/stop")
    public String stopMachineStop(@RequestBody MachineStop machineStop) {
        
        machineStopService.stopStop(machineStop);

        return "Parada Finalizada";
    }

    @GetMapping("/historico")
    public List <MachineStop> getAllMachineStops() {
        return machineStopService.getAllStops();
    }

    @GetMapping("/maquinas/total")
    public List<MachineStopSumarry> getMachineTopSummary() {
        return machineStopService.getMachineStopSumarries();
    }
    
    
    
}
