package com.maquina.maquina.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maquina.maquina.exceptions.MachineStopSalvarException;
import com.maquina.maquina.model.domain.MachineStop;
import com.maquina.maquina.model.domain.MachineStopSumarry;
import com.maquina.maquina.model.domain.StatusParada;
import com.maquina.maquina.model.repository.MachineStopRepository;

@Service
public class MachineStopServiceImpl implements MachineStopService {

    @Autowired
    private MachineStopRepository machineStopRepository;

    @Override
    public MachineStop salvar(MachineStop machineStop) throws MachineStopSalvarException {
       // var machineStopSalvo = machineStopRepository.findByEquipamento(machineStop.getEquipamento());
        return machineStopRepository.save(machineStop);
    }

    @Override
    public void atualizar(MachineStop machineStop) {
        machineStopRepository.save(machineStop);
    }

    @Override
    public void deletar(MachineStop machineStop) throws MachineStopSalvarException {
       machineStopRepository.delete(machineStop);
    }

    @Override
    public void startStop(MachineStop machineStop) {
        machineStop.setStatus(StatusParada.INICIADA);
        machineStop.setHorario(LocalDateTime.now());
        machineStopRepository.save(machineStop);
    }

    @Override
    public void stopStop(MachineStop machineStop) {
        machineStop.setStatus(StatusParada.FINALIZADA);
        machineStop.setHorario(LocalDateTime.now());
        machineStopRepository.save(machineStop);
    }

    @Override
    public List<MachineStop> getAllStops() {
        return machineStopRepository.findAll();
    }

    @Override
    public List<MachineStopSumarry> getMachineStopSumarries() {

        //listando todas a maquinas
        List<MachineStop> stops = getAllStops();

        //Agrupar por nomes
        Map<String, Long> totalStopTime = stops.stream()
            .collect(Collectors.groupingBy(
                MachineStop::getEquipamento,
                Collectors.summingLong(MachineStop::getStopTimeHours)
            ));

            return totalStopTime.entrySet().stream()
                .map(entry -> new MachineStopSumarry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    
}
