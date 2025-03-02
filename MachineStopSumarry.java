package com.maquina.maquina.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tempoParadas")
public class MachineStopSumarry {
    
    private String machineName;
    private long totalStopTime;

    public long getTotalStopTime() {
        return totalStopTime;
    }
}
