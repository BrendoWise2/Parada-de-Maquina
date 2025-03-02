package com.maquina.maquina.model.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "maquinas")
public class MachineStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String operator;
    public String equipamento;
    public LocalDateTime start;
    public LocalDateTime end;

    private LocalDateTime horario;

    @Enumerated(EnumType.STRING)
    private StatusParada status;

    public long getStopTimeHours(){
        if(start != null && end != null){
            Duration duration = Duration.between(start, end);
            return duration.getSeconds();
        }

        return 0;
    }
   
    
}
