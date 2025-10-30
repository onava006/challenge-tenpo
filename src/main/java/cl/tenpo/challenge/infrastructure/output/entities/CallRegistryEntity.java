package cl.tenpo.challenge.infrastructure.output.entities;

import cl.tenpo.challenge.domain.model.CallStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "call_registry")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallRegistryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "parameters", nullable = false)
    private String parameters;

    @Enumerated(EnumType.STRING)
    @Column(name = "call_status", nullable = false, length = 20)
    private CallStatus callstatus;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}