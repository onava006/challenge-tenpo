CREATE TABLE call_registry (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    endpoint VARCHAR(255) NOT NULL,
    parameters VARCHAR(255) NOT NULL,
    call_status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT chk_call_status CHECK (call_status IN ('SUCCESS', 'FAILURE', 'TIMEOUT'))
);

CREATE INDEX idx_call_registry_date ON call_registry(date);
CREATE INDEX idx_call_registry_status ON call_registry(call_status);