package com.maquina.maquina.model.service;

import java.util.List;

import com.maquina.maquina.exceptions.MachineStopSalvarException;
import com.maquina.maquina.model.domain.MachineStop;
import com.maquina.maquina.model.domain.MachineStopSumarry;

public interface MachineStopService {
    
    MachineStop salvar (MachineStop machineStop) throws MachineStopSalvarException;
    void atualizar(MachineStop machineStop);
    void deletar(MachineStop machineStop) throws MachineStopSalvarException;
    List<MachineStop> getAllStops();
    void startStop(MachineStop machineStop);
    void stopStop(MachineStop machineStop);

    public List<MachineStopSumarry> getMachineStopSumarries(){
        
    }

}
