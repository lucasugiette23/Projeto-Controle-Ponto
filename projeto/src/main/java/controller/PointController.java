package controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PointService;

@RestController
@RequestMapping("/ponto")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor_ =  {@Autowired})
public class PointController {

    private final PointService pointService;

    @ApiOperation(value = "Bater ponto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna se a solicitação foi confirmada ou não"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bater-ponto/{id}/{hourPoint}")
    public ResponseEntity<?> findAllByUser(@PathVariable("id") Long userUid, @PathVariable("hourPoint") String hourPoint) {
        return new ResponseEntity(this.pointService.baterPonto(userUid,hourPoint), HttpStatus.OK);
    }


}
