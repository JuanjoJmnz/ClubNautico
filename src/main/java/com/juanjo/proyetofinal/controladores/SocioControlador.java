package com.juanjo.proyetofinal.controladores;

import com.juanjo.proyetofinal.controladores.dto.SocioDTO;
import com.juanjo.proyetofinal.entidades.Socio;
import com.juanjo.proyetofinal.servicios.ISocioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/socio") // Ruta base para todos los endpoints de socio
public class SocioControlador {

    @Autowired
    private ISocioServicio socioServicio; // Inyección del servicio para manejar la lógica de negocio

    // Buscar un socio por su id
    @RequestMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Socio> socioOptional = socioServicio.findById(id);

        if(socioOptional.isPresent()){
            Socio socio = socioOptional.get();
            // Convertimos la entidad Socio a SocioDTO para no exponer la entidad directamente
            SocioDTO socioDTO = SocioDTO.builder()
                    .idSocio(socio.getIdSocio())
                    .nombreSocio(socio.getNombreSocio())
                    .apellidoSocio(socio.getApellidoSocio())
                    .direccionSocio(socio.getDireccionSocio())
                    .telefonoSocio(socio.getTelefonoSocio())
                    .barcos(socio.getBarcos()) // Incluimos lista de barcos
                    .build();
            return ResponseEntity.ok(socioDTO);
        }
        return ResponseEntity.notFound().build(); // 404 si no existe socio con ese id
    }

    // Obtener lista de todos los socios
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<SocioDTO> listaSocio = socioServicio.findAll()
                .stream()
                .map(socio -> SocioDTO.builder()
                        .idSocio(socio.getIdSocio())
                        .nombreSocio(socio.getNombreSocio())
                        .apellidoSocio(socio.getApellidoSocio())
                        .direccionSocio(socio.getDireccionSocio())
                        .telefonoSocio(socio.getTelefonoSocio())
                        .barcos(socio.getBarcos())
                        .build())
                .toList();
        return ResponseEntity.ok(listaSocio);
    }

    // Guardar un nuevo socio
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SocioDTO socioDTO) throws URISyntaxException {
        if (socioDTO.getNombreSocio().isBlank()){
            return ResponseEntity.badRequest().build(); // Validación simple para nombre no vacío
        }
        // Guardamos nuevo socio convertido de DTO a entidad
        socioServicio.save(Socio.builder()
                .nombreSocio(socioDTO.getNombreSocio())
                .apellidoSocio(socioDTO.getApellidoSocio())
                .direccionSocio(socioDTO.getDireccionSocio())
                .telefonoSocio(socioDTO.getTelefonoSocio())
                .barcos(socioDTO.getBarcos())
                .build());

        return ResponseEntity.created(new URI("/api/socio/")).build(); // Devuelve 201 Created
    }

    // Actualizar un socio existente por id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSocio(@PathVariable int id, @RequestBody SocioDTO socioDTO) throws URISyntaxException {
        Optional<Socio> socioOptional = socioServicio.findById(id);

        if (socioOptional.isPresent()) {
            Socio socio = socioOptional.get();
            // Actualiza solo campos no nulos del DTO
            if (socioDTO.getNombreSocio() != null) {
                socio.setNombreSocio(socioDTO.getNombreSocio());
            }
            if (socioDTO.getApellidoSocio() != null) {
                socio.setApellidoSocio(socioDTO.getApellidoSocio());
            }
            if (socioDTO.getDireccionSocio() != null) {
                socio.setDireccionSocio(socioDTO.getDireccionSocio());
            }
            if (socioDTO.getTelefonoSocio() != null) {
                socio.setTelefonoSocio(socioDTO.getTelefonoSocio());
            }
            socioServicio.save(socio); // Guardamos entidad actualizada
            return ResponseEntity.ok("Socio actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar socios cuyo id esté entre min y max (inclusive)
    @GetMapping("/findEntre/{min},{max}")
    public ResponseEntity<?> findSociosEntreIds(@PathVariable int min, @PathVariable int max) {
        List<SocioDTO> socios = socioServicio.findByIdRange(min, max)
                .stream()
                .map(socio -> SocioDTO.builder()
                        .idSocio(socio.getIdSocio())
                        .nombreSocio(socio.getNombreSocio())
                        .apellidoSocio(socio.getApellidoSocio())
                        .direccionSocio(socio.getDireccionSocio())
                        .telefonoSocio(socio.getTelefonoSocio())
                        .barcos(socio.getBarcos())
                        .build())
                .toList();
        return ResponseEntity.ok(socios);
    }

    // Buscar socios cuyo nombre contenga el texto recibido (ignorando mayúsculas/minúsculas)
    @GetMapping("/find/nombre")
    public ResponseEntity<?> findSociosNombre(@RequestParam("nombre") String nombre) {
        List<SocioDTO> socios = socioServicio.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(socio -> SocioDTO.builder()
                        .idSocio(socio.getIdSocio())
                        .nombreSocio(socio.getNombreSocio())
                        .apellidoSocio(socio.getApellidoSocio())
                        .direccionSocio(socio.getDireccionSocio())
                        .telefonoSocio(socio.getTelefonoSocio())
                        .barcos(socio.getBarcos())
                        .build())
                .toList();
        return ResponseEntity.ok(socios);
    }

    // Buscar socios cuyo nombre sea exactamente igual al recibido
    @GetMapping("/find/nombreExacto/{nombre}")
    public ResponseEntity<?> findSocioNombreExacto(@PathVariable String nombre) {
        List<SocioDTO> socios = socioServicio.findByNombreExacto(nombre)
                .stream()
                .map(socio -> SocioDTO.builder()
                        .idSocio(socio.getIdSocio())
                        .nombreSocio(socio.getNombreSocio())
                        .apellidoSocio(socio.getApellidoSocio())
                        .direccionSocio(socio.getDireccionSocio())
                        .telefonoSocio(socio.getTelefonoSocio())
                        .barcos(socio.getBarcos())
                        .build())
                .toList();
        return ResponseEntity.ok(socios);
    }

    // Buscar socio por teléfono
    @GetMapping("/find/telefono/{telefono}")
    public ResponseEntity<?> findSociosTelefono(@PathVariable String telefono) {
        Optional<Socio> socioOptional = socioServicio.findByTelefono(telefono);

        if(socioOptional.isPresent()){
            Socio socio = socioOptional.get();
            SocioDTO socioDTO = SocioDTO.builder()
                    .idSocio(socio.getIdSocio())
                    .nombreSocio(socio.getNombreSocio())
                    .apellidoSocio(socio.getApellidoSocio())
                    .direccionSocio(socio.getDireccionSocio())
                    .telefonoSocio(socio.getTelefonoSocio())
                    .barcos(socio.getBarcos())
                    .build();
            return ResponseEntity.ok(socioDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un socio por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        if(id != null){
            socioServicio.deleteById(id);
            return ResponseEntity.ok("Socio eliminado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/contador")
    public ResponseEntity<?> contarSocios(){
        long contador = socioServicio.contarSocio();
        return ResponseEntity.ok("Existen un total de " + contador + " socios en el club náutico.");
    }
}