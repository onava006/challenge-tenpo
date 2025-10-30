package cl.tenpo.challenge.infrastructure.input.rest.adapter;

import cl.tenpo.challenge.infrastructure.adapters.in.rest.api.TenpoApi;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.TotalSumResponse;
import cl.tenpo.challenge.application.port.input.GenerateTotalSumUseCase;
import cl.tenpo.challenge.infrastructure.input.rest.mapper.TotalSumMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenpoController implements TenpoApi {

    GenerateTotalSumUseCase generateTotalSum;

   public TenpoController(GenerateTotalSumUseCase generateTotalSum){
       this.generateTotalSum = generateTotalSum;
   };

    @Override
    public ResponseEntity<TotalSumResponse> calculateTotal(Integer num1, Integer num2) {
        TotalSumResponse totalSum = TotalSumMapper.toEntity(generateTotalSum.get(num1, num2));
        return new ResponseEntity<>(totalSum, HttpStatus.OK);
    }
}
