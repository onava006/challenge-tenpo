package cl.tenpo.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CallRegistry(
        LocalDateTime date,
        String endpoint,
        String parameters,
        CALLSTATUS callstatus) {

    /***
     *  fecha, endpoint,
     *     parámetros, respuesta o error
     */

    private enum CALLSTATUS{
        RECEIVED,
        ERROR,
        REDIRECTED
    }

}


