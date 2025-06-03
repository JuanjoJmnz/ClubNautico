package com.juanjo.proyetofinal.controladores;

import com.juanjo.proyetofinal.controladores.dto.BarcoDTO;
import com.juanjo.proyetofinal.controladores.dto.ViajeDTO;
import com.juanjo.proyetofinal.entidades.Barco;
import com.juanjo.proyetofinal.entidades.Socio;
import com.juanjo.proyetofinal.entidades.Viaje;
import com.juanjo.proyetofinal.servicios.IBarcoServicio;
import com.juanjo.proyetofinal.servicios.ISocioServicio;
import com.juanjo.proyetofinal.servicios.IViajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/viaje") // Ruta base para todos los endpoints de viaje
public class ViajeControlador {

    @Autowired
    private IViajeServicio viajeServicio; // Servicio para manejar lógica de viajes

    @Autowired
    private IBarcoServicio barcoServicio; // Servicio para manejar lógica de barcos

    @Autowired
    private ISocioServicio socioServicio; // Servicio para manejar lógica de socios

    // Método auxiliar para convertir entidad Viaje a DTO ViajeDTO
    public ViajeDTO convertirDTO(Viaje viaje) {
        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setIdViaje(viaje.getIdViaje());
        viajeDTO.setFechaHora(viaje.getFechaHora());
        viajeDTO.setDestino(viaje.getDestino());
        viajeDTO.setIdBarco(viaje.getBarco().getIdBarco()); // Seteamos id del barco

        // Convertimos el barco asociado a un BarcoDTO
        Barco barco = viaje.getBarco();
        BarcoDTO barcoDTO = BarcoDTO.builder()
                .idBarco(barco.getIdBarco())
                .matriculaBarco(barco.getMatriculaBarco())
                .nombreBarco(barco.getNombreBarco())
                .numeroAmarre(barco.getNumeroAmarre())
                .cuotaBarco(barco.getCuotaBarco())
                .barcosDeSocio(barco.getBarcosDeSocio())
                .build();
        viajeDTO.setDatosBarco(barcoDTO); // Asignamos DTO del barco al viajeDTO

        // Si el viaje tiene socio patrón, extraemos sus datos
        if(viaje.getSocioPatron() != null) {
            viajeDTO.setIdPatronSocio(viaje.getSocioPatron().getIdSocio());
            viajeDTO.setNombrePatron(viaje.getSocioPatron().getNombreSocio());
            viajeDTO.setApellidoPatron(viaje.getSocioPatron().getApellidoSocio());
            viajeDTO.setTelefonoPatron(viaje.getSocioPatron().getTelefonoSocio());
        }
        else {
            // Si no hay socio patrón, usamos los datos sueltos que pueda tener el viaje
            viajeDTO.setNombrePatron(viaje.getNombrePatron());
            viajeDTO.setApellidoPatron(viaje.getApellidoPatron());
            viajeDTO.setTelefonoPatron(viaje.getTelefonoPatron());
        }
        return viajeDTO; // Devolvemos el DTO completo
    }

    // Endpoint para buscar un viaje por su id
    @RequestMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Viaje> viajeOptional = viajeServicio.findById(id);

