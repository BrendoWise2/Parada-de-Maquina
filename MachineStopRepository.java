package com.maquina.maquina.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maquina.maquina.model.domain.MachineStop;

public interface MachineStopRepository extends JpaRepository<MachineStop, Long> {
    Optional <MachineStop> findByEquipamento(String equipamento);
}
