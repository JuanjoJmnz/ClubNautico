package com.juanjo.proyetofinal.controladores;

import com.juanjo.proyetofinal.controladores.dto.BarcoDTO;
import com.juanjo.proyetofinal.entidades.Barco;
import com.juanjo.proyetofinal.entidades.Socio;
import com.juanjo.proyetofinal.repositorio.RepositorioSocio;
import com.juanjo.proyetofinal.servicios.IBarcoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/barco")
public class BarcoControlador {

    // Inyección del servicio que maneja la lógica de negocio relacionada con barcos
    @Autowired
    private IBarcoServicio barcoServicio;

    // Buscar un barco por su ID
    @RequestMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Barco> barcoOptional = barcoServicio.findById(id);

        if(barcoOptional.isPresent()){
            Barco barco = barcoOptional.get();
            // Conversión de la entidad Barco a su DTO para la respuesta
            BarcoDTO barcoDTO = BarcoDTO.builder()
                    .idBarco(barco.getIdBarco())
                    .matriculaBarco(barco.getMatriculaBarco())
                    .nombreBarco(barco.getNombreBarco())
                    .numeroAmarre(barco.getNumeroAmarre())
                    .cuotaBarco(barco.getCuotaBarco())
                    .barcosDeSocio(barco.getBarcosDeSocio())
                    .viajes(barco.getViajes())
                    .build();
            return ResponseEntity.ok(barcoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar barcos con cuota entre dos valores dados
    @GetMapping(value = "/findEntre/{min},{max}")
    public ResponseEntity<?> findBetween(@PathVariable BigDecimal min, @PathVariable BigDecimal max){
        List<BarcoDTO> listaBarco = barcoServicio.findByCuotaBarcoInRange(min, max)
                .stream()
                .map(barco -> BarcoDTO.builder()
                        .idBarco(barco.getIdBarco())
                        .matriculaBarco(barco.getMatriculaBarco())
                        .nombreBarco(barco.getNombreBarco())
                        .numeroAmarre(barco.getNumeroAmarre())
                        .cuotaBarco(barco.getCuotaBarco())
                        .barcosDeSocio(barco.getBarcosDeSocio())
                        .viajes(barco.getViajes())
                        .build())
                .toList();
        return ResponseEntity.ok(listaBarco);
    }

    // Buscar barco por nombre exacto
    @GetMapping(value = "/find/nombre/{nombre}")
    public ResponseEntity<?> findNombre(@PathVariable String nombre){
        Optional<Barco> barcoOptional = barcoServicio.findByNombreBarco(nombre);

        if(barcoOptional.isPresent()){
            Barco barco = barcoOptional.get();
            BarcoDTO barcoDTO = BarcoDTO.builder()
                    .idBarco(barco.getIdBarco())
                    .matriculaBarco(barco.getMatriculaBarco())
                    .nombreBarco(barco.getNombreBarco())
                    .numeroAmarre(barco.getNumeroAmarre())
                    .cuotaBarco(barco.getCuotaBarco())
                    .barcosDeSocio(barco.getBarcosDeSocio())
                    .viajes(barco.getViajes())
                    .build();
            return ResponseEntity.ok(barcoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar barcos por matrícula
    @GetMapping(value = "/find/matricula/{matricula}")
    public ResponseEntity<?> findMatricula(@PathVariable String matricula){
        Optional<Barco> barcoOptional = barcoServicio.findByMatriculaBarco(matricula);

        if(barcoOptional.isPresent()){
            Barco barco = barcoOptional.get();
            BarcoDTO barcoDTO = BarcoDTO.builder()
                    .idBarco(barco.getIdBarco())
                    .matriculaBarco(barco.getMatriculaBarco())
                    .nombreBarco(barco.getNombreBarco())
                    .numeroAmarre(barco.getNumeroAmarre())
                    .cuotaBarco(barco.getCuotaBarco())
                    .barcosDeSocio(barco.getBarcosDeSocio())
                    .viajes(barco.getViajes())
                    .build();
            return ResponseEntity.ok(barcoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Obtener la lista completa de barcos registrados
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<BarcoDTO> listaBarco = barcoServicio.findAll()
                .stream()
                .map(barco -> BarcoDTO.builder()
                        .idBarco(barco.getIdBarco())
                        .matriculaBarco(barco.getMatriculaBarco())
                        .nombreBarco(barco.getNombreBarco())
                        .numeroAmarre(barco.getNumeroAmarre())
                        .cuotaBarco(barco.getCuotaBarco())
                        .barcosDeSocio(barco.getBarcosDeSocio())
                        .viajes(barco.getViajes())
                        .build())
                .toList();
        return ResponseEntity.ok(listaBarco);
    }

    // Guardar un nuevo barco en la base de datos
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BarcoDTO barcoDTO) throws URISyntaxException {
        // Validaciones básicas manuales (se puede mejorar usando @Valid en el DTO)
        if (barcoDTO.getNombreBarco().isBlank() || barcoDTO.getMatriculaBarco().isBlank() || barcoDTO.getBarcosDeSocio() == null){
            return ResponseEntity.badRequest().build();
        }

        // Conversión del DTO a entidad Barco
        Barco barco = Barco.builder()
                .idBarco(barcoDTO.getIdBarco())
                .matriculaBarco(barcoDTO.getMatriculaBarco())
                .nombreBarco(barcoDTO.getNombreBarco())
                .numeroAmarre(barcoDTO.getNumeroAmarre())
                .cuotaBarco(barcoDTO.getCuotaBarco())
                .barcosDeSocio(barcoDTO.getBarcosDeSocio())
                .build();

        barcoServicio.save(barco);
        return ResponseEntity.created(new URI("/api/barco/save/")).build();
    }

    // Actualizar un barco existente por su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBarco(@PathVariable int id, @RequestBody BarcoDTO barcoDTO) throws URISyntaxException {
        Optional<Barco> barcoOptional = barcoServicio.findById(id);

        if (barcoOptional.isPresent()) {
            Barco barco = barcoOptional.get();

            // Actualiza solo los campos que no sean nulos en el DTO recibido
            if (barcoDTO.getMatriculaBarco() != null) {
                barco.setMatriculaBarco(barcoDTO.getMatriculaBarco());
            }
            if (barcoDTO.getNombreBarco() != null) {
                barco.setNombreBarco(barcoDTO.getNombreBarco());
            }
            if (barcoDTO.getNumeroAmarre() != null) {
                barco.setNumeroAmarre(barcoDTO.getNumeroAmarre());
            }
            if (barcoDTO.getCuotaBarco() != null) {
                barco.setCuotaBarco(barcoDTO.getCuotaBarco());
            }
            if(barcoDTO.getBarcosDeSocio() != null){
                barco.setBarcosDeSocio(barcoDTO.getBarcosDeSocio());
            }

            barcoServicio.save(barco);
            return ResponseEntity.ok("Barco actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un barco por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        if(id != null){
            barcoServicio.deleteById(id);
            return ResponseEntity.ok("Barco eliminado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/contador")
    public ResponseEntity<?> contarBarcos(){
        long contador = barcoServicio.contarBarco();
        return ResponseEntity.ok("Existen un total de " + contador + " barcos en el club náutico.");
    }
}