        if(viajeOptional.isEmpty()){
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }
        ViajeDTO viajeDTO = convertirDTO(viajeOptional.get()); // Convertimos a DTO
        return ResponseEntity.ok(viajeDTO); // Devolvemos el viaje DTO con 200 OK
    }

    // Endpoint para obtener todos los viajes
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ViajeDTO> listaViajes = viajeServicio.findAll()
                .stream()
                .map(this::convertirDTO) // Convertimos cada viaje a DTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaViajes);
    }

    // Buscar viajes filtrando por destino (exacto)
    @GetMapping("/find/destino/{destino}")
    public ResponseEntity<?> findByDestino(@PathVariable String destino){
        List<ViajeDTO> listaViajes = viajeServicio.findViajeByDestino(destino)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaViajes);
    }

    // Buscar viajes cuyo fechaHora esté entre dos fechas (formato: yyyy-MM-dd HH:mm:ss)
    @GetMapping("/find/fechaEntre/{fecha1},{fecha2}")
    public ResponseEntity<?> findByFechas(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fecha1,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fecha2){
        List<ViajeDTO> listaViajes = viajeServicio.findByFechaViajeBetween(fecha1, fecha2)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaViajes);
    }

    // Crear un nuevo viaje (POST)
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ViajeDTO viajeDTO) throws URISyntaxException {
        // Buscamos el barco por id para asignarlo al viaje
        Optional<Barco> barcoOptional = barcoServicio.findById(Math.toIntExact(viajeDTO.getIdBarco()));
        if(barcoOptional.isEmpty()){
            return ResponseEntity.notFound().build(); // No se puede guardar sin barco válido
        }

        Viaje viaje = new Viaje();
        viaje.setIdViaje(viajeDTO.getIdViaje());

        // Si fechaHora no viene, se asigna la fecha/hora actual
        if(viajeDTO.getFechaHora() != null){
            viaje.setFechaHora(viajeDTO.getFechaHora());
        }
        else{
            viaje.setFechaHora(LocalDateTime.now());
        }

        viaje.setDestino(viajeDTO.getDestino());
        viaje.setBarco(barcoOptional.get());

        // Si se indica un id de socio patrón, se busca y asigna
        if(viajeDTO.getIdPatronSocio() != null){
            Optional<Socio> socioOptional = socioServicio.findById(Math.toIntExact(viajeDTO.getIdPatronSocio()));
            if(socioOptional.isEmpty()){
                return ResponseEntity.notFound().build(); // No se puede guardar sin socio válido
            }
            Socio socio = socioOptional.get();
            viaje.setSocioPatron(socio);
            viaje.setNombrePatron(socio.getNombreSocio());
            viaje.setApellidoPatron(socio.getApellidoSocio());
            viaje.setTelefonoPatron(socio.getTelefonoSocio());
        }
        else{
            // Si no hay socio patrón, se asignan los datos sueltos del patrón que venga
            viaje.setSocioPatron(null);
            viaje.setNombrePatron(viajeDTO.getNombrePatron());
            viaje.setApellidoPatron(viajeDTO.getApellidoPatron());
            viaje.setTelefonoPatron(viajeDTO.getTelefonoPatron());
        }
        viajeServicio.save(viaje); // Guardamos el viaje
        return ResponseEntity.created(new URI("/api/viaje/save/")).build(); // 201 Created
    }

    // Actualizar un viaje existente por id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ViajeDTO viajeDTO) throws URISyntaxException {
        Optional<Viaje> viajeOptional = viajeServicio.findById(id);
        if (viajeOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // No existe el viaje
        }

        Viaje viaje = viajeOptional.get();

        // Actualizamos fecha y destino solo si vienen en el DTO
        if (viajeDTO.getFechaHora() != null) {
            viaje.setFechaHora(viajeDTO.getFechaHora());
        }

        if (viajeDTO.getDestino() != null) {
            viaje.setDestino(viajeDTO.getDestino());
        }

        // Si se proporciona idBarco, actualizamos el barco
        if (viajeDTO.getIdBarco() != null) {
            Optional<Barco> barcoOptional = barcoServicio.findById(Math.toIntExact(viajeDTO.getIdBarco()));
            if (barcoOptional.isEmpty()) {
                return ResponseEntity.notFound().build(); // Barco no existe
            }
            viaje.setBarco(barcoOptional.get());
        }

        // Actualizar patrón: si viene idPatronSocio lo buscamos y asignamos
        if (viajeDTO.getIdPatronSocio() != null) {
            Optional<Socio> socioOptional = socioServicio.findById(Math.toIntExact(viajeDTO.getIdPatronSocio()));
            if (socioOptional.isEmpty()) {
                return ResponseEntity.notFound().build(); // Socio no existe
            }
            Socio socio = socioOptional.get();
            viaje.setSocioPatron(socio);
            viaje.setNombrePatron(socio.getNombreSocio());
            viaje.setApellidoPatron(socio.getApellidoSocio());
            viaje.setTelefonoPatron(socio.getTelefonoSocio());
        } else {
            // Si no se indica idPatronSocio, actualizamos datos sueltos si vienen
            viaje.setSocioPatron(null);
            if (viajeDTO.getNombrePatron() != null) {
                viaje.setNombrePatron(viajeDTO.getNombrePatron());
            }
            if (viajeDTO.getApellidoPatron() != null) {
                viaje.setApellidoPatron(viajeDTO.getApellidoPatron());
            }
            if (viajeDTO.getTelefonoPatron() != null) {
                viaje.setTelefonoPatron(viajeDTO.getTelefonoPatron());
            }
        }

        viajeServicio.save(viaje); // Guardamos los cambios
        return ResponseEntity.ok(viaje); // Respondemos con el viaje actualizado
    }

    // Eliminar un viaje por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(id != null){
            viajeServicio.deleteById(id); // Borramos el viaje si el id es válido
        }
        return ResponseEntity.ok("Viaje eliminado con éxito."); // Confirmación
    }
}